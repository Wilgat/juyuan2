/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.ruoyi.common.annotation.DataScope;
/*    */ import com.ruoyi.iotlighting.domain.Diagnosis;
/*    */ import com.ruoyi.iotlighting.mapper.DiagnosisMapper;
/*    */ import com.ruoyi.iotlighting.service.IDiagnosisService;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class DiagnosisServiceImpl
/*    */   implements IDiagnosisService
/*    */ {
/* 21 */   private static final Logger log = LoggerFactory.getLogger(DiagnosisServiceImpl.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private DiagnosisMapper diagnosisMapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<Diagnosis> selectDiagnosisList(Diagnosis diagnosis) {
/* 35 */     return this.diagnosisMapper.selectDiagnosisList(diagnosis);
/*    */   }
/*    */ 
/*    */   
/*    */   public int updateGatewayStatus(Diagnosis diagnosis) {
/* 40 */     return this.diagnosisMapper.updateGatewayStatus(diagnosis);
/*    */   }
/*    */ 
/*    */   
/*    */   public int updateTerStatus(Diagnosis diagnosis) {
/* 45 */     return this.diagnosisMapper.updateTerStatus(diagnosis);
/*    */   }
/*    */ 
/*    */   
/*    */   public int updateSensorStatus(String sn, List<String> sensorTypeList) {
/* 50 */     return this.diagnosisMapper.updateSensorStatus(sn, sensorTypeList);
/*    */   }
/*    */ 
/*    */   
/*    */   public int updateTerStatusOffline(Diagnosis diagnosis) {
/* 55 */     return this.diagnosisMapper.updateTerStatusOffline(diagnosis);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateSensorExtendStatus(String sn, List<String> sensorTypeExtendList) {
/* 61 */     Diagnosis param = new Diagnosis();
/* 62 */     param.setTerSn(sn);
/*    */     
/* 64 */     List<Diagnosis> diagnosisList = this.diagnosisMapper.selectSensorExtendList(param);
/*    */     
/* 66 */     if (CollectionUtil.isEmpty(diagnosisList)) {
/*    */       
/* 68 */       if (CollectionUtil.isNotEmpty(sensorTypeExtendList))
/*    */       {
/* 70 */         this.diagnosisMapper.insertSensorExtendStatus(sn, sensorTypeExtendList);
/*    */       }
/*    */ 
/*    */       
/* 74 */       return 1;
/*    */     } 
/* 76 */     if (CollectionUtil.isEmpty(sensorTypeExtendList)) {
/* 77 */       sensorTypeExtendList.add("0");
/*    */     }
/* 79 */     return this.diagnosisMapper.updateSensorExtendStatus(sn, sensorTypeExtendList);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/DiagnosisServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */