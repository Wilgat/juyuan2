package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;

// NEW: missing imports
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;

public class BuildingTerStatus extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  private String floorName;
  private String delFlag;
  private String terSn;
  private String terStatus;
  private String sensorType;
  private String sensorStatus;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
  public void setTerSn(String terSn) { this.terSn = terSn; }
  public void setTerStatus(String terStatus) { this.terStatus = terStatus; }
  public void setSensorType(String sensorType) { this.sensorType = sensorType; }
  public void setSensorStatus(String sensorStatus) { this.sensorStatus = sensorStatus; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingTerStatus)) return false;  
    BuildingTerStatus other = (BuildingTerStatus)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$delFlag = getDelFlag(), other$delFlag = other.getDelFlag(); 
    if ((this$delFlag == null) ? (other$delFlag != null) : !this$delFlag.equals(other$delFlag)) return false;  

    Object this$terSn = getTerSn(), other$terSn = other.getTerSn(); 
    if ((this$terSn == null) ? (other$terSn != null) : !this$terSn.equals(other$terSn)) return false;  

    Object this$terStatus = getTerStatus(), other$terStatus = other.getTerStatus(); 
    if ((this$terStatus == null) ? (other$terStatus != null) : !this$terStatus.equals(other$terStatus)) return false;  

    Object this$sensorType = getSensorType(), other$sensorType = other.getSensorType(); 
    if ((this$sensorType == null) ? (other$sensorType != null) : !this$sensorType.equals(other$sensorType)) return false;  

    Object this$sensorStatus = getSensorStatus(), other$sensorStatus = other.getSensorStatus(); 
    return !((this$sensorStatus == null) ? (other$sensorStatus != null) : !this$sensorStatus.equals(other$sensorStatus)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingTerStatus; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $delFlag = getDelFlag(); 
    result = result * 59 + (($delFlag == null) ? 43 : $delFlag.hashCode()); 
    Object $terSn = getTerSn(); 
    result = result * 59 + (($terSn == null) ? 43 : $terSn.hashCode()); 
    Object $terStatus = getTerStatus(); 
    result = result * 59 + (($terStatus == null) ? 43 : $terStatus.hashCode()); 
    Object $sensorType = getSensorType(); 
    result = result * 59 + (($sensorType == null) ? 43 : $sensorType.hashCode()); 
    Object $sensorStatus = getSensorStatus(); 
    return result * 59 + (($sensorStatus == null) ? 43 : $sensorStatus.hashCode()); 
  } 

  public String toString() { 
    return "BuildingTerStatus(id=" + getId() + ", buildingId=" + getBuildingId() + ", floorName=" + getFloorName() + ", delFlag=" + getDelFlag() + ", terSn=" + getTerSn() + ", terStatus=" + getTerStatus() + ", sensorType=" + getSensorType() + ", sensorStatus=" + getSensorStatus() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public Long getBuildingId() {
    return this.buildingId;
  }

  public String getFloorName() {
    return this.floorName;
  }

  public String getDelFlag() {
    return this.delFlag;
  }

  public String getTerSn() {
    return this.terSn;
  }

  public String getTerStatus() {
    return this.terStatus;
  }

  public String getSensorType() {
    return this.sensorType;
  }

  public String getSensorStatus() {
    return this.sensorStatus;
  }
}