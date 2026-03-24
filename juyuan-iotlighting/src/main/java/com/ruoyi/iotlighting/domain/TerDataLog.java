package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class TerDataLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Excel(name = "设备SN")
    private String terSn;

    @Excel(name = "传感器类型")
    private String sensorType;

    private String sensorStatus;

    @Excel(name = "数据")
    private String data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")   // Fixed: was HH:mm:dd
    @Excel(name = "创建时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String delFlag;

    private String floorName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long floorId;

    private Integer pointX;
    private Integer pointY;

    private String pointName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long gateway;

    private String gatewayName;

    // ==================== Getters ====================

    public Long getId() {
        return id;
    }

    public String getTerSn() {
        return terSn;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public String getData() {
        return data;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getFloorName() {
        return floorName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public Integer getPointX() {
        return pointX;
    }

    public Integer getPointY() {
        return pointY;
    }

    public String getPointName() {
        return pointName;
    }

    public Long getGateway() {
        return gateway;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    // ==================== Setters ====================

    public void setId(Long id) {
        this.id = id;
    }

    public void setTerSn(String terSn) {
        this.terSn = terSn;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public void setPointX(Integer pointX) {
        this.pointX = pointX;
    }

    public void setPointY(Integer pointY) {
        this.pointY = pointY;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public void setGateway(Long gateway) {
        this.gateway = gateway;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    // ==================== equals / hashCode / toString (optional) ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerDataLog that = (TerDataLog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (buildingId != null ? !buildingId.equals(that.buildingId) : that.buildingId != null) return false;
        if (floorId != null ? !floorId.equals(that.floorId) : that.floorId != null) return false;
        if (pointX != null ? !pointX.equals(that.pointX) : that.pointX != null) return false;
        if (pointY != null ? !pointY.equals(that.pointY) : that.pointY != null) return false;
        if (gateway != null ? !gateway.equals(that.gateway) : that.gateway != null) return false;
        if (terSn != null ? !terSn.equals(that.terSn) : that.terSn != null) return false;
        if (sensorType != null ? !sensorType.equals(that.sensorType) : that.sensorType != null) return false;
        if (sensorStatus != null ? !sensorStatus.equals(that.sensorStatus) : that.sensorStatus != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (floorName != null ? !floorName.equals(that.floorName) : that.floorName != null) return false;
        if (pointName != null ? !pointName.equals(that.pointName) : that.pointName != null) return false;
        return gatewayName != null ? gatewayName.equals(that.gatewayName) : that.gatewayName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        result = 31 * result + (floorId != null ? floorId.hashCode() : 0);
        result = 31 * result + (pointX != null ? pointX.hashCode() : 0);
        result = 31 * result + (pointY != null ? pointY.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        result = 31 * result + (terSn != null ? terSn.hashCode() : 0);
        result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
        result = 31 * result + (sensorStatus != null ? sensorStatus.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (floorName != null ? floorName.hashCode() : 0);
        result = 31 * result + (pointName != null ? pointName.hashCode() : 0);
        result = 31 * result + (gatewayName != null ? gatewayName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerDataLog{" +
                "id=" + id +
                ", terSn='" + terSn + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", sensorStatus='" + sensorStatus + '\'' +
                ", data='" + data + '\'' +
                ", createdTime=" + createdTime +
                ", delFlag='" + delFlag + '\'' +
                ", floorName='" + floorName + '\'' +
                ", buildingId=" + buildingId +
                ", floorId=" + floorId +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                ", pointName='" + pointName + '\'' +
                ", gateway=" + gateway +
                ", gatewayName='" + gatewayName + '\'' +
                '}';
    }
}