package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class TerGateway extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Excel(name = "SN码")
    private String sn;

    private String name;

    @Excel(name = "创建人")
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @Excel(name = "更新人")
    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30.0, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String delFlag;

    @Excel(name = "所属部门")
    private Long deptId;

    private String buildingName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long buildingId;

    private String wifiType;

    // ==================== Getters ====================

    public Long getId() {
        return id;
    }

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
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

    public String getBuildingName() {
        return buildingName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public String getWifiType() {
        return wifiType;
    }

    // ==================== Setters ====================

    public void setId(Long id) {
        this.id = id;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public void setWifiType(String wifiType) {
        this.wifiType = wifiType;
    }

    // ==================== equals / hashCode / toString (optional) ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerGateway that = (TerGateway) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (deptId != null ? !deptId.equals(that.deptId) : that.deptId != null) return false;
        if (buildingId != null ? !buildingId.equals(that.buildingId) : that.buildingId != null) return false;
        if (sn != null ? !sn.equals(that.sn) : that.sn != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (updatedBy != null ? !updatedBy.equals(that.updatedBy) : that.updatedBy != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (buildingName != null ? !buildingName.equals(that.buildingName) : that.buildingName != null) return false;
        return wifiType != null ? wifiType.equals(that.wifiType) : that.wifiType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        result = 31 * result + (sn != null ? sn.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (buildingName != null ? buildingName.hashCode() : 0);
        result = 31 * result + (wifiType != null ? wifiType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerGateway{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", delFlag='" + delFlag + '\'' +
                ", deptId=" + deptId +
                ", buildingName='" + buildingName + '\'' +
                ", buildingId=" + buildingId +
                ", wifiType='" + wifiType + '\'' +
                '}';
    }
}