package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingLifeguard extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "点位id")
  @JsonSerialize(using = ToStringSerializer.class)
  private Long pointId;

  @Excel(name = "一号救生员")
  private Long userIdOne;

  @Excel(name = "二号救生员")
  private Long userIdTwo;

  @Excel(name = "三号救生员")
  private Long userIdThree;

  private String buildingName;
  private String floorName;
  private String pointName;
  private String block;
  private String zone;
  private String zoneSub;
  private String terSn;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setPointId(Long pointId) { this.pointId = pointId; }
  public void setUserIdOne(Long userIdOne) { this.userIdOne = userIdOne; }
  public void setUserIdTwo(Long userIdTwo) { this.userIdTwo = userIdTwo; }
  public void setUserIdThree(Long userIdThree) { this.userIdThree = userIdThree; }
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setPointName(String pointName) { this.pointName = pointName; }
  public void setBlock(String block) { this.block = block; }
  public void setZone(String zone) { this.zone = zone; }
  public void setZoneSub(String zoneSub) { this.zoneSub = zoneSub; }
  public void setTerSn(String terSn) { this.terSn = terSn; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingLifeguard)) return false;  
    BuildingLifeguard other = (BuildingLifeguard)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$pointId = getPointId(), other$pointId = other.getPointId(); 
    if ((this$pointId == null) ? (other$pointId != null) : !this$pointId.equals(other$pointId)) return false;  

    Object this$userIdOne = getUserIdOne(), other$userIdOne = other.getUserIdOne(); 
    if ((this$userIdOne == null) ? (other$userIdOne != null) : !this$userIdOne.equals(other$userIdOne)) return false;  

    Object this$userIdTwo = getUserIdTwo(), other$userIdTwo = other.getUserIdTwo(); 
    if ((this$userIdTwo == null) ? (other$userIdTwo != null) : !this$userIdTwo.equals(other$userIdTwo)) return false;  

    Object this$userIdThree = getUserIdThree(), other$userIdThree = other.getUserIdThree(); 
    if ((this$userIdThree == null) ? (other$userIdThree != null) : !this$userIdThree.equals(other$userIdThree)) return false;  

    Object this$buildingName = getBuildingName(), other$buildingName = other.getBuildingName(); 
    if ((this$buildingName == null) ? (other$buildingName != null) : !this$buildingName.equals(other$buildingName)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$pointName = getPointName(), other$pointName = other.getPointName(); 
    if ((this$pointName == null) ? (other$pointName != null) : !this$pointName.equals(other$pointName)) return false;  

    Object this$block = getBlock(), other$block = other.getBlock(); 
    if ((this$block == null) ? (other$block != null) : !this$block.equals(other$block)) return false;  

    Object this$zone = getZone(), other$zone = other.getZone(); 
    if ((this$zone == null) ? (other$zone != null) : !this$zone.equals(other$zone)) return false;  

    Object this$zoneSub = getZoneSub(), other$zoneSub = other.getZoneSub(); 
    if ((this$zoneSub == null) ? (other$zoneSub != null) : !this$zoneSub.equals(other$zoneSub)) return false;  

    Object this$terSn = getTerSn(), other$terSn = other.getTerSn(); 
    return !((this$terSn == null) ? (other$terSn != null) : !this$terSn.equals(other$terSn)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingLifeguard; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $pointId = getPointId(); 
    result = result * 59 + (($pointId == null) ? 43 : $pointId.hashCode()); 
    Object $userIdOne = getUserIdOne(); 
    result = result * 59 + (($userIdOne == null) ? 43 : $userIdOne.hashCode()); 
    Object $userIdTwo = getUserIdTwo(); 
    result = result * 59 + (($userIdTwo == null) ? 43 : $userIdTwo.hashCode()); 
    Object $userIdThree = getUserIdThree(); 
    result = result * 59 + (($userIdThree == null) ? 43 : $userIdThree.hashCode()); 
    Object $buildingName = getBuildingName(); 
    result = result * 59 + (($buildingName == null) ? 43 : $buildingName.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $pointName = getPointName(); 
    result = result * 59 + (($pointName == null) ? 43 : $pointName.hashCode()); 
    Object $block = getBlock(); 
    result = result * 59 + (($block == null) ? 43 : $block.hashCode()); 
    Object $zone = getZone(); 
    result = result * 59 + (($zone == null) ? 43 : $zone.hashCode()); 
    Object $zoneSub = getZoneSub(); 
    result = result * 59 + (($zoneSub == null) ? 43 : $zoneSub.hashCode()); 
    Object $terSn = getTerSn(); 
    return result * 59 + (($terSn == null) ? 43 : $terSn.hashCode()); 
  } 

  public String toString() { 
    return "BuildingLifeguard(id=" + getId() + ", pointId=" + getPointId() + ", userIdOne=" + getUserIdOne() + ", userIdTwo=" + getUserIdTwo() + ", userIdThree=" + getUserIdThree() + ", buildingName=" + getBuildingName() + ", floorName=" + getFloorName() + ", pointName=" + getPointName() + ", block=" + getBlock() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", terSn=" + getTerSn() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public Long getPointId() {
    return this.pointId;
  }

  public Long getUserIdOne() {
    return this.userIdOne;
  }

  public Long getUserIdTwo() {
    return this.userIdTwo;
  }

  public Long getUserIdThree() {
    return this.userIdThree;
  }

  public String getBuildingName() { return this.buildingName; }
  public String getFloorName() { return this.floorName; }
  public String getPointName() { return this.pointName; }
  public String getBlock() { return this.block; }
  public String getZone() { return this.zone; }
  public String getZoneSub() { return this.zoneSub; }
  public String getTerSn() {
    return this.terSn;
  }
}