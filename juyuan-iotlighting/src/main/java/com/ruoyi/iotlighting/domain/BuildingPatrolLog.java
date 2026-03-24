package com.ruoyi.iotlighting.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;

// NEW: missing imports for Jackson annotations
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingPatrolLog extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Excel(name = "路线名称")
  private String routeName;

  @Excel(name = "路线编号")
  @JsonSerialize(using = ToStringSerializer.class)
  private Long routeId;

  @Excel(name = "员工编号")
  private Long userId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "开始时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
  private Date startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "完成时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
  private Date completeTime;

  private Long timeCount;

  @Excel(name = "更点总数")
  private Long patrolPointTotal;

  @Excel(name = "完成数量")
  private Long patrolPointComplete;

  @Excel(name = "完成标记 0 进行中，1已完成，2已超时")
  private String completeFlag;

  private String nickName;

  // All setters (preserved exactly as in the decompiled file)
  public void setId(Long id) { this.id = id; }
  public void setRouteName(String routeName) { this.routeName = routeName; }
  public void setRouteId(Long routeId) { this.routeId = routeId; }
  public void setUserId(Long userId) { this.userId = userId; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public void setStartTime(Date startTime) { this.startTime = startTime; }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public void setCompleteTime(Date completeTime) { this.completeTime = completeTime; }
  public void setTimeCount(Long timeCount) { this.timeCount = timeCount; }
  public void setPatrolPointTotal(Long patrolPointTotal) { this.patrolPointTotal = patrolPointTotal; }
  public void setPatrolPointComplete(Long patrolPointComplete) { this.patrolPointComplete = patrolPointComplete; }
  public void setCompleteFlag(String completeFlag) { this.completeFlag = completeFlag; }
  public void setNickName(String nickName) { this.nickName = nickName; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof BuildingPatrolLog)) return false;  
    BuildingPatrolLog other = (BuildingPatrolLog)o; 
    if (!other.canEqual(this)) return false;  

    Object this$id = getId(), other$id = other.getId(); 
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id)) return false;  

    Object this$routeId = getRouteId(), other$routeId = other.getRouteId(); 
    if ((this$routeId == null) ? (other$routeId != null) : !this$routeId.equals(other$routeId)) return false;  

    Object this$userId = getUserId(), other$userId = other.getUserId(); 
    if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;  

    Object this$timeCount = getTimeCount(), other$timeCount = other.getTimeCount(); 
    if ((this$timeCount == null) ? (other$timeCount != null) : !this$timeCount.equals(other$timeCount)) return false;  

    Object this$patrolPointTotal = getPatrolPointTotal(), other$patrolPointTotal = other.getPatrolPointTotal(); 
    if ((this$patrolPointTotal == null) ? (other$patrolPointTotal != null) : !this$patrolPointTotal.equals(other$patrolPointTotal)) return false;  

    Object this$patrolPointComplete = getPatrolPointComplete(), other$patrolPointComplete = other.getPatrolPointComplete(); 
    if ((this$patrolPointComplete == null) ? (other$patrolPointComplete != null) : !this$patrolPointComplete.equals(other$patrolPointComplete)) return false;  

    Object this$routeName = getRouteName(), other$routeName = other.getRouteName(); 
    if ((this$routeName == null) ? (other$routeName != null) : !this$routeName.equals(other$routeName)) return false;  

    Object this$startTime = getStartTime(), other$startTime = other.getStartTime(); 
    if ((this$startTime == null) ? (other$startTime != null) : !this$startTime.equals(other$startTime)) return false;  

    Object this$completeTime = getCompleteTime(), other$completeTime = other.getCompleteTime(); 
    if ((this$completeTime == null) ? (other$completeTime != null) : !this$completeTime.equals(other$completeTime)) return false;  

    Object this$completeFlag = getCompleteFlag(), other$completeFlag = other.getCompleteFlag(); 
    if ((this$completeFlag == null) ? (other$completeFlag != null) : !this$completeFlag.equals(other$completeFlag)) return false;  

    Object this$nickName = getNickName(), other$nickName = other.getNickName(); 
    return !((this$nickName == null) ? (other$nickName != null) : !this$nickName.equals(other$nickName)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof BuildingPatrolLog; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $id = getId(); 
    result = result * 59 + (($id == null) ? 43 : $id.hashCode()); 
    Object $routeId = getRouteId(); 
    result = result * 59 + (($routeId == null) ? 43 : $routeId.hashCode()); 
    Object $userId = getUserId(); 
    result = result * 59 + (($userId == null) ? 43 : $userId.hashCode()); 
    Object $timeCount = getTimeCount(); 
    result = result * 59 + (($timeCount == null) ? 43 : $timeCount.hashCode()); 
    Object $patrolPointTotal = getPatrolPointTotal(); 
    result = result * 59 + (($patrolPointTotal == null) ? 43 : $patrolPointTotal.hashCode()); 
    Object $patrolPointComplete = getPatrolPointComplete(); 
    result = result * 59 + (($patrolPointComplete == null) ? 43 : $patrolPointComplete.hashCode()); 
    Object $routeName = getRouteName(); 
    result = result * 59 + (($routeName == null) ? 43 : $routeName.hashCode()); 
    Object $startTime = getStartTime(); 
    result = result * 59 + (($startTime == null) ? 43 : $startTime.hashCode()); 
    Object $completeTime = getCompleteTime(); 
    result = result * 59 + (($completeTime == null) ? 43 : $completeTime.hashCode()); 
    Object $completeFlag = getCompleteFlag(); 
    result = result * 59 + (($completeFlag == null) ? 43 : $completeFlag.hashCode()); 
    Object $nickName = getNickName(); 
    return result * 59 + (($nickName == null) ? 43 : $nickName.hashCode()); 
  } 

  public String toString() { 
    return "BuildingPatrolLog(id=" + getId() + ", routeName=" + getRouteName() + ", routeId=" + getRouteId() + ", userId=" + getUserId() + ", startTime=" + getStartTime() + ", completeTime=" + getCompleteTime() + ", timeCount=" + getTimeCount() + ", patrolPointTotal=" + getPatrolPointTotal() + ", patrolPointComplete=" + getPatrolPointComplete() + ", completeFlag=" + getCompleteFlag() + ", nickName=" + getNickName() + ")"; 
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

  public Long getUserId() {
    return this.userId;
  }

  public Date getStartTime() {
    return this.startTime;
  }

  public Date getCompleteTime() {
    return this.completeTime;
  }

  public Long getTimeCount() {
    return this.timeCount;
  }

  public Long getPatrolPointTotal() {
    return this.patrolPointTotal;
  }

  public Long getPatrolPointComplete() {
    return this.patrolPointComplete;
  }

  public String getCompleteFlag() {
    return this.completeFlag;
  }

  public String getNickName() {
    return this.nickName;
  }
}