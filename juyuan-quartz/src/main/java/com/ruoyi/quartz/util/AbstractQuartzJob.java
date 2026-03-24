/*    */ package com.ruoyi.quartz.util;
/*    */ 
/*    */ import com.ruoyi.common.utils.ExceptionUtil;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.common.utils.bean.BeanUtils;
/*    */ import com.ruoyi.common.utils.spring.SpringUtils;
/*    */ import com.ruoyi.quartz.domain.SysJob;
/*    */ import com.ruoyi.quartz.domain.SysJobLog;
/*    */ import com.ruoyi.quartz.service.ISysJobLogService;
/*    */ import java.util.Date;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractQuartzJob
/*    */   implements Job
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(JobExecutionContext context) throws JobExecutionException {
/* 36 */     SysJob sysJob = new SysJob();
/* 37 */     BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get("TASK_PROPERTIES"));
/*    */     
/*    */     try {
/* 40 */       before(context, sysJob);
/* 41 */       if (sysJob != null)
/*    */       {
/* 43 */         doExecute(context, sysJob);
/*    */       }
/* 45 */       after(context, sysJob, null);
/*    */     }
/* 47 */     catch (Exception e) {
/*    */       
/* 49 */       log.error("任务执行异常  - ：", e);
/* 50 */       after(context, sysJob, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void before(JobExecutionContext context, SysJob sysJob) {
/* 62 */     threadLocal.set(new Date());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void after(JobExecutionContext context, SysJob sysJob, Exception e) {
/* 73 */     Date startTime = threadLocal.get();
/* 74 */     threadLocal.remove();
/*    */     
/* 76 */     SysJobLog sysJobLog = new SysJobLog();
/* 77 */     sysJobLog.setJobName(sysJob.getJobName());
/* 78 */     sysJobLog.setJobGroup(sysJob.getJobGroup());
/* 79 */     sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
/* 80 */     sysJobLog.setStartTime(startTime);
/* 81 */     sysJobLog.setStopTime(new Date());
/* 82 */     long runMs = sysJobLog.getStopTime().getTime() - sysJobLog.getStartTime().getTime();
/* 83 */     sysJobLog.setJobMessage(sysJobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
/* 84 */     if (e != null) {
/*    */       
/* 86 */       sysJobLog.setStatus("1");
/* 87 */       String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
/* 88 */       sysJobLog.setExceptionInfo(errorMsg);
/*    */     }
/*    */     else {
/*    */       
/* 92 */       sysJobLog.setStatus("0");
/*    */     } 
/*    */ 
/*    */     
/* 96 */     ((ISysJobLogService)SpringUtils.getBean(ISysJobLogService.class)).addJobLog(sysJobLog);
/*    */   }
/*    */   
/*    */   protected abstract void doExecute(JobExecutionContext paramJobExecutionContext, SysJob paramSysJob) throws Exception;
/*    */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/util/AbstractQuartzJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */