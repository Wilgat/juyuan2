/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import com.ruoyi.iotlighting.domain.OtaInfo;
/*    */ import com.ruoyi.iotlighting.mapper.OtaInfoMapper;
/*    */ import com.ruoyi.iotlighting.service.IOtaInfoService;
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
/*    */ public class OtaInfoServiceImpl
/*    */   implements IOtaInfoService
/*    */ {
/*    */   @Autowired
/*    */   private OtaInfoMapper otaInfoMapper;
/*    */   
/*    */   public OtaInfo selectOtaInfoById(Long id) {
/* 30 */     return this.otaInfoMapper.selectOtaInfoById(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<OtaInfo> selectOtaInfoList(OtaInfo otaInfo) {
/* 41 */     return this.otaInfoMapper.selectOtaInfoList(otaInfo);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int insertOtaInfo(OtaInfo otaInfo) {
/* 52 */     return this.otaInfoMapper.insertOtaInfo(otaInfo);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateOtaInfo(OtaInfo otaInfo) {
/* 63 */     return this.otaInfoMapper.updateOtaInfo(otaInfo);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteOtaInfoByIds(Long[] ids) {
/* 74 */     return this.otaInfoMapper.deleteOtaInfoByIds(ids);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteOtaInfoById(Long id) {
/* 85 */     return this.otaInfoMapper.deleteOtaInfoById(id);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/OtaInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */