package com.ruoyi.iotlighting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;  // ← FIXED: this was missing
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Arrays;
import java.util.Date;

/**
 * OTA (Over-The-Air) firmware/info entity
 */
public class OtaInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Excel(name = "文件名称")
    private String name;

    @Excel(name = "文件内容")
    private Byte[] data;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    private String delFlag;

    @Excel(name = "所属部门")
    private Long deptId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    // equals, hashCode, toString (cleaned up from decompiler output)
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof OtaInfo)) return false;
        OtaInfo other = (OtaInfo) o;
        if (!other.canEqual(this)) return false;

        if (!java.util.Objects.equals(id, other.id)) return false;
        if (!java.util.Objects.equals(deptId, other.deptId)) return false;
        if (!java.util.Objects.equals(name, other.name)) return false;
        if (!Arrays.deepEquals(data, other.data)) return false;
        if (!java.util.Objects.equals(createdBy, other.createdBy)) return false;
        if (!java.util.Objects.equals(createdTime, other.createdTime)) return false;
        if (!java.util.Objects.equals(updatedBy, other.updatedBy)) return false;
        if (!java.util.Objects.equals(updatedTime, other.updatedTime)) return false;
        return java.util.Objects.equals(delFlag, other.delFlag);
    }

    protected boolean canEqual(Object other) {
        return other instanceof OtaInfo;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 59 * result + java.util.Objects.hashCode(id);
        result = 59 * result + java.util.Objects.hashCode(deptId);
        result = 59 * result + java.util.Objects.hashCode(name);
        result = 59 * result + Arrays.deepHashCode(data);
        result = 59 * result + java.util.Objects.hashCode(createdBy);
        result = 59 * result + java.util.Objects.hashCode(createdTime);
        result = 59 * result + java.util.Objects.hashCode(updatedBy);
        result = 59 * result + java.util.Objects.hashCode(updatedTime);
        result = 59 * result + java.util.Objects.hashCode(delFlag);
        return result;
    }

    @Override
    public String toString() {
        return "OtaInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", delFlag='" + delFlag + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}