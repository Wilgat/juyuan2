package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;

// NEW: missing import for Jackson annotation
import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingZoneInfo extends BaseEntity {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long buildingId;

  @Excel(name = "建筑名称")
  private String buildingName;

  private Long floorId;

  @Excel(name = "楼层名称")
  private String floorName;

  @Excel(name = "栋")
  private String block;

  @Excel(name = "区域")
  private String zone;

  @Excel(name = "区域(分类)")
  private String zoneSub;

  @Excel(name = "点位名称")
  private String pointName;

  private String createdBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
  private Date createdTime;

  private String updatedBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
  private Date updatedTime;

  private String delFlag;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
  public void setFloorId(Long floorId) { this.floorId = floorId; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setBlock(String block) { this.block = block; }
  public void setZone(String zone) { this.zone = zone; }
  public void setZoneSub(String zoneSub) { this.zoneSub = zoneSub; }
  public void setPointName(String pointName) { this.pointName = pointName; }
  public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }
  public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
  public void setUpdatedTime(Date updatedTime) { this.updatedTime = updatedTime; }
  public void setDelFlag(String delFlag) { this.delFlag = delFlag; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingZoneInfo)) return false;  
    BuildingZoneInfo other = (BuildingZoneInfo)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$floorId = getFloorId(), other$floorId = other.getFloorId(); 
    if ((this$floorId == null) ? (other$floorId != null) : !this$floorId.equals(other$floorId)) return false;  

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

    Object this$createdBy = getCreatedBy(), other$createdBy = other.getCreatedBy(); 
    if ((this$createdBy == null) ? (other$createdBy != null) : !this$createdBy.equals(other$createdBy)) return false;  

    Object this$createdTime = getCreatedTime(), other$createdTime = other.getCreatedTime(); 
    if ((this$createdTime == null) ? (other$createdTime != null) : !this$createdTime.equals(other$createdTime)) return false;  

    Object this$updatedBy = getUpdatedBy(), other$updatedBy = other.getUpdatedBy(); 
    if ((this$updatedBy == null) ? (other$updatedBy != null) : !this$updatedBy.equals(other$updatedBy)) return false;  

    Object this$updatedTime = getUpdatedTime(), other$updatedTime = other.getUpdatedTime(); 
    if ((this$updatedTime == null) ? (other$updatedTime != null) : !this$updatedTime.equals(other$updatedTime)) return false;  

    Object this$delFlag = getDelFlag(), other$delFlag = other.getDelFlag(); 
    return !((this$delFlag == null) ? (other$delFlag != null) : !this$delFlag.equals(other$delFlag)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingZoneInfo; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $floorId = getFloorId(); 
    result = result * 59 + (($floorId == null) ? 43 : $floorId.hashCode()); 
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
    Object $createdBy = getCreatedBy(); 
    result = result * 59 + (($createdBy == null) ? 43 : $createdBy.hashCode()); 
    Object $createdTime = getCreatedTime(); 
    result = result * 59 + (($createdTime == null) ? 43 : $createdTime.hashCode()); 
    Object $updatedBy = getUpdatedBy(); 
    result = result * 59 + (($updatedBy == null) ? 43 : $updatedBy.hashCode()); 
    Object $updatedTime = getUpdatedTime(); 
    result = result * 59 + (($updatedTime == null) ? 43 : $updatedTime.hashCode()); 
    Object $delFlag = getDelFlag(); 
    return result * 59 + (($delFlag == null) ? 43 : $delFlag.hashCode()); 
  } 

  public String toString() { 
    return "BuildingZoneInfo(id=" + getId() + ", buildingId=" + getBuildingId() + ", buildingName=" + getBuildingName() + ", floorId=" + getFloorId() + ", floorName=" + getFloorName() + ", block=" + getBlock() + ", zone=" + getZone() + ", zoneSub=" + getZoneSub() + ", pointName=" + getPointName() + ", createdBy=" + getCreatedBy() + ", createdTime=" + getCreatedTime() + ", updatedBy=" + getUpdatedBy() + ", updatedTime=" + getUpdatedTime() + ", delFlag=" + getDelFlag() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public Long getBuildingId() {
    return this.buildingId;
  }

  public String getBuildingName() {
    return this.buildingName;
  }

  public Long getFloorId() {
    return this.floorId;
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

  public String getCreatedBy() {
    return this.createdBy;
  }

  public Date getCreatedTime() {
    return this.createdTime;
  }

  public String getUpdatedBy() {
    return this.updatedBy;
  }

  public Date getUpdatedTime() {
    return this.updatedTime;
  }

  public String getDelFlag() {
    return this.delFlag;
  }
}