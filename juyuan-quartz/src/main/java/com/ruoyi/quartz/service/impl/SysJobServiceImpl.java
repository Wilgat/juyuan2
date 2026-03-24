/*     */ package com.ruoyi.quartz.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.constant.ScheduleConstants;
/*     */ import com.ruoyi.common.exception.job.TaskException;
/*     */ import com.ruoyi.quartz.domain.SysJob;
/*     */ import com.ruoyi.quartz.mapper.SysJobMapper;
/*     */ import com.ruoyi.quartz.service.ISysJobService;
/*     */ import com.ruoyi.quartz.util.CronUtils;
/*     */ import com.ruoyi.quartz.util.ScheduleUtils;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ @Service
/*     */ public class SysJobServiceImpl
/*     */   implements ISysJobService
/*     */ {
/*     */   @Autowired
/*     */   private Scheduler scheduler;
/*     */   @Autowired
/*     */   private SysJobMapper jobMapper;
/*     */   
/*     */   @PostConstruct
/*     */   public void init() throws SchedulerException, TaskException {
/*  40 */     this.scheduler.clear();
/*  41 */     List<SysJob> jobList = this.jobMapper.selectJobAll();
/*  42 */     for (SysJob job : jobList)
/*     */     {
/*  44 */       ScheduleUtils.createScheduleJob(this.scheduler, job);
/*     */     }
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
/*     */   public List<SysJob> selectJobList(SysJob job) {
/*  57 */     return this.jobMapper.selectJobList(job);
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
/*     */   public SysJob selectJobById(Long jobId) {
/*  69 */     return this.jobMapper.selectJobById(jobId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int pauseJob(SysJob job) throws SchedulerException {
/*  81 */     Long jobId = job.getJobId();
/*  82 */     String jobGroup = job.getJobGroup();
/*  83 */     job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
/*  84 */     int rows = this.jobMapper.updateJob(job);
/*  85 */     if (rows > 0)
/*     */     {
/*  87 */       this.scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
/*     */     }
/*  89 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int resumeJob(SysJob job) throws SchedulerException {
/* 101 */     Long jobId = job.getJobId();
/* 102 */     String jobGroup = job.getJobGroup();
/* 103 */     job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
/* 104 */     int rows = this.jobMapper.updateJob(job);
/* 105 */     if (rows > 0)
/*     */     {
/* 107 */       this.scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
/*     */     }
/* 109 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int deleteJob(SysJob job) throws SchedulerException {
/* 121 */     Long jobId = job.getJobId();
/* 122 */     String jobGroup = job.getJobGroup();
/* 123 */     int rows = this.jobMapper.deleteJobById(jobId);
/* 124 */     if (rows > 0)
/*     */     {
/* 126 */       this.scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
/*     */     }
/* 128 */     return rows;
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
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
/* 141 */     for (Long jobId : jobIds) {
/*     */       
/* 143 */       SysJob job = this.jobMapper.selectJobById(jobId);
/* 144 */       deleteJob(job);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int changeStatus(SysJob job) throws SchedulerException {
/* 157 */     int rows = 0;
/* 158 */     String status = job.getStatus();
/* 159 */     if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
/*     */       
/* 161 */       rows = resumeJob(job);
/*     */     }
/* 163 */     else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
/*     */       
/* 165 */       rows = pauseJob(job);
/*     */     } 
/* 167 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public boolean run(SysJob job) throws SchedulerException {
/* 179 */     boolean result = false;
/* 180 */     Long jobId = job.getJobId();
/* 181 */     String jobGroup = job.getJobGroup();
/* 182 */     SysJob properties = selectJobById(job.getJobId());
/*     */     
/* 184 */     JobDataMap dataMap = new JobDataMap();
/* 185 */     dataMap.put("TASK_PROPERTIES", properties);
/* 186 */     JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
/* 187 */     if (this.scheduler.checkExists(jobKey)) {
/*     */       
/* 189 */       result = true;
/* 190 */       this.scheduler.triggerJob(jobKey, dataMap);
/*     */     } 
/* 192 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int insertJob(SysJob job) throws SchedulerException, TaskException {
/* 204 */     job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
/* 205 */     int rows = this.jobMapper.insertJob(job);
/* 206 */     if (rows > 0)
/*     */     {
/* 208 */       ScheduleUtils.createScheduleJob(this.scheduler, job);
/*     */     }
/* 210 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int updateJob(SysJob job) throws SchedulerException, TaskException {
/* 222 */     SysJob properties = selectJobById(job.getJobId());
/* 223 */     int rows = this.jobMapper.updateJob(job);
/* 224 */     if (rows > 0)
/*     */     {
/* 226 */       updateSchedulerJob(job, properties.getJobGroup());
/*     */     }
/* 228 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException {
/* 239 */     Long jobId = job.getJobId();
/*     */     
/* 241 */     JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
/* 242 */     if (this.scheduler.checkExists(jobKey))
/*     */     {
/*     */       
/* 245 */       this.scheduler.deleteJob(jobKey);
/*     */     }
/* 247 */     ScheduleUtils.createScheduleJob(this.scheduler, job);
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
/*     */   public boolean checkCronExpressionIsValid(String cronExpression) {
/* 259 */     return CronUtils.isValid(cronExpression);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/service/impl/SysJobServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */