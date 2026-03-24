package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

// NEW: imports for annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingFloorInfo extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "建筑ID")
  @JsonSerialize(using = ToStringSerializer.class)
  private Long buildingId;

  @Excel(name = "建筑层名")
  private String floorName;

  @Excel(name = "建筑层数")
  private Integer floorNumber;

  private Integer type;

  @Excel(name = "创建人")
  private String createdBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "创建时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
  private Date createdTime;

  @Excel(name = "更新人")
  private String updatedBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "更新时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
  private Date updatedTime;

  private String delFlag;

  @Excel(name = "部门ID")
  private Long deptId;

  private Integer floorLength;
  private Integer floorWidth;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long backgroundImgId;

  private String buildingName;
  private String deptName;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }
  public void setFloorName(String floorName) { this.floorName = floorName; }
  public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
  public void setType(Integer type) { this.type = type; }
  public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }
  public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public void setUpdatedTime(Date updatedTime) { this.updatedTime = updatedTime; }
  public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
  public void setDeptId(Long deptId) { this.deptId = deptId; }
  public void setFloorLength(Integer floorLength) { this.floorLength = floorLength; }
  public void setFloorWidth(Integer floorWidth) { this.floorWidth = floorWidth; }
  public void setBackgroundImgId(Long backgroundImgId) { this.backgroundImgId = backgroundImgId; }
  public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
  public void setDeptName(String deptName) { this.deptName = deptName; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingFloorInfo)) return false;  
    BuildingFloorInfo other = (BuildingFloorInfo)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$buildingId = getBuildingId(), other$buildingId = other.getBuildingId(); 
    if ((this$buildingId == null) ? (other$buildingId != null) : !this$buildingId.equals(other$buildingId)) return false;  

    Object this$floorNumber = getFloorNumber(), other$floorNumber = other.getFloorNumber(); 
    if ((this$floorNumber == null) ? (other$floorNumber != null) : !this$floorNumber.equals(other$floorNumber)) return false;  

    Object this$type = getType(), other$type = other.getType(); 
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type)) return false;  

    Object this$deptId = getDeptId(), other$deptId = other.getDeptId(); 
    if ((this$deptId == null) ? (other$deptId != null) : !this$deptId.equals(other$deptId)) return false;  

    Object this$floorLength = getFloorLength(), other$floorLength = other.getFloorLength(); 
    if ((this$floorLength == null) ? (other$floorLength != null) : !this$floorLength.equals(other$floorLength)) return false;  

    Object this$floorWidth = getFloorWidth(), other$floorWidth = other.getFloorWidth(); 
    if ((this$floorWidth == null) ? (other$floorWidth != null) : !this$floorWidth.equals(other$floorWidth)) return false;  

    Object this$backgroundImgId = getBackgroundImgId(), other$backgroundImgId = other.getBackgroundImgId(); 
    if ((this$backgroundImgId == null) ? (other$backgroundImgId != null) : !this$backgroundImgId.equals(other$backgroundImgId)) return false;  

    Object this$floorName = getFloorName(), other$floorName = other.getFloorName(); 
    if ((this$floorName == null) ? (other$floorName != null) : !this$floorName.equals(other$floorName)) return false;  

    Object this$createdBy = getCreatedBy(), other$createdBy = other.getCreatedBy(); 
    if ((this$createdBy == null) ? (other$createdBy != null) : !this$createdBy.equals(other$createdBy)) return false;  

    Object this$createdTime = getCreatedTime(), other$createdTime = other.getCreatedTime(); 
    if ((this$createdTime == null) ? (other$createdTime != null) : !this$createdTime.equals(other$createdTime)) return false;  

    Object this$updatedBy = getUpdatedBy(), other$updatedBy = other.getUpdatedBy(); 
    if ((this$updatedBy == null) ? (other$updatedBy != null) : !this$updatedBy.equals(other$updatedBy)) return false;  

    Object this$updatedTime = getUpdatedTime(), other$updatedTime = other.getUpdatedTime(); 
    if ((this$updatedTime == null) ? (other$updatedTime != null) : !this$updatedTime.equals(other$updatedTime)) return false;  

    Object this$delFlag = getDelFlag(), other$delFlag = other.getDelFlag(); 
    if ((this$delFlag == null) ? (other$delFlag != null) : !this$delFlag.equals(other$delFlag)) return false;  

    Object this$buildingName = getBuildingName(), other$buildingName = other.getBuildingName(); 
    if ((this$buildingName == null) ? (other$buildingName != null) : !this$buildingName.equals(other$buildingName)) return false;  

    Object this$deptName = getDeptName(), other$deptName = other.getDeptName(); 
    return !((this$deptName == null) ? (other$deptName != null) : !this$deptName.equals(other$deptName)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingFloorInfo; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $buildingId = getBuildingId(); 
    result = result * 59 + (($buildingId == null) ? 43 : $buildingId.hashCode()); 
    Object $floorNumber = getFloorNumber(); 
    result = result * 59 + (($floorNumber == null) ? 43 : $floorNumber.hashCode()); 
    Object $type = getType(); 
    result = result * 59 + (($type == null) ? 43 : $type.hashCode()); 
    Object $deptId = getDeptId(); 
    result = result * 59 + (($deptId == null) ? 43 : $deptId.hashCode()); 
    Object $floorLength = getFloorLength(); 
    result = result * 59 + (($floorLength == null) ? 43 : $floorLength.hashCode()); 
    Object $floorWidth = getFloorWidth(); 
    result = result * 59 + (($floorWidth == null) ? 43 : $floorWidth.hashCode()); 
    Object $backgroundImgId = getBackgroundImgId(); 
    result = result * 59 + (($backgroundImgId == null) ? 43 : $backgroundImgId.hashCode()); 
    Object $floorName = getFloorName(); 
    result = result * 59 + (($floorName == null) ? 43 : $floorName.hashCode()); 
    Object $createdBy = getCreatedBy(); 
    result = result * 59 + (($createdBy == null) ? 43 : $createdBy.hashCode()); 
    Object $createdTime = getCreatedTime(); 
    result = result * 59 + (($createdTime == null) ? 43 : $createdTime.hashCode()); 
    Object $updatedBy = getUpdatedBy(); 
    result = result * 59 + (($updatedBy == null) ? 43 : $updatedBy.hashCode()); 
    Object $updatedTime = getUpdatedTime(); 
    result = result * 59 + (($updatedTime == null) ? 43 : $updatedTime.hashCode()); 
    Object $delFlag = getDelFlag(); 
    result = result * 59 + (($delFlag == null) ? 43 : $delFlag.hashCode()); 
    Object $buildingName = getBuildingName(); 
    result = result * 59 + (($buildingName == null) ? 43 : $buildingName.hashCode()); 
    Object $deptName = getDeptName(); 
    return result * 59 + (($deptName == null) ? 43 : $deptName.hashCode()); 
  } 

  public String toString() { 
    return "BuildingFloorInfo(id=" + getId() + ", buildingId=" + getBuildingId() + ", floorName=" + getFloorName() + ", floorNumber=" + getFloorNumber() + ", type=" + getType() + ", createdBy=" + getCreatedBy() + ", createdTime=" + getCreatedTime() + ", updatedBy=" + getUpdatedBy() + ", updatedTime=" + getUpdatedTime() + ", delFlag=" + getDelFlag() + ", deptId=" + getDeptId() + ", floorLength=" + getFloorLength() + ", floorWidth=" + getFloorWidth() + ", backgroundImgId=" + getBackgroundImgId() + ", buildingName=" + getBuildingName() + ", deptName=" + getDeptName() + ")"; 
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

  public Integer getFloorNumber() {
    return this.floorNumber;
  }

  public Integer getType() {
    return this.type;
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

  public Integer getFloorLength() {
    return this.floorLength;
  }

  public Integer getFloorWidth() {
    return this.floorWidth;
  }

  public Long getBackgroundImgId() {
    return this.backgroundImgId;
  }

  public String getBuildingName() {
    return this.buildingName;
  }

  public String getDeptName() {
    return this.deptName;
  }
}