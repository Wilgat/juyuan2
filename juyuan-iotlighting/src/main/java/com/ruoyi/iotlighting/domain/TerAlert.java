package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;

public class TerAlert extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")   // fixed: you had HH:mm:dd
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeEnd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日期", dateFormat = "yyyy-MM-dd")
    private Date createdTimeDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间", dateFormat = "HH:mm")
    private Date createdTimeTime;

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

    @Excel(name = "传感器类型", dictType = "ter_sensor_type")
    private String sensorType;

    @Excel(name = "处理状态", readConverterExp = "0=未处理,1=已处理")
    private String dealFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime;

    private String terSn;
    private String sensorStatus;
    private String data;
    private String delFlag;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long floorId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long backgroundImgId;

    private Integer floorLength;
    private Integer floorWidth;
    private Integer pointX;
    private Integer pointY;

    private String checkSensorType;
    private String[] alertSensorType;

    // ==================== Getters ====================

    public Long getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getCreatedTimeStart() {
        return createdTimeStart;
    }

    public Date getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public Date getCreatedTimeDate() {
        return createdTimeDate;
    }

    public Date getCreatedTimeTime() {
        return createdTimeTime;
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

    public String getDealFlag() {
        return dealFlag;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public String getTerSn() {
        return terSn;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public String getData() {
        return data;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public Long getBackgroundImgId() {
        return backgroundImgId;
    }

    public Integer getFloorLength() {
        return floorLength;
    }

    public Integer getFloorWidth() {
        return floorWidth;
    }

    public Integer getPointX() {
        return pointX;
    }

    public Integer getPointY() {
        return pointY;
    }

    public String getCheckSensorType() {
        return checkSensorType;
    }

    public String[] getAlertSensorType() {
        return alertSensorType;
    }

    // ==================== Setters ====================

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setCreatedTimeStart(Date createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public void setCreatedTimeEnd(Date createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public void setCreatedTimeDate(Date createdTimeDate) {
        this.createdTimeDate = createdTimeDate;
    }

    public void setCreatedTimeTime(Date createdTimeTime) {
        this.createdTimeTime = createdTimeTime;
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

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public void setTerSn(String terSn) {
        this.terSn = terSn;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public void setBackgroundImgId(Long backgroundImgId) {
        this.backgroundImgId = backgroundImgId;
    }

    public void setFloorLength(Integer floorLength) {
        this.floorLength = floorLength;
    }

    public void setFloorWidth(Integer floorWidth) {
        this.floorWidth = floorWidth;
    }

    public void setPointX(Integer pointX) {
        this.pointX = pointX;
    }

    public void setPointY(Integer pointY) {
        this.pointY = pointY;
    }

    public void setCheckSensorType(String checkSensorType) {
        this.checkSensorType = checkSensorType;
    }

    public void setAlertSensorType(String[] alertSensorType) {
        this.alertSensorType = alertSensorType;
    }

    // ==================== equals / hashCode / toString ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerAlert terAlert = (TerAlert) o;

        if (id != null ? !id.equals(terAlert.id) : terAlert.id != null) return false;
        if (buildingId != null ? !buildingId.equals(terAlert.buildingId) : terAlert.buildingId != null) return false;
        if (floorId != null ? !floorId.equals(terAlert.floorId) : terAlert.floorId != null) return false;
        if (backgroundImgId != null ? !backgroundImgId.equals(terAlert.backgroundImgId) : terAlert.backgroundImgId != null) return false;
        if (floorLength != null ? !floorLength.equals(terAlert.floorLength) : terAlert.floorLength != null) return false;
        if (floorWidth != null ? !floorWidth.equals(terAlert.floorWidth) : terAlert.floorWidth != null) return false;
        if (pointX != null ? !pointX.equals(terAlert.pointX) : terAlert.pointX != null) return false;
        if (pointY != null ? !pointY.equals(terAlert.pointY) : terAlert.pointY != null) return false;
        if (createdTime != null ? !createdTime.equals(terAlert.createdTime) : terAlert.createdTime != null) return false;
        if (createdTimeStart != null ? !createdTimeStart.equals(terAlert.createdTimeStart) : terAlert.createdTimeStart != null) return false;
        if (createdTimeEnd != null ? !createdTimeEnd.equals(terAlert.createdTimeEnd) : terAlert.createdTimeEnd != null) return false;
        if (createdTimeDate != null ? !createdTimeDate.equals(terAlert.createdTimeDate) : terAlert.createdTimeDate != null) return false;
        if (createdTimeTime != null ? !createdTimeTime.equals(terAlert.createdTimeTime) : terAlert.createdTimeTime != null) return false;
        if (buildingName != null ? !buildingName.equals(terAlert.buildingName) : terAlert.buildingName != null) return false;
        if (block != null ? !block.equals(terAlert.block) : terAlert.block != null) return false;
        if (floorName != null ? !floorName.equals(terAlert.floorName) : terAlert.floorName != null) return false;
        if (zone != null ? !zone.equals(terAlert.zone) : terAlert.zone != null) return false;
        if (zoneSub != null ? !zoneSub.equals(terAlert.zoneSub) : terAlert.zoneSub != null) return false;
        if (pointName != null ? !pointName.equals(terAlert.pointName) : terAlert.pointName != null) return false;
        if (sensorType != null ? !sensorType.equals(terAlert.sensorType) : terAlert.sensorType != null) return false;
        if (dealFlag != null ? !dealFlag.equals(terAlert.dealFlag) : terAlert.dealFlag != null) return false;
        if (dealTime != null ? !dealTime.equals(terAlert.dealTime) : terAlert.dealTime != null) return false;
        if (terSn != null ? !terSn.equals(terAlert.terSn) : terAlert.terSn != null) return false;
        if (sensorStatus != null ? !sensorStatus.equals(terAlert.sensorStatus) : terAlert.sensorStatus != null) return false;
        if (data != null ? !data.equals(terAlert.data) : terAlert.data != null) return false;
        if (delFlag != null ? !delFlag.equals(terAlert.delFlag) : terAlert.delFlag != null) return false;
        if (checkSensorType != null ? !checkSensorType.equals(terAlert.checkSensorType) : terAlert.checkSensorType != null) return false;
        return Arrays.deepEquals(alertSensorType, terAlert.alertSensorType);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        result = 31 * result + (floorId != null ? floorId.hashCode() : 0);
        result = 31 * result + (backgroundImgId != null ? backgroundImgId.hashCode() : 0);
        result = 31 * result + (floorLength != null ? floorLength.hashCode() : 0);
        result = 31 * result + (floorWidth != null ? floorWidth.hashCode() : 0);
        result = 31 * result + (pointX != null ? pointX.hashCode() : 0);
        result = 31 * result + (pointY != null ? pointY.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (createdTimeStart != null ? createdTimeStart.hashCode() : 0);
        result = 31 * result + (createdTimeEnd != null ? createdTimeEnd.hashCode() : 0);
        result = 31 * result + (createdTimeDate != null ? createdTimeDate.hashCode() : 0);
        result = 31 * result + (createdTimeTime != null ? createdTimeTime.hashCode() : 0);
        result = 31 * result + (buildingName != null ? buildingName.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + (floorName != null ? floorName.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (zoneSub != null ? zoneSub.hashCode() : 0);
        result = 31 * result + (pointName != null ? pointName.hashCode() : 0);
        result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
        result = 31 * result + (dealFlag != null ? dealFlag.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        result = 31 * result + (terSn != null ? terSn.hashCode() : 0);
        result = 31 * result + (sensorStatus != null ? sensorStatus.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (checkSensorType != null ? checkSensorType.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(alertSensorType);
        return result;
    }

    @Override
    public String toString() {
        return "TerAlert{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", createdTimeStart=" + createdTimeStart +
                ", createdTimeEnd=" + createdTimeEnd +
                ", createdTimeDate=" + createdTimeDate +
                ", createdTimeTime=" + createdTimeTime +
                ", buildingName='" + buildingName + '\'' +
                ", block='" + block + '\'' +
                ", floorName='" + floorName + '\'' +
                ", zone='" + zone + '\'' +
                ", zoneSub='" + zoneSub + '\'' +
                ", pointName='" + pointName + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", dealFlag='" + dealFlag + '\'' +
                ", dealTime=" + dealTime +
                ", terSn='" + terSn + '\'' +
                ", sensorStatus='" + sensorStatus + '\'' +
                ", data='" + data + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", buildingId=" + buildingId +
                ", floorId=" + floorId +
                ", backgroundImgId=" + backgroundImgId +
                ", floorLength=" + floorLength +
                ", floorWidth=" + floorWidth +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                ", checkSensorType='" + checkSensorType + '\'' +
                ", alertSensorType=" + Arrays.toString(alertSensorType) +
                '}';
    }
}