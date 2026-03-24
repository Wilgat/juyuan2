package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class TerBuildingPointInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "设备SN")
    private String terSn;

    @Excel(name = "点位编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pointId;

    @Excel(name = "创建人")
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    private String gateway;

    // ==================== Getters ====================

    public String getTerSn() {
        return terSn;
    }

    public Long getPointId() {
        return pointId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getGateway() {
        return gateway;
    }

    // ==================== Setters ====================

    public void setTerSn(String terSn) {
        this.terSn = terSn;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    // ==================== equals / hashCode / toString (optional) ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerBuildingPointInfo that = (TerBuildingPointInfo) o;

        if (pointId != null ? !pointId.equals(that.pointId) : that.pointId != null) return false;
        if (terSn != null ? !terSn.equals(that.terSn) : that.terSn != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        return gateway != null ? gateway.equals(that.gateway) : that.gateway == null;
    }

    @Override
    public int hashCode() {
        int result = pointId != null ? pointId.hashCode() : 0;
        result = 31 * result + (terSn != null ? terSn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (gateway != null ? gateway.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerBuildingPointInfo{" +
                "terSn='" + terSn + '\'' +
                ", pointId=" + pointId +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", gateway='" + gateway + '\'' +
                '}';
    }
}