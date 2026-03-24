package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;

// NEW: missing imports
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingTerStatusVo extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  private String floorName;

  private int terCount;
  private int terCountOnline;
  private int terCountOffline;
  private int terCountAlarm;

  private int sensorCountOnlineMovement;
  private int sensorCountOfflineMovement;
  private int sensorCountAlarmMovement;

  private int sensorCountOnlineSpeed;
  private int sensorCountOfflineSpeed;
  private int sensorCountAlarmSpeed;

  private int sensorCountOnlineSmoking;
  private int sensorCountOfflineSmoking;
  private int sensorCountAlarmSmoking;

  private int sensorCountOnlineWater;
  private int sensorCountOfflineWater;
  private int sensorCountAlarmWater;

  private int sensorCountOnlinePost;
  private int sensorCountOfflinePost;
  private int sensorCountAlarmPost;

  private int sensorCountOnlineTemperature;
  private int sensorCountOfflineTemperature;
  private int sensorCountAlarmTemperature;

  private int sensorCountOnlineLight;
  private int sensorCountOfflineLight;
  private int sensorCountAlarmLight;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setTerCount(int terCount) { this.terCount = terCount; }
  public void setTerCountOnline(int terCountOnline) { this.terCountOnline = terCountOnline; }
  public void setTerCountOffline(int terCountOffline) { this.terCountOffline = terCountOffline; }
  public void setTerCountAlarm(int terCountAlarm) { this.terCountAlarm = terCountAlarm; }
  public void setSensorCountOnlineMovement(int sensorCountOnlineMovement) { this.sensorCountOnlineMovement = sensorCountOnlineMovement; }
  public void setSensorCountOfflineMovement(int sensorCountOfflineMovement) { this.sensorCountOfflineMovement = sensorCountOfflineMovement; }
  public void setSensorCountAlarmMovement(int sensorCountAlarmMovement) { this.sensorCountAlarmMovement = sensorCountAlarmMovement; }
  public void setSensorCountOnlineSpeed(int sensorCountOnlineSpeed) { this.sensorCountOnlineSpeed = sensorCountOnlineSpeed; }
  public void setSensorCountOfflineSpeed(int sensorCountOfflineSpeed) { this.sensorCountOfflineSpeed = sensorCountOfflineSpeed; }
  public void setSensorCountAlarmSpeed(int sensorCountAlarmSpeed) { this.sensorCountAlarmSpeed = sensorCountAlarmSpeed; }
  public void setSensorCountOnlineSmoking(int sensorCountOnlineSmoking) { this.sensorCountOnlineSmoking = sensorCountOnlineSmoking; }
  public void setSensorCountOfflineSmoking(int sensorCountOfflineSmoking) { this.sensorCountOfflineSmoking = sensorCountOfflineSmoking; }
  public void setSensorCountAlarmSmoking(int sensorCountAlarmSmoking) { this.sensorCountAlarmSmoking = sensorCountAlarmSmoking; }
  public void setSensorCountOnlineWater(int sensorCountOnlineWater) { this.sensorCountOnlineWater = sensorCountOnlineWater; }
  public void setSensorCountOfflineWater(int sensorCountOfflineWater) { this.sensorCountOfflineWater = sensorCountOfflineWater; }
  public void setSensorCountAlarmWater(int sensorCountAlarmWater) { this.sensorCountAlarmWater = sensorCountAlarmWater; }
  public void setSensorCountOnlinePost(int sensorCountOnlinePost) { this.sensorCountOnlinePost = sensorCountOnlinePost; }
  public void setSensorCountOfflinePost(int sensorCountOfflinePost) { this.sensorCountOfflinePost = sensorCountOfflinePost; }
  public void setSensorCountAlarmPost(int sensorCountAlarmPost) { this.sensorCountAlarmPost = sensorCountAlarmPost; }
  public void setSensorCountOnlineTemperature(int sensorCountOnlineTemperature) { this.sensorCountOnlineTemperature = sensorCountOnlineTemperature; }
  public void setSensorCountOfflineTemperature(int sensorCountOfflineTemperature) { this.sensorCountOfflineTemperature = sensorCountOfflineTemperature; }
  public void setSensorCountAlarmTemperature(int sensorCountAlarmTemperature) { this.sensorCountAlarmTemperature = sensorCountAlarmTemperature; }
  public void setSensorCountOnlineLight(int sensorCountOnlineLight) { this.sensorCountOnlineLight = sensorCountOnlineLight; }
  public void setSensorCountOfflineLight(int sensorCountOfflineLight) { this.sensorCountOfflineLight = sensorCountOfflineLight; }
  public void setSensorCountAlarmLight(int sensorCountAlarmLight) { this.sensorCountAlarmLight = sensorCountAlarmLight; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingTerStatusVo)) return false;  
    BuildingTerStatusVo other = (BuildingTerStatusVo)o; 
    if (!other.canEqual(this)) return false;  

    if (getTerCount() != other.getTerCount()) return false;  
    if (getTerCountOnline() != other.getTerCountOnline()) return false;  
    if (getTerCountOffline() != other.getTerCountOffline()) return false;  
    if (getTerCountAlarm() != other.getTerCountAlarm()) return false;  
    if (getSensorCountOnlineMovement() != other.getSensorCountOnlineMovement()) return false;  
    if (getSensorCountOfflineMovement() != other.getSensorCountOfflineMovement()) return false;  
    if (getSensorCountAlarmMovement() != other.getSensorCountAlarmMovement()) return false;  
    if (getSensorCountOnlineSpeed() != other.getSensorCountOnlineSpeed()) return false;  
    if (getSensorCountOfflineSpeed() != other.getSensorCountOfflineSpeed()) return false;  
    if (getSensorCountAlarmSpeed() != other.getSensorCountAlarmSpeed()) return false;  
    if (getSensorCountOnlineSmoking() != other.getSensorCountOnlineSmoking()) return false;  
    if (getSensorCountOfflineSmoking() != other.getSensorCountOfflineSmoking()) return false;  
    if (getSensorCountAlarmSmoking() != other.getSensorCountAlarmSmoking()) return false;  
    if (getSensorCountOnlineWater() != other.getSensorCountOnlineWater()) return false;  
    if (getSensorCountOfflineWater() != other.getSensorCountOfflineWater()) return false;  
    if (getSensorCountAlarmWater() != other.getSensorCountAlarmWater()) return false;  
    if (getSensorCountOnlinePost() != other.getSensorCountOnlinePost()) return false;  
    if (getSensorCountOfflinePost() != other.getSensorCountOfflinePost()) return false;  
    if (getSensorCountAlarmPost() != other.getSensorCountAlarmPost()) return false;  
    if (getSensorCountOnlineTemperature() != other.getSensorCountOnlineTemperature()) return false;  
    if (getSensorCountOfflineTemperature() != other.getSensorCountOfflineTemperature()) return false;  
    if (getSensorCountAlarmTemperature() != other.getSensorCountAlarmTemperature()) return false;  
    if (getSensorCountOnlineLight() != other.getSensorCountOnlineLight()) return false;  
    if (getSensorCountOfflineLight() != other.getSensorCountOfflineLight()) return false;  
    if (getSensorCountAlarmLight() != other.getSensorCountAlarmLight()) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    return !((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingTerStatusVo; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    result = result * 59 + getTerCount(); 
    result = result * 59 + getTerCountOnline(); 
    result = result * 59 + getTerCountOffline(); 
    result = result * 59 + getTerCountAlarm(); 
    result = result * 59 + getSensorCountOnlineMovement(); 
    result = result * 59 + getSensorCountOfflineMovement(); 
    result = result * 59 + getSensorCountAlarmMovement(); 
    result = result * 59 + getSensorCountOnlineSpeed(); 
    result = result * 59 + getSensorCountOfflineSpeed(); 
    result = result * 59 + getSensorCountAlarmSpeed(); 
    result = result * 59 + getSensorCountOnlineSmoking(); 
    result = result * 59 + getSensorCountOfflineSmoking(); 
    result = result * 59 + getSensorCountAlarmSmoking(); 
    result = result * 59 + getSensorCountOnlineWater(); 
    result = result * 59 + getSensorCountOfflineWater(); 
    result = result * 59 + getSensorCountAlarmWater(); 
    result = result * 59 + getSensorCountOnlinePost(); 
    result = result * 59 + getSensorCountOfflinePost(); 
    result = result * 59 + getSensorCountAlarmPost(); 
    result = result * 59 + getSensorCountOnlineTemperature(); 
    result = result * 59 + getSensorCountOfflineTemperature(); 
    result = result * 59 + getSensorCountAlarmTemperature(); 
    result = result * 59 + getSensorCountOnlineLight(); 
    result = result * 59 + getSensorCountOfflineLight(); 
    result = result * 59 + getSensorCountAlarmLight(); 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $floorName = getFloorName(); 
    return result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
  } 

  public String toString() { 
    return "BuildingTerStatusVo(id=" + getId() + ", buildingId=" + getBuildingId() + ", floorName=" + getFloorName() + ", terCount=" + getTerCount() + ", terCountOnline=" + getTerCountOnline() + ", terCountOffline=" + getTerCountOffline() + ", terCountAlarm=" + getTerCountAlarm() + ", sensorCountOnlineMovement=" + getSensorCountOnlineMovement() + ", sensorCountOfflineMovement=" + getSensorCountOfflineMovement() + ", sensorCountAlarmMovement=" + getSensorCountAlarmMovement() + ", sensorCountOnlineSpeed=" + getSensorCountOnlineSpeed() + ", sensorCountOfflineSpeed=" + getSensorCountOfflineSpeed() + ", sensorCountAlarmSpeed=" + getSensorCountAlarmSpeed() + ", sensorCountOnlineSmoking=" + getSensorCountOnlineSmoking() + ", sensorCountOfflineSmoking=" + getSensorCountOfflineSmoking() + ", sensorCountAlarmSmoking=" + getSensorCountAlarmSmoking() + ", sensorCountOnlineWater=" + getSensorCountOnlineWater() + ", sensorCountOfflineWater=" + getSensorCountOfflineWater() + ", sensorCountAlarmWater=" + getSensorCountAlarmWater() + ", sensorCountOnlinePost=" + getSensorCountOnlinePost() + ", sensorCountOfflinePost=" + getSensorCountOfflinePost() + ", sensorCountAlarmPost=" + getSensorCountAlarmPost() + ", sensorCountOnlineTemperature=" + getSensorCountOnlineTemperature() + ", sensorCountOfflineTemperature=" + getSensorCountOfflineTemperature() + ", sensorCountAlarmTemperature=" + getSensorCountAlarmTemperature() + ", sensorCountOnlineLight=" + getSensorCountOnlineLight() + ", sensorCountOfflineLight=" + getSensorCountOfflineLight() + ", sensorCountAlarmLight=" + getSensorCountAlarmLight() + ")"; 
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

  public int getTerCount() {
    return this.terCount;
  }

  public int getTerCountOnline() {
    return this.terCountOnline;
  }

  public int getTerCountOffline() {
    return this.terCountOffline;
  }

  public int getTerCountAlarm() {
    return this.terCountAlarm;
  }

  public int getSensorCountOnlineMovement() {
    return this.sensorCountOnlineMovement;
  }

  public int getSensorCountOfflineMovement() {
    return this.sensorCountOfflineMovement;
  }

  public int getSensorCountAlarmMovement() {
    return this.sensorCountAlarmMovement;
  }

  public int getSensorCountOnlineSpeed() {
    return this.sensorCountOnlineSpeed;
  }

  public int getSensorCountOfflineSpeed() {
    return this.sensorCountOfflineSpeed;
  }

  public int getSensorCountAlarmSpeed() {
    return this.sensorCountAlarmSpeed;
  }

  public int getSensorCountOnlineSmoking() {
    return this.sensorCountOnlineSmoking;
  }

  public int getSensorCountOfflineSmoking() {
    return this.sensorCountOfflineSmoking;
  }

  public int getSensorCountAlarmSmoking() {
    return this.sensorCountAlarmSmoking;
  }

  public int getSensorCountOnlineWater() {
    return this.sensorCountOnlineWater;
  }

  public int getSensorCountOfflineWater() {
    return this.sensorCountOfflineWater;
  }

  public int getSensorCountAlarmWater() {
    return this.sensorCountAlarmWater;
  }

  public int getSensorCountOnlinePost() {
    return this.sensorCountOnlinePost;
  }

  public int getSensorCountOfflinePost() {
    return this.sensorCountOfflinePost;
  }

  public int getSensorCountAlarmPost() {
    return this.sensorCountAlarmPost;
  }

  public int getSensorCountOnlineTemperature() {
    return this.sensorCountOnlineTemperature;
  }

  public int getSensorCountOfflineTemperature() {
    return this.sensorCountOfflineTemperature;
  }

  public int getSensorCountAlarmTemperature() {
    return this.sensorCountAlarmTemperature;
  }

  public int getSensorCountOnlineLight() {
    return this.sensorCountOnlineLight;
  }

  public int getSensorCountOfflineLight() {
    return this.sensorCountOfflineLight;
  }

  public int getSensorCountAlarmLight() {
    return this.sensorCountAlarmLight;
  }
}