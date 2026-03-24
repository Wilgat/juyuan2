/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.collection.CollUtil;
/*     */ import cn.hutool.core.lang.UUID;
/*     */ import cn.hutool.http.HttpUtil;
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.AppPush;
/*     */ import com.ruoyi.iotlighting.domain.TerAlert;
/*     */ import com.ruoyi.iotlighting.domain.TerAlertSystem;
/*     */ import com.ruoyi.iotlighting.mapper.AppPushMapper;
/*     */ import com.ruoyi.iotlighting.service.IAppPushService;
/*     */ import com.ruoyi.system.service.ISysDictDataService;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.CollectionUtils;
/*     */ 
/*     */ @Service
/*     */ public class AppPushServiceImpl implements IAppPushService {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(AppPushServiceImpl.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private AppPushMapper appPushMapper;
/*     */   
/*     */   @Autowired
/*     */   private ISysDictDataService sysDictDataService;
/*     */   
/*     */   private static final String PUSH_URL = "https://fc-mp-12e3a2f4-8eb9-4ba9-86a1-137c421aaa07.next.bspapp.com/unipush";
/*     */ 
/*     */   
/*     */   public void pushMessage(TerAlert terAlert) {
/*  40 */     log.info("推送消息：{}", JSON.toJSONString(terAlert));
/*     */ 
/*     */     
/*  43 */     List<String> cidListFromAlarm = this.appPushMapper.selectAppPushCidList(terAlert);
/*     */     
/*  45 */     List<String> cidListFromAdmin = this.appPushMapper.selectAdminPushCidList();
/*     */     
/*  47 */     Set<String> cidSet = new HashSet<>();
/*     */     
/*  49 */     if (!CollectionUtils.isEmpty(cidListFromAlarm)) {
/*  50 */       cidSet.addAll(cidListFromAlarm);
/*     */     }
/*     */     
/*  53 */     if (!CollectionUtils.isEmpty(cidListFromAdmin)) {
/*  54 */       cidSet.addAll(cidListFromAdmin);
/*     */     }
/*  56 */     List<String> pushCidList = (List<String>)cidSet.stream().filter(cid -> (StringUtils.isNotEmpty(cid) && !StringUtils.equals(cid, "null"))).collect(Collectors.toList());
/*     */     
/*  58 */     if (CollUtil.isEmpty(pushCidList)) {
/*     */       return;
/*     */     }
/*  61 */     log.info("推送消息cid：{}", JSON.toJSONString(pushCidList));
/*  62 */     AppPush appPush = new AppPush();
/*  63 */     appPush.setCid(StringUtils.join(pushCidList, ","));
/*  64 */     appPush.setTitle(terAlert.getSensorType());
/*  65 */     appPush.setContent("位置：" + terAlert.getBuildingName() + "," + terAlert.getFloorName() + "," + terAlert.getPointName());
/*  66 */     appPush.setRequestId(UUID.randomUUID().toString());
/*  67 */     HttpUtil.post("https://fc-mp-12e3a2f4-8eb9-4ba9-86a1-137c421aaa07.next.bspapp.com/unipush", JSONObject.toJSONString(appPush, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void pushSystemAlertMessage(TerAlertSystem terAlertSystem) {
/*  72 */     log.info("推送系统告警消息：{}", JSON.toJSONString(terAlertSystem));
/*  73 */     TerAlert queryParam = new TerAlert();
/*  74 */     queryParam.setBuildingId(terAlertSystem.getBuildingId());
/*  75 */     queryParam.setSensorType("diagnosis");
/*     */     
/*  77 */     List<String> cidListFromAlarm = this.appPushMapper.selectAppPushCidList(queryParam);
/*     */     
/*  79 */     List<String> cidListFromAdmin = this.appPushMapper.selectAdminPushCidList();
/*     */     
/*  81 */     Set<String> cidSet = new HashSet<>();
/*     */     
/*  83 */     if (!CollectionUtils.isEmpty(cidListFromAlarm)) {
/*  84 */       cidSet.addAll(cidListFromAlarm);
/*     */     }
/*     */     
/*  87 */     if (!CollectionUtils.isEmpty(cidListFromAdmin)) {
/*  88 */       cidSet.addAll(cidListFromAdmin);
/*     */     }
/*  90 */     List<String> pushCidList = (List<String>)cidSet.stream().filter(cid -> (StringUtils.isNotEmpty(cid) && !StringUtils.equals(cid, "null"))).collect(Collectors.toList());
/*     */     
/*  92 */     if (CollUtil.isEmpty(pushCidList)) {
/*     */       return;
/*     */     }
/*  95 */     log.info("推送消息cid：{}", JSON.toJSONString(pushCidList));
/*  96 */     AppPush appPush = new AppPush();
/*  97 */     appPush.setCid(StringUtils.join(pushCidList, ","));
/*  98 */     appPush.setTitle(terAlertSystem.getAlertType());
/*  99 */     String content = "位置：" + terAlertSystem.getBuildingName();
/* 100 */     if (!StringUtils.equals(terAlertSystem.getAlertType(), "wifi") && !StringUtils.equals(terAlertSystem.getAlertType(), "gateway")) {
/* 101 */       content = content + "," + terAlertSystem.getFloorName();
/* 102 */       content = content + "," + terAlertSystem.getPointName();
/*     */     } 
/* 104 */     appPush.setContent(content);
/* 105 */     appPush.setRequestId(UUID.randomUUID().toString());
/* 106 */     HttpUtil.post("https://fc-mp-12e3a2f4-8eb9-4ba9-86a1-137c421aaa07.next.bspapp.com/unipush", JSONObject.toJSONString(appPush, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/AppPushServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */