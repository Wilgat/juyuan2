/*    */ package com.ruoyi.quartz.util;
/*    */ 
/*    */ import com.ruoyi.quartz.domain.SysJob;
/*    */ import org.quartz.JobExecutionContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QuartzJobExecution
/*    */   extends AbstractQuartzJob
/*    */ {
/*    */   protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
/* 17 */     JobInvokeUtil.invokeMethod(sysJob);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/util/QuartzJobExecution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */