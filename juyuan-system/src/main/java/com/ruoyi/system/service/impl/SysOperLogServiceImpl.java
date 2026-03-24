/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import com.ruoyi.system.domain.SysOperLog;
/*    */ import com.ruoyi.system.mapper.SysOperLogMapper;
/*    */ import com.ruoyi.system.service.ISysOperLogService;
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
/*    */ @Service
/*    */ public class SysOperLogServiceImpl
/*    */   implements ISysOperLogService
/*    */ {
/*    */   @Autowired
/*    */   private SysOperLogMapper operLogMapper;
/*    */   
/*    */   public void insertOperlog(SysOperLog operLog) {
/* 29 */     this.operLogMapper.insertOperlog(operLog);
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
/*    */   public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
/* 41 */     return this.operLogMapper.selectOperLogList(operLog);
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
/*    */   public int deleteOperLogByIds(Long[] operIds) {
/* 53 */     return this.operLogMapper.deleteOperLogByIds(operIds);
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
/*    */   public SysOperLog selectOperLogById(Long operId) {
/* 65 */     return this.operLogMapper.selectOperLogById(operId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cleanOperLog() {
/* 74 */     this.operLogMapper.cleanOperLog();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysOperLogServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */