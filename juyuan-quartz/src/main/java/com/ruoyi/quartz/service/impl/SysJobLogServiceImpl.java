/*    */ package com.ruoyi.quartz.service.impl;
/*    */ 
/*    */ import com.ruoyi.quartz.domain.SysJobLog;
/*    */ import com.ruoyi.quartz.mapper.SysJobLogMapper;
/*    */ import com.ruoyi.quartz.service.ISysJobLogService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class SysJobLogServiceImpl
/*    */   implements ISysJobLogService
/*    */ {
/*    */   @Autowired
/*    */   private SysJobLogMapper jobLogMapper;
/*    */   
/*    */   public List<SysJobLog> selectJobLogList(SysJobLog jobLog) {
/* 30 */     return this.jobLogMapper.selectJobLogList(jobLog);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SysJobLog selectJobLogById(Long jobLogId) {
/* 42 */     return this.jobLogMapper.selectJobLogById(jobLogId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addJobLog(SysJobLog jobLog) {
/* 53 */     this.jobLogMapper.insertJobLog(jobLog);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteJobLogByIds(Long[] logIds) {
/* 65 */     return this.jobLogMapper.deleteJobLogByIds(logIds);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteJobLogById(Long jobId) {
/* 76 */     return this.jobLogMapper.deleteJobLogById(jobId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cleanJobLog() {
/* 85 */     this.jobLogMapper.cleanJobLog();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/service/impl/SysJobLogServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */