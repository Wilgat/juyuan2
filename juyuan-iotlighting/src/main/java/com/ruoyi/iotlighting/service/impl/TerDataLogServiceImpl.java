/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import com.ruoyi.iotlighting.domain.TerDataLog;
/*    */ import com.ruoyi.iotlighting.mapper.TerDataLogMapper;
/*    */ import com.ruoyi.iotlighting.service.ITerDataLogService;
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
/*    */ public class TerDataLogServiceImpl
/*    */   implements ITerDataLogService
/*    */ {
/*    */   @Autowired
/*    */   private TerDataLogMapper terDataLogMapper;
/*    */   
/*    */   public TerDataLog selectTerDataLogById(Long id) {
/* 30 */     return this.terDataLogMapper.selectTerDataLogById(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<TerDataLog> selectTerDataLogList(TerDataLog terDataLog) {
/* 41 */     return this.terDataLogMapper.selectTerDataLogList(terDataLog);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int insertTerDataLog(TerDataLog terDataLog) {
/* 52 */     return this.terDataLogMapper.insertTerDataLog(terDataLog);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateTerDataLog(TerDataLog terDataLog) {
/* 63 */     return this.terDataLogMapper.updateTerDataLog(terDataLog);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteTerDataLogByIds(Long[] ids) {
/* 74 */     return this.terDataLogMapper.deleteTerDataLogByIds(ids);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteTerDataLogById(Long id) {
/* 85 */     return this.terDataLogMapper.deleteTerDataLogById(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<TerDataLog> selectTerAlertLogList(TerDataLog terDataLog) {
/* 90 */     return this.terDataLogMapper.selectTerAlertLogList(terDataLog);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerDataLogServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */