/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import com.ruoyi.common.annotation.DataScope;
/*    */ import com.ruoyi.common.core.domain.entity.SysRole;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.utils.SecurityUtils;
/*    */ import com.ruoyi.iotlighting.domain.TerAlert;
/*    */ import com.ruoyi.iotlighting.domain.TerAlertCount;
/*    */ import com.ruoyi.iotlighting.mapper.TerAlertMapper;
/*    */ import com.ruoyi.iotlighting.service.ITerAlertService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
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
/*    */ 
/*    */ @Service
/*    */ public class TerAlertServiceImpl
/*    */   implements ITerAlertService
/*    */ {
/*    */   @Autowired
/*    */   private TerAlertMapper terAlertMapper;
/*    */   
/*    */   public int insertTerAlert(TerAlert terAlert) {
/* 39 */     return this.terAlertMapper.insertTerAlert(terAlert);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateTerAlert(TerAlert terAlert) {
/* 50 */     return this.terAlertMapper.updateTerAlert(terAlert);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<TerAlert> selectTerAlertList(TerAlert terAlert) {
/* 57 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/* 58 */     Set<String> roleKeys = (Set<String>)loginUser.getUser().getRoles().stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
/*    */     
/* 60 */     long count = roleKeys.stream().filter(roleKey -> roleKey.contains("admin")).count();
/*    */     
/* 62 */     if (count == 0L) {
/* 63 */       terAlert.setAlertSensorType((String[])roleKeys.stream().map(roleKey -> roleKey.replace("alert_", "")).toArray(x$0 -> new String[x$0]));
/*    */     }
/* 65 */     return this.terAlertMapper.selectTerAlertList(terAlert);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<TerAlert> selectTerAlertListNotLogin(TerAlert terAlert) {
/* 70 */     return this.terAlertMapper.selectTerAlertListNotLogin(terAlert);
/*    */   }
/*    */ 
/*    */   
/*    */   public int dealTerAlert(Long id) {
/* 75 */     TerAlert terAlert = new TerAlert();
/* 76 */     terAlert.setDealTime(new Date());
/* 77 */     terAlert.setDealFlag("1");
/* 78 */     terAlert.setId(id);
/* 79 */     return updateTerAlert(terAlert);
/*    */   }
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<TerAlertCount> selectTerAlertCount(TerAlertCount terAlertCount) {
/* 85 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/* 86 */     Set<String> roleKeys = (Set<String>)loginUser.getUser().getRoles().stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
/*    */     
/* 88 */     long count = roleKeys.stream().filter(roleKey -> roleKey.contains("admin")).count();
/*    */     
/* 90 */     if (count == 0L) {
/* 91 */       terAlertCount.setAlertSensorType((String[])roleKeys.stream().map(roleKey -> roleKey.replace("alert_", "")).toArray(x$0 -> new String[x$0]));
/*    */     }
/* 93 */     return this.terAlertMapper.selectTerAlertCount(terAlertCount);
/*    */   }
/*    */ 
/*    */   
/*    */   public int dealTerAlertBatch(Long[] ids) {
/* 98 */     return this.terAlertMapper.updateTerAlertBatch(ids);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerAlertServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */