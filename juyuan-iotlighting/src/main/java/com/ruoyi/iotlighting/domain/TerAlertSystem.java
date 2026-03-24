package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class TerAlertSystem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Date createTime;

    @Excel(name = "日期", dateFormat = "yyyy-MM-dd")
    private Date createTimeDate;

    @Excel(name = "时间", dateFormat = "HH:mm")
    private Date createTimeTime;

    private String terSn;

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

    @Excel(name = "告警类型", dictType = "ter_system_alert_type")
    private String alertType;

    private String sensorType;
    private String wifiType;
    private String wifiSsid;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long floorId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long pointId;

    private String delFlag;

    @Excel(name = "处理状态", readConverterExp = "0=未处理,1=已处理")
    private String dealFlag;

    @Excel(name = "处理时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")   // fixed pattern
    private Date dealTime;

    private Date createTimeStart;
    private Date createTimeEnd;

    private String checkAlertType;
    private String backgroundImgId;

    private Integer floorLength;
    private Integer floorWidth;
    private Integer pointX;
    private Integer pointY;

    private String gatewayName;

    // ==================== Getters ====================

    public Long getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getCreateTimeDate() {
        return createTimeDate;
    }

    public Date getCreateTimeTime() {
        return createTimeTime;
    }

    public String getTerSn() {
        return terSn;
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

    public String getAlertType() {
        return alertType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getWifiType() {
        return wifiType;
    }

    public String getWifiSsid() {
        return wifiSsid;
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

    public String getDelFlag() {
        return delFlag;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public String getCheckAlertType() {
        return checkAlertType;
    }

    public String getBackgroundImgId() {
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

    public String getGatewayName() {
        return gatewayName;
    }

    // ==================== Setters ====================

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateTimeDate(Date createTimeDate) {
        this.createTimeDate = createTimeDate;
    }

    public void setCreateTimeTime(Date createTimeTime) {
        this.createTimeTime = createTimeTime;
    }

    public void setTerSn(String terSn) {
        this.terSn = terSn;
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

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setWifiType(String wifiType) {
        this.wifiType = wifiType;
    }

    public void setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid;
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

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public void setCheckAlertType(String checkAlertType) {
        this.checkAlertType = checkAlertType;
    }

    public void setBackgroundImgId(String backgroundImgId) {
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

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    // ==================== equals / hashCode / toString (optional) ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerAlertSystem that = (TerAlertSystem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (buildingId != null ? !buildingId.equals(that.buildingId) : that.buildingId != null) return false;
        if (floorId != null ? !floorId.equals(that.floorId) : that.floorId != null) return false;
        if (pointId != null ? !pointId.equals(that.pointId) : that.pointId != null) return false;
        if (floorLength != null ? !floorLength.equals(that.floorLength) : that.floorLength != null) return false;
        if (floorWidth != null ? !floorWidth.equals(that.floorWidth) : that.floorWidth != null) return false;
        if (pointX != null ? !pointX.equals(that.pointX) : that.pointX != null) return false;
        if (pointY != null ? !pointY.equals(that.pointY) : that.pointY != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createTimeDate != null ? !createTimeDate.equals(that.createTimeDate) : that.createTimeDate != null) return false;
        if (createTimeTime != null ? !createTimeTime.equals(that.createTimeTime) : that.createTimeTime != null) return false;
        if (terSn != null ? !terSn.equals(that.terSn) : that.terSn != null) return false;
        if (buildingName != null ? !buildingName.equals(that.buildingName) : that.buildingName != null) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;
        if (floorName != null ? !floorName.equals(that.floorName) : that.floorName != null) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;
        if (zoneSub != null ? !zoneSub.equals(that.zoneSub) : that.zoneSub != null) return false;
        if (pointName != null ? !pointName.equals(that.pointName) : that.pointName != null) return false;
        if (alertType != null ? !alertType.equals(that.alertType) : that.alertType != null) return false;
        if (sensorType != null ? !sensorType.equals(that.sensorType) : that.sensorType != null) return false;
        if (wifiType != null ? !wifiType.equals(that.wifiType) : that.wifiType != null) return false;
        if (wifiSsid != null ? !wifiSsid.equals(that.wifiSsid) : that.wifiSsid != null) return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (dealFlag != null ? !dealFlag.equals(that.dealFlag) : that.dealFlag != null) return false;
        if (dealTime != null ? !dealTime.equals(that.dealTime) : that.dealTime != null) return false;
        if (createTimeStart != null ? !createTimeStart.equals(that.createTimeStart) : that.createTimeStart != null) return false;
        if (createTimeEnd != null ? !createTimeEnd.equals(that.createTimeEnd) : that.createTimeEnd != null) return false;
        if (checkAlertType != null ? !checkAlertType.equals(that.checkAlertType) : that.checkAlertType != null) return false;
        if (backgroundImgId != null ? !backgroundImgId.equals(that.backgroundImgId) : that.backgroundImgId != null) return false;
        return gatewayName != null ? gatewayName.equals(that.gatewayName) : that.gatewayName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        result = 31 * result + (floorId != null ? floorId.hashCode() : 0);
        result = 31 * result + (pointId != null ? pointId.hashCode() : 0);
        result = 31 * result + (floorLength != null ? floorLength.hashCode() : 0);
        result = 31 * result + (floorWidth != null ? floorWidth.hashCode() : 0);
        result = 31 * result + (pointX != null ? pointX.hashCode() : 0);
        result = 31 * result + (pointY != null ? pointY.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createTimeDate != null ? createTimeDate.hashCode() : 0);
        result = 31 * result + (createTimeTime != null ? createTimeTime.hashCode() : 0);
        result = 31 * result + (terSn != null ? terSn.hashCode() : 0);
        result = 31 * result + (buildingName != null ? buildingName.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + (floorName != null ? floorName.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (zoneSub != null ? zoneSub.hashCode() : 0);
        result = 31 * result + (pointName != null ? pointName.hashCode() : 0);
        result = 31 * result + (alertType != null ? alertType.hashCode() : 0);
        result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
        result = 31 * result + (wifiType != null ? wifiType.hashCode() : 0);
        result = 31 * result + (wifiSsid != null ? wifiSsid.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (dealFlag != null ? dealFlag.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        result = 31 * result + (createTimeStart != null ? createTimeStart.hashCode() : 0);
        result = 31 * result + (createTimeEnd != null ? createTimeEnd.hashCode() : 0);
        result = 31 * result + (checkAlertType != null ? checkAlertType.hashCode() : 0);
        result = 31 * result + (backgroundImgId != null ? backgroundImgId.hashCode() : 0);
        result = 31 * result + (gatewayName != null ? gatewayName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerAlertSystem{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createTimeDate=" + createTimeDate +
                ", createTimeTime=" + createTimeTime +
                ", terSn='" + terSn + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", block='" + block + '\'' +
                ", floorName='" + floorName + '\'' +
                ", zone='" + zone + '\'' +
                ", zoneSub='" + zoneSub + '\'' +
                ", pointName='" + pointName + '\'' +
                ", alertType='" + alertType + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", wifiType='" + wifiType + '\'' +
                ", wifiSsid='" + wifiSsid + '\'' +
                ", buildingId=" + buildingId +
                ", floorId=" + floorId +
                ", pointId=" + pointId +
                ", delFlag='" + delFlag + '\'' +
                ", dealFlag='" + dealFlag + '\'' +
                ", dealTime=" + dealTime +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                ", checkAlertType='" + checkAlertType + '\'' +
                ", backgroundImgId='" + backgroundImgId + '\'' +
                ", floorLength=" + floorLength +
                ", floorWidth=" + floorWidth +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                ", gatewayName='" + gatewayName + '\'' +
                '}';
    }
}