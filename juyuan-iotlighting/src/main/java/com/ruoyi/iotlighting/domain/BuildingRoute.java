package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BuildingRoute extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "路线名称")
  private String routeName;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setRouteName(String routeName) { this.routeName = routeName; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingRoute)) return false;  
    BuildingRoute other = (BuildingRoute)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$routeName = getRouteName(), other$routeName = other.getRouteName(); 
    return !((this$routeName == null) ? (other$routeName != null) : !this$routeName.equals(other$routeName)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingRoute; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $routeName = getRouteName(); 
    return result * 59 + (($routeName == null) ? 43 : $routeName.hashCode()); 
  } 

  public String toString() { 
    return "BuildingRoute(id=" + getId() + ", routeName=" + getRouteName() + ", buildingId=" + getBuildingId() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public Long getId() {
    return this.id;
  }

  public String getRouteName() {
    return this.routeName;
  }

  public Long getBuildingId() {
    return this.buildingId;
  }
}