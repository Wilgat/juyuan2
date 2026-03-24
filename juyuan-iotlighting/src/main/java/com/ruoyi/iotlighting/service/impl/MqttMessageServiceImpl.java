package com.ruoyi.iotlighting.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.iotlighting.domain.Diagnosis;
import com.ruoyi.iotlighting.domain.IotMessage;
import com.ruoyi.iotlighting.domain.TerAlert;
import com.ruoyi.iotlighting.domain.TerDataLog;
import com.ruoyi.iotlighting.domain.TerInfo;
import com.ruoyi.iotlighting.domain.TerSensorStatus;
import com.ruoyi.iotlighting.service.*;
import com.ruoyi.system.service.*;

import cn.hutool.core.util.IdUtil;                      // for IdUtil
import com.alibaba.fastjson2.JSONObject;               // for JSONObject
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;               // for BeanUtils.copyProperties
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;                    // for Collectors.toList()
import javax.annotation.*;
@Service
public class MqttMessageServiceImpl implements IMqttMessageService {

    private static final Logger log = LoggerFactory.getLogger(MqttMessageServiceImpl.class);

    @Autowired
    private ITerInfoService terInfoService;

    @Autowired
    private ITerSensorStatusService terSensorStatusService;

    @Autowired
    private ITerDataLogService terDataLogService;

    @Autowired
    private ITerAlertService terAlertService;

    @Autowired
    private IBuildingPatrolLogService buildingPatrolLogService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IDiagnosisService diagnosisService;

    private static final Map<String, String> SENSOR_TYPE_MAP = new HashMap<>();
    private static final Map<String, String> SENSOR_TYPE_EXTEND_MAP = new HashMap<>();

    static {
        SENSOR_TYPE_MAP.put("211", "light");
        SENSOR_TYPE_MAP.put("212", "light");
        SENSOR_TYPE_MAP.put("221", "light");
        SENSOR_TYPE_MAP.put("222", "light");
        SENSOR_TYPE_MAP.put("11", "motion");
        SENSOR_TYPE_MAP.put("12", "speed");
        SENSOR_TYPE_MAP.put("13", "height");
        SENSOR_TYPE_MAP.put("14", "park");
        SENSOR_TYPE_MAP.put("21", "smoke");
        SENSOR_TYPE_MAP.put("22", "h2s");
        SENSOR_TYPE_MAP.put("31", "com");
        SENSOR_TYPE_MAP.put("41", "post");
        SENSOR_TYPE_MAP.put("42", "patrol");
        SENSOR_TYPE_MAP.put("43", "flooding");
        SENSOR_TYPE_EXTEND_MAP.put("43#1", "flooding_1");
    }

