package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

// NEW: imports for annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingArea extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "区域名称")
  private String areaName;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  private String areaType;

  @Excel(name = "路线编号")
  @JsonSerialize(using = ToStringSerializer.class)
  private Long areaId;

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
  public void setAreaName(String areaName) { this.areaName = areaName; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setAreaType(String areaType) { this.areaType = areaType; }
  public void setAreaId(Long areaId) { this.areaId = areaId; }
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
    if (!(o instanceof BuildingArea)) return false;  
    BuildingArea other = (BuildingArea)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$areaId = getAreaId(), other$areaId = other.getAreaId(); 
    if ((this$areaId == null) ? (other$areaId != null) : !this$areaId.equals(other$areaId)) return false;  

    Object this$pointId = getPointId(), other$pointId = other.getPointId(); 
    if ((this$pointId == null) ? (other$pointId != null) : !this$pointId.equals(other$pointId)) return false;  

    Object this$areaName = getAreaName(), other$areaName = other.getAreaName(); 
    if ((this$areaName == null) ? (other$areaName != null) : !this$areaName.equals(other$areaName)) return false;  

    Object this$areaType = getAreaType(), other$areaType = other.getAreaType(); 
    if ((this$areaType == null) ? (other$areaType != null) : !this$areaType.equals(other$areaType)) return false;  

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
    return other instanceof BuildingArea; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $areaId = getAreaId(); 
    result = result * 59 + (($areaId == null) ? 43 : $areaId.hashCode()); 
    Object $pointId = getPointId(); 
    result = result * 59 + (($pointId == null) ? 43 : $pointId.hashCode()); 
    Object $areaName = getAreaName(); 
    result = result * 59 + (($areaName == null) ? 43 : $areaName.hashCode()); 
    Object $areaType = getAreaType(); 
    result = result * 59 + (($areaType == null) ? 43 : $areaType.hashCode()); 
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
    return "BuildingArea(id=" + getId() + ", areaName=" + getAreaName() + ", buildingId=" + getBuildingId() + ", areaType=" + getAreaType() + ", areaId=" + getAreaId() + ", buildingName=" + getBuildingName() + ", floorName=" + getFloorName() + ", block=" + getBlock() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", pointName=" + getPointName() + ", pointId=" + getPointId() + ", terSn=" + getTerSn() + ", gateway=" + getGateway() + ")"; 
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

  public Long getAreaId() {
    return this.areaId;
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