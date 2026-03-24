/*     */ package com.ruoyi.quartz.domain;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SysJobLog
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "日志序号")
/*     */   private Long jobLogId;
/*     */   @Excel(name = "任务名称")
/*     */   private String jobName;
/*     */   @Excel(name = "任务组名")
/*     */   private String jobGroup;
/*     */   @Excel(name = "调用目标字符串")
/*     */   private String invokeTarget;
/*     */   @Excel(name = "日志信息")
/*     */   private String jobMessage;
/*     */   @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
/*     */   private String status;
/*     */   @Excel(name = "异常信息")
/*     */   private String exceptionInfo;
/*     */   private Date startTime;
/*     */   private Date stopTime;
/*     */   
/*     */   public Long getJobLogId() {
/*  54 */     return this.jobLogId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobLogId(Long jobLogId) {
/*  59 */     this.jobLogId = jobLogId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJobName() {
/*  64 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobName(String jobName) {
/*  69 */     this.jobName = jobName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJobGroup() {
/*  74 */     return this.jobGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobGroup(String jobGroup) {
/*  79 */     this.jobGroup = jobGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInvokeTarget() {
/*  84 */     return this.invokeTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvokeTarget(String invokeTarget) {
/*  89 */     this.invokeTarget = invokeTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJobMessage() {
/*  94 */     return this.jobMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobMessage(String jobMessage) {
/*  99 */     this.jobMessage = jobMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 104 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 109 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getExceptionInfo() {
/* 114 */     return this.exceptionInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExceptionInfo(String exceptionInfo) {
/* 119 */     this.exceptionInfo = exceptionInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getStartTime() {
/* 124 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartTime(Date startTime) {
/* 129 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getStopTime() {
/* 134 */     return this.stopTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStopTime(Date stopTime) {
/* 139 */     this.stopTime = stopTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 144 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 145 */       .append("jobLogId", getJobLogId())
/* 146 */       .append("jobName", getJobName())
/* 147 */       .append("jobGroup", getJobGroup())
/* 148 */       .append("jobMessage", getJobMessage())
/* 149 */       .append("status", getStatus())
/* 150 */       .append("exceptionInfo", getExceptionInfo())
/* 151 */       .append("startTime", getStartTime())
/* 152 */       .append("stopTime", getStopTime())
/* 153 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/domain/SysJobLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */