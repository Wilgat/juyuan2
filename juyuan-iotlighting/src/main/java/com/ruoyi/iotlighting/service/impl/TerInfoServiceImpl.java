package com.ruoyi.iotlighting.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
import com.ruoyi.iotlighting.domain.TerInfo;
import com.ruoyi.iotlighting.domain.TerSensorStatus;
import com.ruoyi.iotlighting.mapper.BuildingPatrolMapper;
import com.ruoyi.iotlighting.mapper.TerInfoMapper;
import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
import com.ruoyi.iotlighting.service.ITerBuildingPointInfoService;
import com.ruoyi.iotlighting.service.ITerInfoService;
import com.ruoyi.iotlighting.service.ITerSensorStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TerInfoServiceImpl implements ITerInfoService {

    private static final Logger log = LoggerFactory.getLogger(TerInfoServiceImpl.class);

    @Autowired
    private TerInfoMapper terInfoMapper;

    @Autowired
    private IBuildingFloorInfoService buildingFloorInfoService;

    @Autowired
    private ITerSensorStatusService terSensorStatusService;

    @Autowired
    private ISysMqttService sysMqttService;

    @Lazy
    @Autowired
    private ITerBuildingPointInfoService terBuildingPointInfoService;

    @Autowired
    private BuildingPatrolMapper buildingPatrolMapper;

    public TerInfo selectTerInfoById(Long id) {
        return this.terInfoMapper.selectTerInfoById(id);
    }

    @DataScope(deptAlias = "d")
    public List<TerInfo> selectTerInfoList(TerInfo terInfo) {
        return this.terInfoMapper.selectTerInfoStatusList(terInfo);
    }

    @DataScope(deptAlias = "d")
    public List<TerInfo> selectTerInfoListAll(TerInfo terInfo) {
        return this.terInfoMapper.selectTerInfoStatusListAll(terInfo);
    }

    public List<TerInfo> selectTerInfoListNotLogin(TerInfo terInfo) {
        return this.terInfoMapper.selectTerInfoStatusListNotLogin(terInfo);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int insertTerInfo(TerInfo terInfo) {
        TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(terInfo.getSn());
        if (!ObjectUtils.isEmpty(terInfoDb)) {
            throw new ServiceException(MessageUtils.message("terInfo.sn.error", new Object[0]));
        }
        TerInfo queryParam = new TerInfo();
        queryParam.setSn(terInfo.getUpc());
        List<TerInfo> terInfoList = this.terInfoMapper.selectTerInfoBySnOrUpc(queryParam);
        if (!ObjectUtils.isEmpty(terInfoList)) {
            throw new ServiceException(MessageUtils.message("terInfo.upc.error", new Object[0]));
        }

        terInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
        terInfo.setTerStatus("offline");
        terInfo.setDelFlag("0");
        terInfo.setCreatedTime(new Date());
        terInfo.setUpdatedTime(new Date());

        List<TerSensorStatus> terSensorStatusList = terInfo.getTerSensorStatusList();
        for (TerSensorStatus terSensorStatus : terSensorStatusList) {
            terSensorStatus.setTerSn(terInfo.getSn());
            terSensorStatus.setData("——");
            terSensorStatus.setUpdatedTime(new Date());
            terSensorStatus.setSensorStatus("offline");
            this.terSensorStatusService.insertTerSensorStatus(terSensorStatus);
        }

        if (StringUtils.isNotEmpty(terInfo.getGateway())) {
            SysMqttBo mqttBo = new SysMqttBo();
            mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/add/" + terInfo.getSn());
            mqttBo.setQos(Integer.valueOf(1));
            mqttBo.setRetained(false);
            mqttBo.setData(terInfo.buildingPayload());
            this.sysMqttService.sendMsgToTopic(mqttBo);
        }
        return this.terInfoMapper.insertTerInfo(terInfo);
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateTerInfo(TerInfo terInfo) {
        List<TerInfo> terInfoList = this.terInfoMapper.selectTerInfoBySnOrUpc(terInfo);
        if (!ObjectUtils.isEmpty(terInfoList)) {
            for (TerInfo item : terInfoList) {
                if (item.getId().longValue() != terInfo.getId().longValue()) {
                    throw new ServiceException(MessageUtils.message("terInfo.sn.error", new Object[0]));
                }
            }
        }

        TerInfo terInfoOld = this.terInfoMapper.selectTerInfoById(terInfo.getId());
        String oldSn = terInfoOld.getSn();

        List<TerSensorStatus> terSensorStatusList = terInfo.getTerSensorStatusList();

        this.terSensorStatusService.deleteTerSensorStatusByTerSn(oldSn);
        this.terSensorStatusService.deleteTerLightStatusByTerSn(oldSn);
        boolean patrolFlag = false;
        for (TerSensorStatus terSensorStatus : terSensorStatusList) {
            terSensorStatus.setTerSn(terInfo.getSn());
            terSensorStatus.setData("——");
            terSensorStatus.setUpdatedTime(new Date());
            terSensorStatus.setSensorStatus("offline");
            if (StringUtils.isEmpty(terSensorStatus.getSensorConfig())) {
                terSensorStatus.setSensorConfig("0");
            }
            this.terSensorStatusService.insertTerSensorStatus(terSensorStatus);

            if (StringUtils.equals(terSensorStatus.getSensorType(), "patrol")) {
                patrolFlag = true;
            }
        }
        terInfo.setUpdatedTime(new Date());

        if (!patrolFlag) {
            this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(oldSn);
        }

        TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(oldSn);
        int count = this.terInfoMapper.updateTerInfo(terInfo);

        if (StringUtils.isNotEmpty(terInfo.getGateway())) {
            terInfo = this.terInfoMapper.selectTerInfoBySn(terInfo.getSn());

            if (!StringUtils.equals(terInfoDb.getGateway(), terInfo.getGateway())) {
                SysMqttBo mqttBo = new SysMqttBo();
                mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
                mqttBo.setQos(Integer.valueOf(1));
                mqttBo.setRetained(true);
                mqttBo.setData(terInfo.buildingPayload());
                this.sysMqttService.sendMsgToTopic(mqttBo);
                log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));

                delRetainedTopic(terInfoDb);
            } else {
                SysMqttBo mqttBo = new SysMqttBo();
                mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
                mqttBo.setQos(Integer.valueOf(1));
                mqttBo.setRetained(true);
                mqttBo.setData(terInfo.buildingPayload());
                this.sysMqttService.sendMsgToTopic(mqttBo);
                log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
            }
        } else if (StringUtils.isNotEmpty(terInfoDb.getGateway())) {
            delRetainedTopic(terInfoDb);
        }
        return count;
    }

    public int updateTerStatus(TerInfo terInfo) {
        return this.terInfoMapper.updateTerInfo(terInfo);
    }

    public int updateTerOtaByIds(Long[] ids) {
        if (ObjectUtils.isEmpty((Object[]) ids)) {
            throw new ServiceException("设备id为空，升级失败");
        }
        ClassPathResource classPathResource = new ClassPathResource("AU.OPU");
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
            Map<Integer, String> fileCache = new LinkedHashMap<>();

            byte[] tempbytes = new byte[256];
            int byteread = 0;
            int index = 0;

            while ((byteread = inputStream.read(tempbytes)) != -1) {
                index++;
                StringBuilder hexString = new StringBuilder();
                for (byte b : tempbytes) {
                    hexString.append(String.format("%02X ", b));
                }
                fileCache.put(index, hexString.toString());
            }

            for (Long id : ids) {
                TerInfo terInfo = this.terInfoMapper.selectTerInfoById(id);
                SysMqttBo mqttBo = new SysMqttBo();
                mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
                mqttBo.setQos(2);
                mqttBo.setRetained(false);
                mqttBo.setData("begin," + fileCache.size());
                this.sysMqttService.sendMsgToTopic(mqttBo);

                for (Integer key : fileCache.keySet()) {
                    mqttBo = new SysMqttBo();
                    mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
                    mqttBo.setQos(2);
                    mqttBo.setRetained(false);
                    mqttBo.setData(key + "," + fileCache.get(key));
                    this.sysMqttService.sendMsgToTopic(mqttBo);
                    Thread.sleep(100L);
                }

                mqttBo = new SysMqttBo();
                mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
                mqttBo.setQos(2);
                mqttBo.setRetained(false);
                mqttBo.setData("end");
                this.sysMqttService.sendMsgToTopic(mqttBo);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }
        }
        return 1;
    }

    public int unbind(String terSn) {
        TerInfo terInfo = this.terInfoMapper.selectTerInfoBySn(terSn);
        if (ObjectUtils.isEmpty(terInfo)) {
            throw new ServiceException("设备不存在");
        }
        log.info("解绑设备信息：{}", JSONObject.toJSONString(terInfo));

        this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(terInfo.getSn());
        this.terBuildingPointInfoService.deleteTerBuildingPointInfoByTerSns(new String[]{terInfo.getSn()});

        if (StringUtils.isNotEmpty(terInfo.getGateway())) {
            delRetainedTopic(terInfo);
        }
        return 1;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int deleteTerInfoByIds(Long[] ids) {
        for (Long id : ids) {
            TerInfo terInfo = this.terInfoMapper.selectTerInfoById(id);
            if (!ObjectUtils.isEmpty(terInfo)) {
                if (ObjectUtil.isNotNull(terInfo.getPointId())) {
                    throw new ServiceException("灯管已绑定点位，请解绑后再删除");
                }
                log.info("删除设备信息：{}", JSONObject.toJSONString(terInfo));

                this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(terInfo.getSn());
                this.terSensorStatusService.deleteTerSensorStatusByTerSn(terInfo.getSn());
                this.terBuildingPointInfoService.deleteTerBuildingPointInfoByTerSns(new String[]{terInfo.getSn()});
                this.terInfoMapper.deleteTerInfoById(id);
                this.terSensorStatusService.deleteTerLightStatusByTerSn(terInfo.getSn());

                if (StringUtils.isNotEmpty(terInfo.getGateway())) {
                    delRetainedTopic(terInfo);
                }
            }
        }
        return 1;
    }

    private void delRetainedTopic(TerInfo terInfo) {
        SysMqttBo mqttBo = new SysMqttBo();
        mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/delete/" + terInfo.getSn());
        mqttBo.setQos(1);
        mqttBo.setRetained(false);
        mqttBo.setData("1");
        this.sysMqttService.sendMsgToTopic(mqttBo);
        log.info("发送消息:{}", JSONObject.toJSONString(mqttBo));

        mqttBo = new SysMqttBo();
        mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
        mqttBo.setQos(1);
        mqttBo.setRetained(true);
        mqttBo.setData("");
        this.sysMqttService.sendMsgToTopic(mqttBo);
        log.info("发送消息:{}", JSONObject.toJSONString(mqttBo));

        mqttBo = new SysMqttBo();
        mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightingstatus/light/" + terInfo.getSn());
        mqttBo.setQos(1);
        mqttBo.setRetained(true);
        mqttBo.setData("");
        this.sysMqttService.sendMsgToTopic(mqttBo);
        log.info("发送消息:{}", JSONObject.toJSONString(mqttBo));

        mqttBo = new SysMqttBo();
        mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightingstatus/patrol/" + terInfo.getSn());
        mqttBo.setQos(1);
        mqttBo.setRetained(true);
        mqttBo.setData("");
        this.sysMqttService.sendMsgToTopic(mqttBo);
        log.info("发送消息:{}", JSONObject.toJSONString(mqttBo));
    }

    public int deleteTerInfoById(Long id) {
        return this.terInfoMapper.deleteTerInfoById(id);
    }

    public TerInfo selectTerInfoBySn(String terSn) {
        return this.terInfoMapper.selectTerInfoBySn(terSn);
    }

    @DataScope(deptAlias = "d")
    public List<TerInfo> selectTerInfoBySnOrUpc(String key) {
        TerInfo terInfoParam = new TerInfo();
        terInfoParam.setSn(key);
        return this.terInfoMapper.selectTerInfoBySnOrUpc(terInfoParam);
    }

    public BuildingTerStatusVo selectTerCount() {
        BuildingFloorInfo buildingFloorInfo = new BuildingFloorInfo();
        List<BuildingTerStatusVo> buildingTerStatusVoList = this.buildingFloorInfoService.selectBuildingFloorInfoListStatus(buildingFloorInfo);
        BuildingTerStatusVo buildingTerStatusVo = new BuildingTerStatusVo();
        for (BuildingTerStatusVo item : buildingTerStatusVoList) {
            buildingTerStatusVo.setTerCount(buildingTerStatusVo.getTerCount() + item.getTerCount());

            buildingTerStatusVo.setSensorCountOnlineMovement(buildingTerStatusVo.getSensorCountOnlineMovement() + item.getSensorCountOnlineMovement());
            buildingTerStatusVo.setSensorCountAlarmMovement(buildingTerStatusVo.getSensorCountAlarmMovement() + item.getSensorCountAlarmMovement());

            buildingTerStatusVo.setSensorCountOnlineLight(buildingTerStatusVo.getSensorCountOnlineLight() + item.getSensorCountOnlineLight());
            buildingTerStatusVo.setSensorCountAlarmLight(buildingTerStatusVo.getSensorCountAlarmLight() + item.getSensorCountAlarmLight());

            buildingTerStatusVo.setSensorCountOnlinePost(buildingTerStatusVo.getSensorCountOnlinePost() + item.getSensorCountOnlinePost());
            buildingTerStatusVo.setSensorCountAlarmPost(buildingTerStatusVo.getSensorCountAlarmPost() + item.getSensorCountAlarmPost());

            buildingTerStatusVo.setSensorCountOnlineSmoking(buildingTerStatusVo.getSensorCountOnlineSmoking() + item.getSensorCountOnlineSmoking());
            buildingTerStatusVo.setSensorCountAlarmSmoking(buildingTerStatusVo.getSensorCountAlarmSmoking() + item.getSensorCountAlarmSmoking());

            buildingTerStatusVo.setSensorCountOnlineTemperature(buildingTerStatusVo.getSensorCountOnlineTemperature() + item.getSensorCountOnlineTemperature());
            buildingTerStatusVo.setSensorCountAlarmTemperature(buildingTerStatusVo.getSensorCountAlarmTemperature() + item.getSensorCountAlarmTemperature());

            buildingTerStatusVo.setSensorCountOnlineWater(buildingTerStatusVo.getSensorCountOnlineWater() + item.getSensorCountOnlineWater());
            buildingTerStatusVo.setSensorCountAlarmWater(buildingTerStatusVo.getSensorCountAlarmWater() + item.getSensorCountAlarmWater());
        }
        return buildingTerStatusVo;
    }
}