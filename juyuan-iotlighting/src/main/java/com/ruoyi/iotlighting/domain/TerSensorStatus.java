package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.iotlighting.adapter.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TerSensorStatus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "安装日期", dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    @Excel(name = "产品独立代码", width = 20.0)
    private String upc;

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

    @Excel(name = "模组", dictType = "ter_sensor_type")
    private String sensorType;

    private String terSn;

    private Integer moduleNumber;

    private String sensorStatus;

    @Excel(name = "设定值", handler = SensorConfigExcelHandlerAdapter.class, width = 45.0)
    private String sensorConfig;

    private String data;

    private String dealFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long floorId;

    private String gateway;

    private String checkSensorType;

    private List<String> terSnList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeEnd;

    // ==================== Getters ====================

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getUpc() {
        return upc;
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

    public String getSensorType() {
        return sensorType;
    }

    public String getTerSn() {
        return terSn;
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public String getSensorConfig() {
        return sensorConfig;
    }

    public String getData() {
        return data;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public String getGateway() {
        return gateway;
    }

    public String getCheckSensorType() {
        return checkSensorType;
    }

    public List<String> getTerSnList() {
        return terSnList;
    }

    public Date getCreatedTimeStart() {
        return createdTimeStart;
    }

    public Date getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    // ==================== Setters ====================

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpc(String upc) {
        this.upc = upc;
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

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setTerSn(String terSn) {
        this.terSn = terSn;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public void setSensorConfig(String sensorConfig) {
        this.sensorConfig = sensorConfig;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public void setCheckSensorType(String checkSensorType) {
        this.checkSensorType = checkSensorType;
    }

    public void setTerSnList(List<String> terSnList) {
        this.terSnList = terSnList;
    }

    public void setCreatedTimeStart(Date createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public void setCreatedTimeEnd(Date createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }
}