    @Override
    public TerAlert handlSensorData(IotMessage message) {
        if (StringUtils.isEmpty(message.getSn()) || StringUtils.isEmpty(message.getType())) {
            log.error("处理失败，未匹配到灯管数据：{}", JSONObject.toJSONString(message));
            throw new ServiceException("处理失败，未匹配到灯管数据");
        }

        TerSensorStatus oldTerSensorStatus = terSensorStatusService.selectTerSensorByType(message.getSn(), message.getType());
        String sn = message.getSn();
        TerInfo terInfo = terInfoService.selectTerInfoBySn(sn);

        if (ObjectUtils.isEmpty(terInfo)) {
            throw new ServiceException("设备未注册，请注册后在发送数据");
        }

        message.setGateway(terInfo.getGateway());

        String sensorStatus = genSensorStatus(message);
        TerSensorStatus terSensorStatus = new TerSensorStatus();
        terSensorStatus.setData(message.getData());
        terSensorStatus.setSensorStatus(sensorStatus);
        terSensorStatus.setSensorType(message.getType());
        terSensorStatus.setTerSn(message.getSn());
        terSensorStatus.setUpdatedTime(new Date());

        if ("alarm".equals(sensorStatus)) {
            terSensorStatus.setDealFlag("0");
        }

        terSensorStatusService.updateSertTerSensorStatus(terSensorStatus);

        boolean alertFlag = false;

        if ("motion".equals(message.getType())) {
            alertFlag = (!ObjectUtils.isEmpty(oldTerSensorStatus) &&
                         "1".equals(oldTerSensorStatus.getSensorConfig()) &&
                         "alarm".equals(sensorStatus));
        } else if ("park".equals(message.getType())) {
            boolean newParkFlag = (ObjectUtils.isEmpty(oldTerSensorStatus) && "alarm".equals(sensorStatus));
            boolean startParkFlag = (!ObjectUtils.isEmpty(oldTerSensorStatus) &&
                                     !"alarm".equals(oldTerSensorStatus.getSensorStatus()) &&
                                     "alarm".equals(sensorStatus));
            boolean endParkFlag = (!ObjectUtils.isEmpty(oldTerSensorStatus) &&
                                   "alarm".equals(oldTerSensorStatus.getSensorStatus()) &&
                                   !"alarm".equals(sensorStatus));
            alertFlag = newParkFlag || startParkFlag || endParkFlag;
        } else {
            boolean newSensorTypeAlert = (ObjectUtils.isEmpty(oldTerSensorStatus) && "alarm".equals(sensorStatus));
            boolean normalAlertFlag = (!ObjectUtils.isEmpty(oldTerSensorStatus) &&
                                       !"alarm".equals(oldTerSensorStatus.getSensorStatus()) &&
                                       "alarm".equals(sensorStatus));
            alertFlag = newSensorTypeAlert || normalAlertFlag;
        }

        Long alertId = null;
        if (alertFlag) {
            TerAlert terAlert = new TerAlert();
            terAlert.setId(IdUtil.getSnowflakeNextId());  // ← fixed
            terAlert.setTerSn(message.getSn());
            terAlert.setCreatedTime(new Date());
            terAlert.setData(message.getData());
            terAlert.setDealFlag("0");
            terAlert.setSensorType(message.getType());
            terAlertService.insertTerAlert(terAlert);
            alertId = terAlert.getId();
        }

        String terStatus = genTerStatus(message);
        terInfo.setTerStatus(terStatus);
        terInfo.setUpdatedTime(new Date());               // ← must exist in TerInfo
        terInfoService.updateTerStatus(terInfo);

        TerDataLog terDataLog = new TerDataLog();
        BeanUtils.copyProperties(terSensorStatus, terDataLog);  // ← fixed: copyProperties
        terDataLog.setCreatedTime(new Date());
        terDataLogService.insertTerDataLog(terDataLog);

        if (alertFlag && !ObjectUtils.isEmpty(alertId)) {
            TerAlert param = new TerAlert();
            param.setId(alertId);
            List<TerAlert> terAlertList = terAlertService.selectTerAlertListNotLogin(param);
            if (CollectionUtils.isEmpty(terAlertList)) {
                return null;
            }
            return terAlertList.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public TerInfo handlTerOnline(String sn) {
        TerInfo terInfo = terInfoService.selectTerInfoBySn(sn);
        boolean newTerFlag = false;

        if (ObjectUtils.isEmpty(terInfo)) {
            terInfo = buildNewTer(sn);
            newTerFlag = true;
            // return null;  ← original behavior
        }

        terInfo.setUpdatedTime(new Date());  // ← must exist

        terInfo.setTerStatus("online");

        if (newTerFlag) {
            terInfoService.insertTerInfo(terInfo);
        } else {
            terInfoService.updateTerStatus(terInfo);
        }

        TerSensorStatus terSensorStatus = new TerSensorStatus();
        terSensorStatus.setTerSn(sn);
        terSensorStatus.setSensorStatus("online");
        terSensorStatus.setUpdatedTime(new Date());
        terSensorStatusService.updateTerSensorStatus(terSensorStatus);

        TerDataLog terDataLog = new TerDataLog();
        terDataLog.setTerSn(sn);
        terDataLog.setDelFlag("0");
        terDataLog.setSensorType("connected");
        terDataLog.setCreatedTime(new Date());
        terDataLogService.insertTerDataLog(terDataLog);

        return newTerFlag ? null : terInfo;
    }

    @Override
    public void handlTerOffline(String sn) {
        TerInfo terInfo = terInfoService.selectTerInfoBySn(sn);
        if (ObjectUtils.isEmpty(terInfo)) {
            return;
        }

        terInfo.setUpdatedTime(new Date());  // ← must exist
        terInfo.setTerStatus("offline");

        terInfoService.updateTerStatus(terInfo);

        TerDataLog terDataLog = new TerDataLog();
        terDataLog.setTerSn(sn);
        terDataLog.setDelFlag("0");
        terDataLog.setSensorType("disconnected");
        terDataLog.setCreatedTime(new Date());
        terDataLogService.insertTerDataLog(terDataLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleDiagnosis(IotMessage message) {
        List<String> data = Arrays.asList(message.getData().split(","));  // ← fixed
        if (CollectionUtils.isEmpty(data) || data.size() < 4) {
            throw new ServiceException("诊断数据格式异常");
        }

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setTerSn(message.getSn());
        diagnosis.setGateway(message.getGateway());

        String channel = data.get(0);
        String wifiSsid = data.get(1);
        String gatewayRssi = data.get(2);
        diagnosis.setWifiSsid(wifiSsid);
        diagnosis.setGatewayRssi(gatewayRssi);

        if (channel.length() == 1) {
            channel = "0" + channel;
        }
        diagnosis.setChannel(channel);
        diagnosis.setGatewayOnlineStatus("1");
        diagnosis.setGatewayDiagnosisUpdateTime(new Date());
        diagnosisService.updateGatewayStatus(diagnosis);

        String terOnlineStatus = data.get(3);
        diagnosis.setTerOnlineStatus(terOnlineStatus);

        if (StringUtils.isNotEmpty(terOnlineStatus) && "0".equals(terOnlineStatus)) {
            diagnosisService.updateTerStatusOffline(diagnosis);
            return;
        }

        String terRssi = data.get(4);
        String hopCount = data.get(5);
        if (hopCount.length() == 1) {
            hopCount = "0" + hopCount;
        }
        diagnosis.setTerRssi(terRssi);
        diagnosis.setHopCount(hopCount);
        diagnosis.setTerDiagnosisUpdateTime(new Date());
        diagnosisService.updateTerStatus(diagnosis);

        List<String> sensorTypeList = new ArrayList<>();
        List<String> sensorTypeExtendList = new ArrayList<>();

        for (int i = 6; i < data.size(); i++) {
            String key = data.get(i);
            if (SENSOR_TYPE_MAP.containsKey(key)) {
                sensorTypeList.add(SENSOR_TYPE_MAP.get(key));
            }
            if (SENSOR_TYPE_EXTEND_MAP.containsKey(key)) {
                sensorTypeExtendList.add(SENSOR_TYPE_EXTEND_MAP.get(key));
            }
        }

        sensorTypeList.add("light");
        sensorTypeList.add("com");

        diagnosisService.updateSensorStatus(message.getSn(), sensorTypeList);
        diagnosisService.updateSensorExtendStatus(message.getSn(), sensorTypeExtendList);
    }

    private TerInfo buildNewTer(String sn) {
        TerInfo terInfo = new TerInfo();
        terInfo.setSn(sn);
        terInfo.setCreatedTime(new Date());
        terInfo.setUpdatedTime(new Date());
        terInfo.setDelFlag("0");
        return terInfo;
    }

    private String genTerStatus(IotMessage message) {
        List<TerSensorStatus> terSensorStatusList = terSensorStatusService.selectTerSensorStatusByTerSn(message.getSn());
        terSensorStatusList = terSensorStatusList.stream()
                .filter(s -> StringUtils.equalsIgnoreCase(s.getSensorStatus(), "alarm"))
                .collect(Collectors.toList());  // ← fixed

        return CollectionUtils.isEmpty(terSensorStatusList) ? "online" : "alarm";
    }

    private String genSensorStatus(IotMessage message) {
        String type = message.getType();
        String data = message.getData();

        if ("park".equals(type) &&
            !StringUtils.equalsIgnoreCase("0", data) &&
            !StringUtils.equalsIgnoreCase("1", data)) {
            throw new ServiceException("发送数据不合法，处理失败");
        }

        message.setData(data);
        String status = "online";

        if ("post".equalsIgnoreCase(type) &&
            StringUtils.isNotEmpty(data) &&
            !"0".equalsIgnoreCase(data)) {
            status = "alarm";
        } else if ("patrol".equalsIgnoreCase(type)) {
            if (!"0".equalsIgnoreCase(data)) {
                buildingPatrolLogService.handlePatrolDetailData(message.getGateway(), message.getSn());
            }
        } else if ("speed".equalsIgnoreCase(type)) {
            if (!"0".equalsIgnoreCase(data)) {
                status = "alarm";
                try {
                    if (!"1".equalsIgnoreCase(data)) {
                        String speedMacList = configService.selectConfigByKey("speed.mac.list");
                        data = extractAndConvertBinaryParts(data, speedMacList);
                        message.setData(data);
                    }
                } catch (Exception e) {
                    log.error("解析data数据错误", e);
                }
            }
        } else if ("1".equalsIgnoreCase(data)) {
            status = "alarm";
        }

        return status;
    }

    public static String extractAndConvertBinaryParts(String data, String speedMacList) {
        String binaryString = Integer.toBinaryString(Integer.parseInt(data));

        while (binaryString.length() < 16) {
            binaryString = "0" + binaryString;
        }

        String firstEight = binaryString.substring(0, 8);
        String lastEight = binaryString.substring(8, 16);

        int firstPartDecimal = Integer.parseInt(firstEight, 2);
        int lastPartDecimal = Integer.parseInt(lastEight, 2);

        String[] macList = speedMacList.split(",");

        return macList[firstPartDecimal - 1].trim() + "," + lastPartDecimal;
    }
}