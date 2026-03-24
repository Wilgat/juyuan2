/*     */ package com.ruoyi.quartz.domain;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonFormat;
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.quartz.util.CronUtils;
/*     */ import java.util.Date;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Size;
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
/*     */ public class SysJob
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "任务序号", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long jobId;
/*     */   @Excel(name = "任务名称")
/*     */   private String jobName;
/*     */   @Excel(name = "任务组名")
/*     */   private String jobGroup;
/*     */   @Excel(name = "调用目标字符串")
/*     */   private String invokeTarget;
/*     */   @Excel(name = "执行表达式 ")
/*     */   private String cronExpression;
/*     */   @Excel(name = "计划策略 ", readConverterExp = "0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行")
/*  46 */   private String misfirePolicy = "0";
/*     */ 
/*     */   
/*     */   @Excel(name = "并发执行", readConverterExp = "0=允许,1=禁止")
/*     */   private String concurrent;
/*     */ 
/*     */   
/*     */   @Excel(name = "任务状态", readConverterExp = "0=正常,1=暂停")
/*     */   private String status;
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getJobId() {
/*  59 */     return this.jobId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobId(Long jobId) {
/*  64 */     this.jobId = jobId;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "任务名称不能为空")
/*     */   @Size(min = 0, max = 64, message = "任务名称不能超过64个字符")
/*     */   public String getJobName() {
/*  71 */     return this.jobName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobName(String jobName) {
/*  76 */     this.jobName = jobName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJobGroup() {
/*  81 */     return this.jobGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJobGroup(String jobGroup) {
/*  86 */     this.jobGroup = jobGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "调用目标字符串不能为空")
/*     */   @Size(min = 0, max = 500, message = "调用目标字符串长度不能超过500个字符")
/*     */   public String getInvokeTarget() {
/*  93 */     return this.invokeTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvokeTarget(String invokeTarget) {
/*  98 */     this.invokeTarget = invokeTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "Cron执行表达式不能为空")
/*     */   @Size(min = 0, max = 255, message = "Cron执行表达式不能超过255个字符")
/*     */   public String getCronExpression() {
/* 105 */     return this.cronExpression;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCronExpression(String cronExpression) {
/* 110 */     this.cronExpression = cronExpression;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
/*     */   public Date getNextValidTime() {
/* 116 */     if (StringUtils.isNotEmpty(this.cronExpression))
/*     */     {
/* 118 */       return CronUtils.getNextExecution(this.cronExpression);
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMisfirePolicy() {
/* 125 */     return this.misfirePolicy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMisfirePolicy(String misfirePolicy) {
/* 130 */     this.misfirePolicy = misfirePolicy;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getConcurrent() {
/* 135 */     return this.concurrent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConcurrent(String concurrent) {
/* 140 */     this.concurrent = concurrent;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 145 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 150 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 156 */       .append("jobId", getJobId())
/* 157 */       .append("jobName", getJobName())
/* 158 */       .append("jobGroup", getJobGroup())
/* 159 */       .append("cronExpression", getCronExpression())
/* 160 */       .append("nextValidTime", getNextValidTime())
/* 161 */       .append("misfirePolicy", getMisfirePolicy())
/* 162 */       .append("concurrent", getConcurrent())
/* 163 */       .append("status", getStatus())
/* 164 */       .append("createBy", getCreateBy())
/* 165 */       .append("createTime", getCreateTime())
/* 166 */       .append("updateBy", getUpdateBy())
/* 167 */       .append("updateTime", getUpdateTime())
/* 168 */       .append("remark", getRemark())
/* 169 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/domain/SysJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */