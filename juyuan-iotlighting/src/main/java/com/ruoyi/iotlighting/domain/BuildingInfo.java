package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

// NEW: imports for annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingInfo extends BaseEntity {
  private static final long serialVersionUID = 1L;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  @Excel(name = "建筑标识")
  private String name;
  @Excel(name = "建筑层数")
  private Integer buildingFloor;
  @Excel(name = "建筑层数")
  private String floors;
  @Excel(name = "建筑长度")
  private Integer buildingLength;
  @Excel(name = "建筑宽度")
  private Integer buildingWidth;
  @Excel(name = "地下停车场层数")
  private Integer parkingFloor;
  @Excel(name = "停车场长度")
  private Integer parkingLength;
  @Excel(name = "停车场宽度")
  private Integer parkingWidth;
  @Excel(name = "创建人")
  private String createdBy;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "创建时间", width = 30.0D, dateFormat = "yyyy-MM-dd")
  private Date createdTime;
  @Excel(name = "更新人")
  private String updatedBy;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "更新时间", width = 30.0D, dateFormat = "yyyy-MM-dd")
  private Date updatedTime;
  private String delFlag;
  @Excel(name = "部门ID")
  private Long deptId;

  public void setId(Long id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setBuildingFloor(Integer buildingFloor) { this.buildingFloor = buildingFloor; }
  public void setFloors(String floors) { this.floors = floors; }
  public void setBuildingLength(Integer buildingLength) { this.buildingLength = buildingLength; }
  public void setBuildingWidth(Integer buildingWidth) { this.buildingWidth = buildingWidth; }
  public void setParkingFloor(Integer parkingFloor) { this.parkingFloor = parkingFloor; }
  public void setParkingLength(Integer parkingLength) { this.parkingLength = parkingLength; }
  public void setParkingWidth(Integer parkingWidth) { this.parkingWidth = parkingWidth; }
  public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
  @JsonFormat(pattern = "yyyy-MM-dd")
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }
  public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
  @JsonFormat(pattern = "yyyy-MM-dd")
  public void setUpdatedTime(Date updatedTime) { this.updatedTime = updatedTime; }
  public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
  public void setDeptId(Long deptId) { this.deptId = deptId; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingInfo)) return false;  
    BuildingInfo other = (BuildingInfo)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingFloor = getBuildingFloor(), other$buildingFloor = other.getBuildingFloor(); 
    if ((this$buildingFloor == null) ? (other$buildingFloor != null) : !this$buildingFloor.equals(other$buildingFloor)) return false;  

    Object this$buildingLength = getBuildingLength(), other$buildingLength = other.getBuildingLength(); 
    if ((this$buildingLength == null) ? (other$buildingLength != null) : !this$buildingLength.equals(other$buildingLength)) return false;  

    Object this$buildingWidth = getBuildingWidth(), other$buildingWidth = other.getBuildingWidth(); 
    if ((this$buildingWidth == null) ? (other$buildingWidth != null) : !this$buildingWidth.equals(other$buildingWidth)) return false;  

    Object this$parkingFloor = getParkingFloor(), other$parkingFloor = other.getParkingFloor(); 
    if ((this$parkingFloor == null) ? (other$parkingFloor != null) : !this$parkingFloor.equals(other$parkingFloor)) return false;  

    Object this$parkingLength = getParkingLength(), other$parkingLength = other.getParkingLength(); 
    if ((this$parkingLength == null) ? (other$parkingLength != null) : !this$parkingLength.equals(other$parkingLength)) return false;  

    Object this$parkingWidth = getParkingWidth(), other$parkingWidth = other.getParkingWidth(); 
    if ((this$parkingWidth == null) ? (other$parkingWidth != null) : !this$parkingWidth.equals(other$parkingWidth)) return false;  

    Object this$deptId = getDeptId(), other$deptId = other.getDeptId(); 
    if ((this$deptId == null) ? (other$deptId != null) : !this$deptId.equals(other$deptId)) return false;  

    Object this$name = getName(), other$name = other.getName(); 
    if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;  

    Object this$floors = getFloors(), other$floors = other.getFloors(); 
    if ((this$floors == null) ? (other$floors != null) : !this$floors.equals(other$floors)) return false;  

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
    return other instanceof BuildingInfo; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingFloor = getBuildingFloor(); 
    result = result * 59 + (($buildingFloor == null) ? 43 : $buildingFloor.hashCode()); 
    Object $buildingLength = getBuildingLength(); 
    result = result * 59 + (($buildingLength == null) ? 43 : $buildingLength.hashCode()); 
    Object $buildingWidth = getBuildingWidth(); 
    result = result * 59 + (($buildingWidth == null) ? 43 : $buildingWidth.hashCode()); 
    Object $parkingFloor = getParkingFloor(); 
    result = result * 59 + (($parkingFloor == null) ? 43 : $parkingFloor.hashCode()); 
    Object $parkingLength = getParkingLength(); 
    result = result * 59 + (($parkingLength == null) ? 43 : $parkingLength.hashCode()); 
    Object $parkingWidth = getParkingWidth(); 
    result = result * 59 + (($parkingWidth == null) ? 43 : $parkingWidth.hashCode()); 
    Object $deptId = getDeptId(); 
    result = result * 59 + (($deptId == null) ? 43 : $deptId.hashCode()); 
    Object $name = getName(); 
    result = result * 59 + (($name == null) ? 43 : $name.hashCode()); 
    Object $floors = getFloors(); 
    result = result * 59 + (($floors == null) ? 43 : $floors.hashCode()); 
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
    return "BuildingInfo(id=" + getId() + ", name=" + getName() + ", buildingFloor=" + getBuildingFloor() + ", floors=" + getFloors() + ", buildingLength=" + getBuildingLength() + ", buildingWidth=" + getBuildingWidth() + ", parkingFloor=" + getParkingFloor() + ", parkingLength=" + getParkingLength() + ", parkingWidth=" + getParkingWidth() + ", createdBy=" + getCreatedBy() + ", createdTime=" + getCreatedTime() + ", updatedBy=" + getUpdatedBy() + ", updatedTime=" + getUpdatedTime() + ", delFlag=" + getDelFlag() + ", deptId=" + getDeptId() + ")"; 
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Integer getBuildingFloor() {
    return this.buildingFloor;
  }

  public String getFloors() {
    return this.floors;
  }

  public Integer getBuildingLength() {
    return this.buildingLength;
  }

  public Integer getBuildingWidth() {
    return this.buildingWidth;
  }

  public Integer getParkingFloor() {
    return this.parkingFloor;
  }

  public Integer getParkingLength() {
    return this.parkingLength;
  }

  public Integer getParkingWidth() {
    return this.parkingWidth;
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

  public Long getDeptId() {
    return this.deptId;
  }
}