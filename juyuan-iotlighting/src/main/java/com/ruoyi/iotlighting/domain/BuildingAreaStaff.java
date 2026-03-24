package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingAreaStaff extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "区域名称")
  private String areaName;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  private String areaType;

  @Excel(name = "用户表id")
  private Long userId;

  @Excel(name = "员工编号")
  private String staffNumber;

  @Excel(name = "员工职位：lifeguard救生员，patrol 巡检人员")
  private String staffPosition;

  @Excel(name = "工牌号码")
  private String licenseNumber;

  private String nickName;
  private Long timeConfig;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setAreaName(String areaName) { this.areaName = areaName; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setAreaType(String areaType) { this.areaType = areaType; }
  public void setUserId(Long userId) { this.userId = userId; }
  public void setStaffNumber(String staffNumber) { this.staffNumber = staffNumber; }
  public void setStaffPosition(String staffPosition) { this.staffPosition = staffPosition; }
  public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
  public void setNickName(String nickName) { this.nickName = nickName; }
  public void setTimeConfig(Long timeConfig) { this.timeConfig = timeConfig; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingAreaStaff)) return false;  
    BuildingAreaStaff other = (BuildingAreaStaff)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$userId = getUserId(), other$userId = other.getUserId(); 
    if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;  

    Object this$timeConfig = getTimeConfig(), other$timeConfig = other.getTimeConfig(); 
    if ((this$timeConfig == null) ? (other$timeConfig != null) : !this$timeConfig.equals(other$timeConfig)) return false;  

    Object this$areaName = getAreaName(), other$areaName = other.getAreaName(); 
    if ((this$areaName == null) ? (other$areaName != null) : !this$areaName.equals(other$areaName)) return false;  

    Object this$areaType = getAreaType(), other$areaType = other.getAreaType(); 
    if ((this$areaType == null) ? (other$areaType != null) : !this$areaType.equals(other$areaType)) return false;  

    Object this$staffNumber = getStaffNumber(), other$staffNumber = other.getStaffNumber(); 
    if ((this$staffNumber == null) ? (other$staffNumber != null) : !this$staffNumber.equals(other$staffNumber)) return false;  

    Object this$staffPosition = getStaffPosition(), other$staffPosition = other.getStaffPosition(); 
    if ((this$staffPosition == null) ? (other$staffPosition != null) : !this$staffPosition.equals(other$staffPosition)) return false;  

    Object this$licenseNumber = getLicenseNumber(), other$licenseNumber = other.getLicenseNumber(); 
    if ((this$licenseNumber == null) ? (other$licenseNumber != null) : !this$licenseNumber.equals(other$licenseNumber)) return false;  

    Object this$nickName = getNickName(), other$nickName = other.getNickName(); 
    return !((this$nickName == null) ? (other$nickName != null) : !this$nickName.equals(other$nickName)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingAreaStaff; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $userId = getUserId(); 
    result = result * 59 + (($userId == null) ? 43 : $userId.hashCode()); 
    Object $timeConfig = getTimeConfig(); 
    result = result * 59 + (($timeConfig == null) ? 43 : $timeConfig.hashCode()); 
    Object $areaName = getAreaName(); 
    result = result * 59 + (($areaName == null) ? 43 : $areaName.hashCode()); 
    Object $areaType = getAreaType(); 
    result = result * 59 + (($areaType == null) ? 43 : $areaType.hashCode()); 
    Object $staffNumber = getStaffNumber(); 
    result = result * 59 + (($staffNumber == null) ? 43 : $staffNumber.hashCode()); 
    Object $staffPosition = getStaffPosition(); 
    result = result * 59 + (($staffPosition == null) ? 43 : $staffPosition.hashCode()); 
    Object $licenseNumber = getLicenseNumber(); 
    result = result * 59 + (($licenseNumber == null) ? 43 : $licenseNumber.hashCode()); 
    Object $nickName = getNickName(); 
    return result * 59 + (($nickName == null) ? 43 : $nickName.hashCode()); 
  } 

  public String toString() { 
    return "BuildingAreaStaff(id=" + getId() + ", areaName=" + getAreaName() + ", buildingId=" + getBuildingId() + ", areaType=" + getAreaType() + ", userId=" + getUserId() + ", staffNumber=" + getStaffNumber() + ", staffPosition=" + getStaffPosition() + ", licenseNumber=" + getLicenseNumber() + ", nickName=" + getNickName() + ", timeConfig=" + getTimeConfig() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public String getAreaName() {
    return this.areaName;
  }

  public Long getBuildingId() {
    return this.buildingId;
  }

  public String getAreaType() {
    return this.areaType;
  }

  public Long getUserId() {
    return this.userId;
  }

  public String getStaffNumber() {
    return this.staffNumber;
  }

  public String getStaffPosition() {
    return this.staffPosition;
  }

  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  public String getNickName() {
    return this.nickName;
  }

  public Long getTimeConfig() {
    return this.timeConfig;
  }
}