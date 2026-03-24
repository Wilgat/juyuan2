package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingPatrolLogDetail extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long patrolLogId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long patrolId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long routeId;

  private Date startTime;
  private String completeFlag;
  private Long userId;
  private String floorName;

  private String block;
  private String zone;
  private String zoneSub;
  private Date createTime;
  private String licenseNumber;
  private String terSn;
  private Long patrolNumber;
  private Long waitTime;
  private Long tolerantTime;
  private String gateway;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setPatrolLogId(Long patrolLogId) { this.patrolLogId = patrolLogId; }
  public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }
  public void setRouteId(Long routeId) { this.routeId = routeId; }
  public void setStartTime(Date startTime) { this.startTime = startTime; }
  public void setCompleteFlag(String completeFlag) { this.completeFlag = completeFlag; }
  public void setUserId(Long userId) { this.userId = userId; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setBlock(String block) { this.block = block; }
  public void setZone(String zone) { this.zone = zone; }
  public void setZoneSub(String zoneSub) { this.zoneSub = zoneSub; }
  public void setCreateTime(Date createTime) { this.createTime = createTime; }
  public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
  public void setTerSn(String terSn) { this.terSn = terSn; }
  public void setPatrolNumber(Long patrolNumber) { this.patrolNumber = patrolNumber; }
  public void setWaitTime(Long waitTime) { this.waitTime = waitTime; }
  public void setTolerantTime(Long tolerantTime) { this.tolerantTime = tolerantTime; }
  public void setGateway(String gateway) { this.gateway = gateway; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingPatrolLogDetail)) return false;  
    BuildingPatrolLogDetail other = (BuildingPatrolLogDetail)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$patrolLogId = getPatrolLogId(), other$patrolLogId = other.getPatrolLogId(); 
    if ((this$patrolLogId == null) ? (other$patrolLogId != null) : !this$patrolLogId.equals(other$patrolLogId)) return false;  

    Object this$patrolId = getPatrolId(), other$patrolId = other.getPatrolId(); 
    if ((this$patrolId == null) ? (other$patrolId != null) : !this$patrolId.equals(other$patrolId)) return false;  

    Object this$routeId = getRouteId(), other$routeId = other.getRouteId(); 
    if ((this$routeId == null) ? (other$routeId != null) : !this$routeId.equals(other$routeId)) return false;  

    Object this$userId = getUserId(), other$userId = other.getUserId(); 
    if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;  

    Object this$patrolNumber = getPatrolNumber(), other$patrolNumber = other.getPatrolNumber(); 
    if ((this$patrolNumber == null) ? (other$patrolNumber != null) : !this$patrolNumber.equals(other$patrolNumber)) return false;  

    Object this$waitTime = getWaitTime(), other$waitTime = other.getWaitTime(); 
    if ((this$waitTime == null) ? (other$waitTime != null) : !this$waitTime.equals(other$waitTime)) return false;  

    Object this$tolerantTime = getTolerantTime(), other$tolerantTime = other.getTolerantTime(); 
    if ((this$tolerantTime == null) ? (other$tolerantTime != null) : !this$tolerantTime.equals(other$tolerantTime)) return false;  

    Object this$startTime = getStartTime(), other$startTime = other.getStartTime(); 
    if ((this$startTime == null) ? (other$startTime != null) : !this$startTime.equals(other$startTime)) return false;  

    Object this$completeFlag = getCompleteFlag(), other$completeFlag = other.getCompleteFlag(); 
    if ((this$completeFlag == null) ? (other$completeFlag != null) : !this$completeFlag.equals(other$completeFlag)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$block = getBlock(), other$block = other.getBlock(); 
    if ((this$block == null) ? (other$block != null) : !this$block.equals(other$block)) return false;  

    Object this$zone = getZone(), other$zone = other.getZone(); 
    if ((this$zone == null) ? (other$zone != null) : !this$zone.equals(other$zone)) return false;  

    Object this$zoneSub = getZoneSub(), other$zoneSub = other.getZoneSub(); 
    if ((this$zoneSub == null) ? (other$zoneSub != null) : !this$zoneSub.equals(other$zoneSub)) return false;  

    Object this$createTime = getCreateTime(), other$createTime = other.getCreateTime(); 
    if ((this$createTime == null) ? (other$createTime != null) : !this$createTime.equals(other$createTime)) return false;  

    Object this$licenseNumber = getLicenseNumber(), other$licenseNumber = other.getLicenseNumber(); 
    if ((this$licenseNumber == null) ? (other$licenseNumber != null) : !this$licenseNumber.equals(other$licenseNumber)) return false;  

    Object this$terSn = getTerSn(), other$terSn = other.getTerSn(); 
    if ((this$terSn == null) ? (other$terSn != null) : !this$terSn.equals(other$terSn)) return false;  

    Object this$gateway = getGateway(), other$gateway = other.getGateway(); 
    return !((this$gateway == null) ? (other$gateway != null) : !this$gateway.equals(other$gateway)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingPatrolLogDetail; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $patrolLogId = getPatrolLogId(); 
    result = result * 59 + (($patrolLogId == null) ? 43 : $patrolLogId.hashCode()); 
    Object $patrolId = getPatrolId(); 
    result = result * 59 + (($patrolId == null) ? 43 : $patrolId.hashCode()); 
    Object $routeId = getRouteId(); 
    result = result * 59 + (($routeId == null) ? 43 : $routeId.hashCode()); 
    Object $userId = getUserId(); 
    result = result * 59 + (($userId == null) ? 43 : $userId.hashCode()); 
    Object $patrolNumber = getPatrolNumber(); 
    result = result * 59 + (($patrolNumber == null) ? 43 : $patrolNumber.hashCode()); 
    Object $waitTime = getWaitTime(); 
    result = result * 59 + (($waitTime == null) ? 43 : $waitTime.hashCode()); 
    Object $tolerantTime = getTolerantTime(); 
    result = result * 59 + (($tolerantTime == null) ? 43 : $tolerantTime.hashCode()); 
    Object $startTime = getStartTime(); 
    result = result * 59 + (($startTime == null) ? 43 : $startTime.hashCode()); 
    Object $completeFlag = getCompleteFlag(); 
    result = result * 59 + (($completeFlag == null) ? 43 : $completeFlag.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $block = getBlock(); 
    result = result * 59 + (($block == null) ? 43 : $block.hashCode()); 
    Object $zone = getZone(); 
    result = result * 59 + (($zone == null) ? 43 : $zone.hashCode()); 
    Object $zoneSub = getZoneSub(); 
    result = result * 59 + (($zoneSub == null) ? 43 : $zoneSub.hashCode()); 
    Object $createTime = getCreateTime(); 
    result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode()); 
    Object $licenseNumber = getLicenseNumber(); 
    result = result * 59 + (($licenseNumber == null) ? 43 : $licenseNumber.hashCode()); 
    Object $terSn = getTerSn(); 
    result = result * 59 + (($terSn == null) ? 43 : $terSn.hashCode()); 
    Object $gateway = getGateway(); 
    return result * 59 + (($gateway == null) ? 43 : $gateway.hashCode()); 
  } 

  public String toString() { 
    return "BuildingPatrolLogDetail(id=" + getId() + ", patrolLogId=" + getPatrolLogId() + ", patrolId=" + getPatrolId() + ", routeId=" + getRouteId() + ", startTime=" + getStartTime() + ", completeFlag=" + getCompleteFlag() + ", userId=" + getUserId() + ", floorName=" + getFloorName() + ", block=" + getBlock() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", createTime=" + getCreateTime() + ", licenseNumber=" + getLicenseNumber() + ", terSn=" + getTerSn() + ", patrolNumber=" + getPatrolNumber() + ", waitTime=" + getWaitTime() + ", tolerantTime=" + getTolerantTime() + ", gateway=" + getGateway() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public Long getPatrolLogId() {
    return this.patrolLogId;
  }

  public Long getPatrolId() {
    return this.patrolId;
  }

  public Long getRouteId() {
    return this.routeId;
  }

  public Date getStartTime() {
    return this.startTime;
  }

  public String getCompleteFlag() {
    return this.completeFlag;
  }

  public Long getUserId() {
    return this.userId;
  }

  public String getFloorName() {
    return this.floorName;
  }

  public String getBlock() {
    return this.block;
  }

  public String getZone() {
    return this.zone;
  }

  public String getZoneSub() {
    return this.zoneSub;
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  public String getTerSn() {
    return this.terSn;
  }

  public Long getPatrolNumber() {
    return this.patrolNumber;
  }

  public Long getWaitTime() {
    return this.waitTime;
  }

  public Long getTolerantTime() {
    return this.tolerantTime;
  }

  public String getGateway() {
    return this.gateway;
  }
}