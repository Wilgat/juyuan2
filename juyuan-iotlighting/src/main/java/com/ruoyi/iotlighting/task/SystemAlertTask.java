/*     */ package com.ruoyi.iotlighting.task;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.Diagnosis;
/*     */ import com.ruoyi.iotlighting.domain.TerAlertSystem;
/*     */ import com.ruoyi.iotlighting.mapper.DiagnosisMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerAlertSystemMapper;
/*     */ import com.ruoyi.iotlighting.service.IAppPushService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component("systemAlertTask")
/*     */ public class SystemAlertTask
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(SystemAlertTask.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private DiagnosisMapper diagnosisMapper;
/*     */   
/*     */   @Autowired
/*     */   private TerAlertSystemMapper terAlertSystemMapper;
/*     */   
/*     */   @Autowired
/*     */   private IAppPushService appPushService;
/*     */   
/*     */   @Autowired
/*     */   private SystemAlertTask self;
/*     */ 
/*     */   
/*     */   public void statusChange() {
/*  44 */     Diagnosis queryParam = new Diagnosis();
/*     */     
/*  46 */     List<Diagnosis> diagnosisList = this.diagnosisMapper.selectDiagnosisList(queryParam);
/*  47 */     List<Diagnosis> diagnosisExtendList = this.diagnosisMapper.selectSensorExtendList(queryParam);
/*  48 */     diagnosisList.addAll(diagnosisExtendList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     List<String> dealGatewayList = new ArrayList<>();
/*     */     
/*  56 */     List<String> dealTerList = new ArrayList<>();
/*  57 */     for (Diagnosis diagnosis : diagnosisList) {
/*  58 */       if (dealGatewayList.contains(diagnosis.getGateway())) {
/*     */         continue;
/*     */       }
/*  61 */       this.self.updateDataStatus(dealGatewayList, dealTerList, diagnosis);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public void updateDataStatus(List<String> dealGatewayList, List<String> dealTerList, Diagnosis diagnosis) {
/*  72 */     Date gatewayDiagnosisUpdateTime = diagnosis.getGatewayDiagnosisUpdateTime();
/*     */     
/*  74 */     if (gatewayDiagnosisUpdateTime != null && System.currentTimeMillis() - gatewayDiagnosisUpdateTime.getTime() > 900000L) {
/*  75 */       dealGatewayList.add(diagnosis.getGateway());
/*  76 */       TerAlertSystem alertQueryParam = new TerAlertSystem();
/*  77 */       alertQueryParam.setTerSn(diagnosis.getGateway());
/*  78 */       TerAlertSystem terAlertSystem = this.terAlertSystemMapper.queryAlertSystemLaste(alertQueryParam);
/*  79 */       if (ObjectUtil.isNull(terAlertSystem) || gatewayDiagnosisUpdateTime.after(terAlertSystem.getCreateTime())) {
/*  80 */         TerAlertSystem newWifiTerAlertSystem = new TerAlertSystem();
/*  81 */         newWifiTerAlertSystem.setAlertType("wifi");
/*  82 */         newWifiTerAlertSystem.setDelFlag("0");
/*  83 */         newWifiTerAlertSystem.setDealFlag("0");
/*  84 */         newWifiTerAlertSystem.setTerSn(diagnosis.getGateway());
/*  85 */         newWifiTerAlertSystem.setBuildingId(diagnosis.getBuildingId());
/*  86 */         newWifiTerAlertSystem.setCreateTime(new Date());
/*  87 */         newWifiTerAlertSystem.setWifiType(diagnosis.getWifiType());
/*  88 */         newWifiTerAlertSystem.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  89 */         log.info("wifi告警：{}", JSON.toJSONString(newWifiTerAlertSystem));
/*  90 */         this.terAlertSystemMapper.insertTerAlertSystem(newWifiTerAlertSystem);
/*  91 */         this.appPushService.pushSystemAlertMessage(newWifiTerAlertSystem);
/*     */         
/*  93 */         TerAlertSystem newGatewayTerAlertSystem = new TerAlertSystem();
/*  94 */         newGatewayTerAlertSystem.setAlertType("gateway");
/*  95 */         newGatewayTerAlertSystem.setDelFlag("0");
/*  96 */         newGatewayTerAlertSystem.setDealFlag("0");
/*  97 */         newGatewayTerAlertSystem.setTerSn(diagnosis.getGateway());
/*  98 */         newGatewayTerAlertSystem.setBuildingId(diagnosis.getBuildingId());
/*  99 */         newGatewayTerAlertSystem.setCreateTime(new Date());
/* 100 */         newGatewayTerAlertSystem.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 101 */         newGatewayTerAlertSystem.setWifiType(diagnosis.getWifiType());
/* 102 */         log.info("网关告警：{}", JSON.toJSONString(newGatewayTerAlertSystem));
/* 103 */         this.terAlertSystemMapper.insertTerAlertSystem(newGatewayTerAlertSystem);
/* 104 */         this.appPushService.pushSystemAlertMessage(newGatewayTerAlertSystem);
/*     */         
/* 106 */         Diagnosis diagnosisUpdateParam = new Diagnosis();
/* 107 */         diagnosisUpdateParam.setGateway(diagnosis.getGateway());
/* 108 */         diagnosisUpdateParam.setGatewayOnlineStatus("0");
/* 109 */         this.diagnosisMapper.updateGatewayStatus(diagnosisUpdateParam); return;
/*     */       } 
/* 111 */       if (ObjectUtil.isNotNull(terAlertSystem) && gatewayDiagnosisUpdateTime
/* 112 */         .before(terAlertSystem.getCreateTime()) && "1"
/* 113 */         .equals(diagnosis.getTerOnlineStatus())) {
/*     */         
/* 115 */         Diagnosis diagnosisUpdateParam = new Diagnosis();
/* 116 */         diagnosisUpdateParam.setGateway(diagnosis.getGateway());
/* 117 */         diagnosisUpdateParam.setGatewayOnlineStatus("0");
/* 118 */         this.diagnosisMapper.updateGatewayStatus(diagnosisUpdateParam);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 123 */     if (dealTerList.contains(diagnosis.getTerSn())) {
/*     */       return;
/*     */     }
/* 126 */     if (StringUtils.equals(diagnosis.getTerOnlineStatus(), "0")) {
/* 127 */       dealTerList.add(diagnosis.getTerSn());
/* 128 */       Date terDiagnosisUpdateTime = diagnosis.getTerDiagnosisUpdateTime();
/*     */       
/* 130 */       if (terDiagnosisUpdateTime != null && System.currentTimeMillis() - terDiagnosisUpdateTime.getTime() > 300000L) {
/* 131 */         TerAlertSystem alertQueryParam = new TerAlertSystem();
/* 132 */         alertQueryParam.setTerSn(diagnosis.getTerSn());
/* 133 */         alertQueryParam.setAlertType("lighting");
/* 134 */         TerAlertSystem terAlertSystem = this.terAlertSystemMapper.queryAlertSystemLaste(alertQueryParam);
/* 135 */         if (ObjectUtil.isNull(terAlertSystem) || terDiagnosisUpdateTime.after(terAlertSystem.getCreateTime())) {
/* 136 */           TerAlertSystem newTerAlertSystem = new TerAlertSystem();
/* 137 */           newTerAlertSystem.setAlertType("lighting");
/* 138 */           newTerAlertSystem.setDelFlag("0");
/* 139 */           newTerAlertSystem.setDealFlag("0");
/* 140 */           newTerAlertSystem.setTerSn(diagnosis.getTerSn());
/* 141 */           newTerAlertSystem.setBuildingId(diagnosis.getBuildingId());
/* 142 */           newTerAlertSystem.setBuildingName(diagnosis.getBuildingName());
/* 143 */           newTerAlertSystem.setFloorId(diagnosis.getFloorId());
/* 144 */           newTerAlertSystem.setFloorName(diagnosis.getFloorName());
/* 145 */           newTerAlertSystem.setPointId(diagnosis.getPointId());
/* 146 */           newTerAlertSystem.setPointName(diagnosis.getPointName());
/* 147 */           newTerAlertSystem.setCreateTime(new Date());
/* 148 */           newTerAlertSystem.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 149 */           newTerAlertSystem.setWifiType(diagnosis.getWifiType());
/* 150 */           log.info("灯管告警：{}", JSON.toJSONString(newTerAlertSystem));
/* 151 */           this.terAlertSystemMapper.insertTerAlertSystem(newTerAlertSystem);
/* 152 */           this.appPushService.pushSystemAlertMessage(newTerAlertSystem);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 159 */     if (StringUtils.equals(diagnosis.getDataStatus(), "0")) {
/* 160 */       Date sensorDiagnosisUpdateTime = diagnosis.getSensorDiagnosisUpdateTime();
/* 161 */       TerAlertSystem alertQueryParam = new TerAlertSystem();
/* 162 */       alertQueryParam.setTerSn(diagnosis.getTerSn());
/* 163 */       alertQueryParam.setAlertType("sensor");
/* 164 */       alertQueryParam.setSensorType(diagnosis.getSensorType());
/* 165 */       TerAlertSystem terAlertSystem = this.terAlertSystemMapper.queryAlertSystemLaste(alertQueryParam);
/* 166 */       if (ObjectUtil.isNull(terAlertSystem) || (ObjectUtil.isNotNull(sensorDiagnosisUpdateTime) && sensorDiagnosisUpdateTime.after(terAlertSystem.getCreateTime()))) {
/* 167 */         TerAlertSystem newTerAlertSystem = new TerAlertSystem();
/* 168 */         newTerAlertSystem.setAlertType("sensor");
/* 169 */         newTerAlertSystem.setSensorType(diagnosis.getSensorType());
/* 170 */         newTerAlertSystem.setDelFlag("0");
/* 171 */         newTerAlertSystem.setDealFlag("0");
/* 172 */         newTerAlertSystem.setTerSn(diagnosis.getTerSn());
/* 173 */         newTerAlertSystem.setBuildingId(diagnosis.getBuildingId());
/* 174 */         newTerAlertSystem.setBuildingName(diagnosis.getBuildingName());
/* 175 */         newTerAlertSystem.setFloorId(diagnosis.getFloorId());
/* 176 */         newTerAlertSystem.setFloorName(diagnosis.getFloorName());
/* 177 */         newTerAlertSystem.setPointId(diagnosis.getPointId());
/* 178 */         newTerAlertSystem.setPointName(diagnosis.getPointName());
/* 179 */         newTerAlertSystem.setCreateTime(new Date());
/* 180 */         newTerAlertSystem.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 181 */         newTerAlertSystem.setWifiType(diagnosis.getWifiType());
/* 182 */         log.info("功能盒告警：{}", JSON.toJSONString(newTerAlertSystem));
/* 183 */         this.terAlertSystemMapper.insertTerAlertSystem(newTerAlertSystem);
/* 184 */         this.appPushService.pushSystemAlertMessage(newTerAlertSystem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/task/SystemAlertTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */