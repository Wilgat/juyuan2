/*     */ package com.ruoyi.quartz.util;
/*     */ 
/*     */ import com.ruoyi.common.constant.Constants;
/*     */ import com.ruoyi.common.constant.ScheduleConstants;
/*     */ import com.ruoyi.common.exception.job.TaskException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.quartz.domain.SysJob;
/*     */ import org.quartz.CronScheduleBuilder;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.Job;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.ScheduleBuilder;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.TriggerKey;
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
/*     */ public class ScheduleUtils
/*     */ {
/*     */   private static Class<? extends Job> getQuartzJobClass(SysJob sysJob) {
/*  37 */     boolean isConcurrent = "0".equals(sysJob.getConcurrent());
/*  38 */     return isConcurrent ? (Class)QuartzJobExecution.class : (Class)QuartzDisallowConcurrentExecution.class;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
/*  46 */     return TriggerKey.triggerKey("TASK_CLASS_NAME" + jobId, jobGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JobKey getJobKey(Long jobId, String jobGroup) {
/*  54 */     return JobKey.jobKey("TASK_CLASS_NAME" + jobId, jobGroup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createScheduleJob(Scheduler scheduler, SysJob job) throws SchedulerException, TaskException {
/*  62 */     Class<? extends Job> jobClass = getQuartzJobClass(job);
/*     */     
/*  64 */     Long jobId = job.getJobId();
/*  65 */     String jobGroup = job.getJobGroup();
/*  66 */     JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId, jobGroup)).build();
/*     */ 
/*     */     
/*  69 */     CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
/*  70 */     cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);
/*     */ 
/*     */ 
/*     */     
/*  74 */     CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId, jobGroup)).withSchedule((ScheduleBuilder)cronScheduleBuilder).build();
/*     */ 
/*     */     
/*  77 */     jobDetail.getJobDataMap().put("TASK_PROPERTIES", job);
/*     */ 
/*     */     
/*  80 */     if (scheduler.checkExists(getJobKey(jobId, jobGroup)))
/*     */     {
/*     */       
/*  83 */       scheduler.deleteJob(getJobKey(jobId, jobGroup));
/*     */     }
/*     */ 
/*     */     
/*  87 */     if (StringUtils.isNotNull(CronUtils.getNextExecution(job.getCronExpression())))
/*     */     {
/*     */       
/*  90 */       scheduler.scheduleJob(jobDetail, (Trigger)trigger);
/*     */     }
/*     */ 
/*     */     
/*  94 */     if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue()))
/*     */     {
/*  96 */       scheduler.pauseJob(getJobKey(jobId, jobGroup));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb) throws TaskException {
/* 106 */     switch (job.getMisfirePolicy()) {
/*     */       
/*     */       case "0":
/* 109 */         return cb;
/*     */       case "1":
/* 111 */         return cb.withMisfireHandlingInstructionIgnoreMisfires();
/*     */       case "2":
/* 113 */         return cb.withMisfireHandlingInstructionFireAndProceed();
/*     */       case "3":
/* 115 */         return cb.withMisfireHandlingInstructionDoNothing();
/*     */     } 
/* 117 */     throw new TaskException("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
/*     */   }
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
/*     */   public static boolean whiteList(String invokeTarget) {
/* 130 */     String packageName = StringUtils.substringBefore(invokeTarget, "(");
/* 131 */     int count = StringUtils.countMatches(packageName, ".");
/* 132 */     if (count > 1)
/*     */     {
/* 134 */       return StringUtils.containsAnyIgnoreCase(invokeTarget, (CharSequence[])Constants.JOB_WHITELIST_STR);
/*     */     }
/* 136 */     Object obj = SpringUtils.getBean(StringUtils.split(invokeTarget, ".")[0]);
/* 137 */     String beanPackageName = obj.getClass().getPackage().getName();
/* 138 */     return (StringUtils.containsAnyIgnoreCase(beanPackageName, (CharSequence[])Constants.JOB_WHITELIST_STR) && 
/* 139 */       !StringUtils.containsAnyIgnoreCase(beanPackageName, (CharSequence[])Constants.JOB_ERROR_STR));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/util/ScheduleUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */