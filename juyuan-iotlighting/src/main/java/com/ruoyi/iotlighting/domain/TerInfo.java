package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TerInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "安装日期", dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    @Excel(name = "大厦名称")
    private String buildingName;

    @Excel(name = "座")
    private String block;

    @Excel(name = "楼层")
    private String floorName;

    @Excel(name = "区域")
    private String zone;

    @Excel(name = "区域(分类)")
    private String zoneSub;

    @Excel(name = "确实位置")
    private String pointName;

    @Excel(name = "LED", dictType = "ter_module_type")
    private String led;

    @Excel(name = "灯管插口1", dictType = "ter_module_type")
    private String module1;

    @Excel(name = "灯管插口2", dictType = "ter_module_type")
    private String module2;

    @Excel(name = "灯管插口3", dictType = "ter_module_type")
    private String module3;

    @Excel(name = "灯管插口4", dictType = "ter_module_type")
    private String module4;

    private String sn;
    private String upc;
    private String terStatus;

    private String createdBy;
    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String delFlag;
    private Long deptId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long floorId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long pointId;

    private Integer pointX;
    private Integer pointY;

    private String linkageStatus;
    private String gateway;
    private String gatewayName;

    private String licenseNumberOne;
    private String licenseNumberTwo;
    private String licenseNumberThree;

    private List<TerSensorStatus> terSensorStatusList;

    // ==================== Getters ====================

    public Long getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getBlock() {
        return block;
    }

    public String getFloorName() {
        return floorName;
    }

    public String getZone() {
        return zone;
    }

    public String getZoneSub() {
        return zoneSub;
    }

    public String getPointName() {
        return pointName;
    }

    public String getLed() {
        return led;
    }

    public String getModule1() {
        return module1;
    }

    public String getModule2() {
        return module2;
    }

    public String getModule3() {
        return module3;
    }

    public String getModule4() {
        return module4;
    }

    public String getSn() {
        return sn;
    }

    public String getUpc() {
        return upc;
    }

    public String getTerStatus() {
        return terStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Long getDeptId() {
        return deptId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public Long getPointId() {
        return pointId;
    }

    public Integer getPointX() {
        return pointX;
    }

    public Integer getPointY() {
        return pointY;
    }

    public String getLinkageStatus() {
        return linkageStatus;
    }

    public String getGateway() {
        return gateway;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public String getLicenseNumberOne() {
        return licenseNumberOne;
    }

    public String getLicenseNumberTwo() {
        return licenseNumberTwo;
    }

    public String getLicenseNumberThree() {
        return licenseNumberThree;
    }

    public List<TerSensorStatus> getTerSensorStatusList() {
        return terSensorStatusList;
    }

    // ==================== Setters ====================

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setZoneSub(String zoneSub) {
        this.zoneSub = zoneSub;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public void setLed(String led) {
        this.led = led;
    }

    public void setModule1(String module1) {
        this.module1 = module1;
    }

    public void setModule2(String module2) {
        this.module2 = module2;
    }

    public void setModule3(String module3) {
        this.module3 = module3;
    }

    public void setModule4(String module4) {
        this.module4 = module4;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setTerStatus(String terStatus) {
        this.terStatus = terStatus;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public void setPointX(Integer pointX) {
        this.pointX = pointX;
    }

    public void setPointY(Integer pointY) {
        this.pointY = pointY;
    }

    public void setLinkageStatus(String linkageStatus) {
        this.linkageStatus = linkageStatus;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public void setLicenseNumberOne(String licenseNumberOne) {
        this.licenseNumberOne = licenseNumberOne;
    }

    public void setLicenseNumberTwo(String licenseNumberTwo) {
        this.licenseNumberTwo = licenseNumberTwo;
    }

    public void setLicenseNumberThree(String licenseNumberThree) {
        this.licenseNumberThree = licenseNumberThree;
    }

    public void setTerSensorStatusList(List<TerSensorStatus> terSensorStatusList) {
        this.terSensorStatusList = terSensorStatusList;
    }

    // ==================== Custom business methods (kept from original) ====================

    // ==================== Custom business methods ====================

    public String buildingPayload() {
        String payload = "";

        // Fixed: ObjectUtils.isEmpty → simple null check (pointY is Integer)
        if (getPointY() != null) {
            payload = payload + "bind," + getPointX() + "," + getPointY() + "," 
                    + getFloorId() + "," + getBuildingId();
        } else {
            payload = payload + "unbind";
        }

        List<TerSensorStatus> terSensorStatusList = getTerSensorStatusList();
        String ledCode = getLedCodeFromUpc(getUpc());

        List<String> terSensorStatusArray = new ArrayList<>();
        List<String> lightConfigArray = new ArrayList<>();
        lightConfigArray.add(ledCode);

        for (TerSensorStatus terSensorStatus : terSensorStatusList) {
            String sensorType = terSensorStatus.getSensorType();
            String sensorConfig = terSensorStatus.getSensorConfig();

            if (StringUtils.isEmpty(sensorConfig)) {
                continue;
            }

            if (StringUtils.equals(sensorType, "light")) {
                JSONObject jsonObject = JSON.parseObject(sensorConfig);
                if (StringUtils.equals(jsonObject.getString("mode"), "2")) {
                    lightConfigArray.add("00:00");
                    lightConfigArray.add("00:00");
                    lightConfigArray.add(jsonObject.getJSONObject("auto").getString("lux"));
                    lightConfigArray.add(jsonObject.getJSONObject("auto").getString("wait"));
                    lightConfigArray.add(jsonObject.getJSONObject("auto").getString("high"));

                    lightConfigArray.add("00:00");
                    lightConfigArray.add("00:00");
                    lightConfigArray.add("60");
                    lightConfigArray.add("60");
                    lightConfigArray.add("60");
                    continue;
                }
                lightConfigArray.add(jsonObject.getJSONObject("day").getString("start"));
                lightConfigArray.add(jsonObject.getJSONObject("day").getString("end"));
                lightConfigArray.add(jsonObject.getJSONObject("day").getString("high"));
                lightConfigArray.add(jsonObject.getJSONObject("day").getString("wait"));
                lightConfigArray.add(jsonObject.getJSONObject("day").getString("low"));

                lightConfigArray.add(jsonObject.getJSONObject("night").getString("start"));
                lightConfigArray.add(jsonObject.getJSONObject("night").getString("end"));
                lightConfigArray.add(jsonObject.getJSONObject("night").getString("high"));
                lightConfigArray.add(jsonObject.getJSONObject("night").getString("wait"));
                lightConfigArray.add(jsonObject.getJSONObject("night").getString("low"));
                continue;
            }

            if (StringUtils.equals(sensorType, "post")) {
                terSensorStatusArray.add(sensorType);
                terSensorStatusArray.add(sensorConfig);
                terSensorStatusArray.add(StringUtils.isEmpty(this.licenseNumberOne) ? "0" : this.licenseNumberOne);
                terSensorStatusArray.add(StringUtils.isEmpty(this.licenseNumberTwo) ? "0" : this.licenseNumberTwo);
                terSensorStatusArray.add(StringUtils.isEmpty(this.licenseNumberThree) ? "0" : this.licenseNumberThree);
                continue;
            }

            if (StringUtils.equals(sensorType, "smoke") || StringUtils.equals(sensorType, "h2s")) {
                String[] sensorConfigArray = sensorConfig.split(",");
                int time = 0;
                int percent = 30;
                if (sensorConfigArray.length == 1) {
                    percent = Integer.parseInt(sensorConfigArray[0]);
                } else if (sensorConfigArray.length == 2) {
                    time = Integer.parseInt(sensorConfigArray[0]);
                    percent = Integer.parseInt(sensorConfigArray[1]);
                }

                String timeBinary = Integer.toBinaryString(time);
                while (timeBinary.length() < 8) {
                    timeBinary = "0" + timeBinary;
                }
                String percentBinary = Integer.toBinaryString(percent);
                while (percentBinary.length() < 8) {
                    percentBinary = "0" + percentBinary;
                }

                sensorConfig = String.valueOf(Integer.parseInt(timeBinary + percentBinary, 2));
                terSensorStatusArray.add(sensorType);
                terSensorStatusArray.add(sensorConfig);
                continue;
            }

            if (StringUtils.equals(sensorType, "flooding")) {
                if (StringUtils.isEmpty(sensorConfig)) {
                    sensorConfig = "0";
                }
                terSensorStatusArray.add(sensorType);
                String[] sensorConfigArray = sensorConfig.split(",");
                if (sensorConfigArray.length == 2 && StringUtils.equals(sensorConfigArray[1], "0")) {
                    terSensorStatusArray.add("0");
                    continue;
                }
                terSensorStatusArray.add(sensorConfigArray[0]);
                continue;
            }

            if (StringUtils.equals(sensorType, "park") || StringUtils.equals(sensorType, "height")) {
                terSensorStatusArray.add(sensorType);
                String[] sensorConfigArray = sensorConfig.split(",");
                if (sensorConfigArray.length == 2) {
                    terSensorStatusArray.add(sensorConfig);
                    continue;
                }
                if (sensorConfigArray.length == 3) {
                    int config = 536 - Integer.parseInt(sensorConfigArray[2]) + Integer.parseInt(sensorConfigArray[0]);
                    config = Math.max(config, 0);
                    terSensorStatusArray.add(config + "," + sensorConfigArray[1]);
                }
                continue;
            }

            terSensorStatusArray.add(sensorType);
            terSensorStatusArray.add(sensorConfig);
        }

        terSensorStatusArray.addAll(lightConfigArray);
        payload = payload + "," + StringUtils.join(terSensorStatusArray, ",");
        return payload;
    }

    private String getLedCodeFromUpc(String upc) {
        return (upc.length() > 16) ? upc.substring(2, 5) : upc.substring(0, 3);
    }

}