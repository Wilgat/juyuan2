/*     */ package com.ruoyi.iotlighting.task;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrolLogDetail;
/*     */ import com.ruoyi.iotlighting.domain.TerAlert;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolLogDetailMapper;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolLogMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IAppPushService;
/*     */ import com.ruoyi.iotlighting.service.ITerAlertService;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component("patrolTask")
/*     */ public class PatrolTask
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(PatrolTask.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolLogMapper buildingPatrolLogMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolLogDetailMapper buildingPatrolLogDetailMapper;
/*     */   
/*     */   @Autowired
/*     */   private IAppPushService appPushService;
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */   
/*     */   @Autowired
/*     */   private ITerAlertService terAlertService;
/*     */ 
/*     */   
/*     */   public void statusChange() {
/*  57 */     BuildingPatrolLogDetail queryParam = new BuildingPatrolLogDetail();
/*  58 */     queryParam.setCompleteFlag("0");
/*  59 */     List<BuildingPatrolLogDetail> buildingPatrolLogDetailList = this.buildingPatrolLogDetailMapper.selectCurrentPatrolLogDetailList(queryParam);
/*  60 */     if (CollectionUtil.isEmpty(buildingPatrolLogDetailList)) {
/*     */       return;
/*     */     }
/*  63 */     Map<Long, List<BuildingPatrolLogDetail>> buildingPatrolLogMap = (Map<Long, List<BuildingPatrolLogDetail>>)buildingPatrolLogDetailList.stream().collect(Collectors.groupingBy(BuildingPatrolLogDetail::getPatrolLogId));
/*     */     
/*  65 */     Set<Map.Entry<Long, List<BuildingPatrolLogDetail>>> set = buildingPatrolLogMap.entrySet();
/*  66 */     for (Map.Entry<Long, List<BuildingPatrolLogDetail>> entrySet : set) {
/*  67 */       List<BuildingPatrolLogDetail> list = entrySet.getValue();
/*  68 */       Long patrolLogId = entrySet.getKey();
/*     */       
/*  70 */       Date perPointTime = ((BuildingPatrolLogDetail)list.get(0)).getStartTime();
/*     */       
/*  72 */       Date now = new Date();
/*  73 */       for (BuildingPatrolLogDetail buildingPatrolLogDetail : list) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  81 */         if (ObjectUtil.isNull(buildingPatrolLogDetail.getWaitTime()) || ObjectUtil.isNull(buildingPatrolLogDetail.getTolerantTime())) {
/*     */           continue;
/*     */         }
/*     */         
/*  85 */         Date date = buildingPatrolLogDetail.getCreateTime();
/*     */         
/*  87 */         if (ObjectUtil.isNotEmpty(date)) {
/*  88 */           perPointTime = date;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*  93 */         Long maxWaitTime = Long.valueOf(buildingPatrolLogDetail.getWaitTime().longValue() + buildingPatrolLogDetail.getTolerantTime().longValue());
/*  94 */         Date maxDate = DateUtils.addSeconds(perPointTime, maxWaitTime.intValue());
/*     */         
/*  96 */         if (maxDate.after(now)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 101 */         BuildingPatrolLogDetail detail = new BuildingPatrolLogDetail();
/* 102 */         detail.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 103 */         detail.setPatrolId(buildingPatrolLogDetail.getPatrolId());
/* 104 */         detail.setPatrolLogId(patrolLogId);
/* 105 */         detail.setCompleteFlag("2");
/* 106 */         detail.setCreateTime(new Date());
/* 107 */         this.buildingPatrolLogDetailMapper.insertBuildingPatrolLogDetail(detail);
/*     */ 
/*     */         
/* 110 */         TerAlert terAlert = new TerAlert();
/* 111 */         terAlert.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 112 */         terAlert.setTerSn(buildingPatrolLogDetail.getTerSn());
/* 113 */         terAlert.setCreatedTime(new Date());
/* 114 */         terAlert.setData(DateUtils.getTime());
/* 115 */         terAlert.setDealFlag("0");
/* 116 */         terAlert.setSensorType("patrol");
/* 117 */         this.terAlertService.insertTerAlert(terAlert);
/* 118 */         Long alertId = terAlert.getId();
/*     */ 
/*     */ 
/*     */         
/* 122 */         SysMqttBo mqttBo = new SysMqttBo();
/* 123 */         mqttBo.setTopic("gateway/" + buildingPatrolLogDetail.getGateway() + "/lightingstatus/patrol/" + buildingPatrolLogDetail.getTerSn());
/* 124 */         mqttBo.setQos(Integer.valueOf(1));
/* 125 */         mqttBo.setRetained(true);
/* 126 */         mqttBo.setData("0");
/* 127 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 128 */         log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */ 
/*     */         
/* 131 */         mqttBo = new SysMqttBo();
/* 132 */         mqttBo.setTopic("server/patrol");
/* 133 */         mqttBo.setQos(Integer.valueOf(1));
/* 134 */         mqttBo.setRetained(false);
/* 135 */         mqttBo.setData("0");
/* 136 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 137 */         log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */ 
/*     */         
/* 140 */         TerAlert param = new TerAlert();
/* 141 */         param.setId(alertId);
/* 142 */         List<TerAlert> terAlertList = this.terAlertService.selectTerAlertListNotLogin(param);
/* 143 */         mqttBo = new SysMqttBo();
/* 144 */         mqttBo.setTopic("server/alarm");
/* 145 */         mqttBo.setQos(Integer.valueOf(1));
/* 146 */         mqttBo.setData(JSON.toJSONString(terAlertList.get(0)));
/* 147 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/*     */         
/*     */         try {
/* 150 */           this.appPushService.pushMessage(terAlertList.get(0));
/* 151 */         } catch (Exception e) {
/* 152 */           log.error("报警信息发送失败", e);
/*     */         } 
/*     */ 
/*     */         
/* 156 */         if (buildingPatrolLogDetail == list.get(list.size() - 1)) {
/* 157 */           BuildingPatrolLog buildingPatrolLog = new BuildingPatrolLog();
/* 158 */           buildingPatrolLog.setCompleteFlag("2");
/* 159 */           buildingPatrolLog.setCompleteTime(new Date());
/* 160 */           buildingPatrolLog.setId(entrySet.getKey());
/* 161 */           this.buildingPatrolLogMapper.updateBuildingPatrolLog(buildingPatrolLog);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/task/PatrolTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */