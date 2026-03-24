/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import com.ruoyi.system.domain.SysLogininfor;
/*    */ import com.ruoyi.system.mapper.SysLogininforMapper;
/*    */ import com.ruoyi.system.service.ISysLogininforService;
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
/*    */ public class SysLogininforServiceImpl
/*    */   implements ISysLogininforService
/*    */ {
/*    */   @Autowired
/*    */   private SysLogininforMapper logininforMapper;
/*    */   
/*    */   public void insertLogininfor(SysLogininfor logininfor) {
/* 30 */     this.logininforMapper.insertLogininfor(logininfor);
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
/*    */   public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor) {
/* 42 */     return this.logininforMapper.selectLogininforList(logininfor);
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
/*    */   public int deleteLogininforByIds(Long[] infoIds) {
/* 54 */     return this.logininforMapper.deleteLogininforByIds(infoIds);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cleanLogininfor() {
/* 63 */     this.logininforMapper.cleanLogininfor();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysLogininforServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */