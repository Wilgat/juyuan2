/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.iotlighting.domain.TerGateway;
/*     */ import com.ruoyi.iotlighting.mapper.TerGatewayMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*     */ import com.ruoyi.iotlighting.service.ITerGatewayService;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.ObjectUtils;
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
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class TerGatewayServiceImpl
/*     */   implements ITerGatewayService
/*     */ {
/*     */   @Autowired
/*     */   private TerGatewayMapper terGatewayMapper;
/*     */   @Autowired
/*     */   private TerInfoMapper terInfoMapper;
/*     */   
/*     */   public TerGateway selectTerGatewayById(Long id) {
/*  41 */     return this.terGatewayMapper.selectTerGatewayById(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<TerGateway> selectTerGatewayList(TerGateway terGateway) {
/*  53 */     return this.terGatewayMapper.selectTerGatewayList(terGateway);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertTerGateway(TerGateway terGateway) {
/*  64 */     terGateway.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  65 */     return this.terGatewayMapper.insertTerGateway(terGateway);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateTerGateway(TerGateway terGateway) {
/*  76 */     return this.terGatewayMapper.updateTerGateway(terGateway);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerGatewayByIds(Long[] ids) {
/*  87 */     return deleteTerGatewayById(ids[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerGatewayById(Long id) {
/*  98 */     int count = this.terGatewayMapper.selectTerByGatewayId(id);
/*  99 */     if (count > 0) {
/* 100 */       throw new ServiceException(MessageUtils.message("terGateway.del.error", new Object[0]));
/*     */     }
/* 102 */     return this.terGatewayMapper.deleteTerGatewayById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkTerGateway(TerGateway terGateway) {
/* 107 */     TerGateway gatewayDb = this.terGatewayMapper.selectTerGatewayBySn(terGateway.getSn());
/* 108 */     if (ObjectUtils.isEmpty(terGateway.getId()) && !ObjectUtils.isEmpty(gatewayDb)) {
/* 109 */       throw new ServiceException(MessageUtils.message("terGateway.sn.error", new Object[0]));
/*     */     }
/* 111 */     if (!ObjectUtils.isEmpty(terGateway.getId())) {
/* 112 */       if (!ObjectUtils.isEmpty(gatewayDb) && terGateway.getId().longValue() != gatewayDb.getId().longValue()) {
/* 113 */         throw new ServiceException(MessageUtils.message("terGateway.sn.error", new Object[0]));
/*     */       }
/*     */       
/* 116 */       if (!Objects.equals(terGateway.getBuildingId(), gatewayDb.getBuildingId())) {
/* 117 */         int count = this.terGatewayMapper.selectTerByGatewayId(terGateway.getId());
/* 118 */         if (count > 0)
/* 119 */           throw new ServiceException(MessageUtils.message("terGateway.del.error", new Object[0])); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerGatewayServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */