package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingPatrol extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "路线名称")
  private String routeName;

  @Excel(name = "路线编号")
  @JsonSerialize(using = ToStringSerializer.class)
  private Long routeId;

  private Long patrolNumber;
  private Long waitTime;
  private Long tolerantTime;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  private String buildingName;
  private String floorName;
  private String block;
  private String zone;
  private String zoneSub;
  private String pointName;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long pointId;

  private String terSn;
  private String gateway;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setRouteName(String routeName) { this.routeName = routeName; }
  public void setRouteId(Long routeId) { this.routeId = routeId; }
  public void setPatrolNumber(Long patrolNumber) { this.patrolNumber = patrolNumber; }
  public void setWaitTime(Long waitTime) { this.waitTime = waitTime; }
  public void setTolerantTime(Long tolerantTime) { this.tolerantTime = tolerantTime; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setBlock(String block) { this.block = block; }
  public void setZone(String zone) { this.zone = zone; }
  public void setZoneSub(String zoneSub) { this.zoneSub = zoneSub; }
  public void setPointName(String pointName) { this.pointName = pointName; }
  public void setPointId(Long pointId) { this.pointId = pointId; }
  public void setTerSn(String terSn) { this.terSn = terSn; }
  public void setGateway(String gateway) { this.gateway = gateway; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingPatrol)) return false;  
    BuildingPatrol other = (BuildingPatrol)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$routeId = getRouteId(), other$routeId = other.getRouteId(); 
    if ((this$routeId == null) ? (other$routeId != null) : !this$routeId.equals(other$routeId)) return false;  

    Object this$patrolNumber = getPatrolNumber(), other$patrolNumber = other.getPatrolNumber(); 
    if ((this$patrolNumber == null) ? (other$patrolNumber != null) : !this$patrolNumber.equals(other$patrolNumber)) return false;  

    Object this$waitTime = getWaitTime(), other$waitTime = other.getWaitTime(); 
    if ((this$waitTime == null) ? (other$waitTime != null) : !this$waitTime.equals(other$waitTime)) return false;  

    Object this$tolerantTime = getTolerantTime(), other$tolerantTime = other.getTolerantTime(); 
    if ((this$tolerantTime == null) ? (other$tolerantTime != null) : !this$tolerantTime.equals(other$tolerantTime)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$pointId = getPointId(), other$pointId = other.getPointId(); 
    if ((this$pointId == null) ? (other$pointId != null) : !this$pointId.equals(other$pointId)) return false;  

    Object this$routeName = getRouteName(), other$routeName = other.getRouteName(); 
    if ((this$routeName == null) ? (other$routeName != null) : !this$routeName.equals(other$routeName)) return false;  

    Object this$buildingName = getBuildingName(), other$buildingName = other.getBuildingName(); 
    if ((this$buildingName == null) ? (other$buildingName != null) : !this$buildingName.equals(other$buildingName)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$block = getBlock(), other$block = other.getBlock(); 
    if ((this$block == null) ? (other$block != null) : !this$block.equals(other$block)) return false;  

    Object this$zone = getZone(), other$zone = other.getZone(); 
    if ((this$zone == null) ? (other$zone != null) : !this$zone.equals(other$zone)) return false;  

    Object this$zoneSub = getZoneSub(), other$zoneSub = other.getZoneSub(); 
    if ((this$zoneSub == null) ? (other$zoneSub != null) : !this$zoneSub.equals(other$zoneSub)) return false;  

    Object this$pointName = getPointName(), other$pointName = other.getPointName(); 
    if ((this$pointName == null) ? (other$pointName != null) : !this$pointName.equals(other$pointName)) return false;  

    Object this$terSn = getTerSn(), other$terSn = other.getTerSn(); 
    if ((this$terSn == null) ? (other$terSn != null) : !this$terSn.equals(other$terSn)) return false;  

    Object this$gateway = getGateway(), other$gateway = other.getGateway(); 
    return !((this$gateway == null) ? (other$gateway != null) : !this$gateway.equals(other$gateway)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingPatrol; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $routeId = getRouteId(); 
    result = result * 59 + (($routeId == null) ? 43 : $routeId.hashCode()); 
    Object $patrolNumber = getPatrolNumber(); 
    result = result * 59 + (($patrolNumber == null) ? 43 : $patrolNumber.hashCode()); 
    Object $waitTime = getWaitTime(); 
    result = result * 59 + (($waitTime == null) ? 43 : $waitTime.hashCode()); 
    Object $tolerantTime = getTolerantTime(); 
    result = result * 59 + (($tolerantTime == null) ? 43 : $tolerantTime.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $pointId = getPointId(); 
    result = result * 59 + (($pointId == null) ? 43 : $pointId.hashCode()); 
    Object $routeName = getRouteName(); 
    result = result * 59 + (($routeName == null) ? 43 : $routeName.hashCode()); 
    Object $buildingName = getBuildingName(); 
    result = result * 59 + (($buildingName == null) ? 43 : $buildingName.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $block = getBlock(); 
    result = result * 59 + (($block == null) ? 43 : $block.hashCode()); 
    Object $zone = getZone(); 
    result = result * 59 + (($zone == null) ? 43 : $zone.hashCode()); 
    Object $zoneSub = getZoneSub(); 
    result = result * 59 + (($zoneSub == null) ? 43 : $zoneSub.hashCode()); 
    Object $pointName = getPointName(); 
    result = result * 59 + (($pointName == null) ? 43 : $pointName.hashCode()); 
    Object $terSn = getTerSn(); 
    result = result * 59 + (($terSn == null) ? 43 : $terSn.hashCode()); 
    Object $gateway = getGateway(); 
    return result * 59 + (($gateway == null) ? 43 : $gateway.hashCode()); 
  } 

  public String toString() { 
    return "BuildingPatrol(id=" + getId() + ", routeName=" + getRouteName() + ", routeId=" + getRouteId() + ", patrolNumber=" + getPatrolNumber() + ", waitTime=" + getWaitTime() + ", tolerantTime=" + getTolerantTime() + ", buildingId=" + getBuildingId() + ", buildingName=" + getBuildingName() + ", floorName=" + getFloorName() + ", block=" + getBlock() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", pointName=" + getPointName() + ", pointId=" + getPointId() + ", terSn=" + getTerSn() + ", gateway=" + getGateway() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public String getRouteName() {
    return this.routeName;
  }

  public Long getRouteId() {
    return this.routeId;
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

  public Long getBuildingId() {
    return this.buildingId;
  }

  public String getBuildingName() {
    return this.buildingName;
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

  public String getPointName() {
    return this.pointName;
  }

  public Long getPointId() {
    return this.pointId;
  }

  public String getTerSn() {
    return this.terSn;
  }

  public String getGateway() {
    return this.gateway;
  }
}