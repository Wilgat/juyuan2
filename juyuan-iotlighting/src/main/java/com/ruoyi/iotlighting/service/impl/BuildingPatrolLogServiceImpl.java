/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrolLogDetail;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolLogDetailMapper;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolLogMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingPatrolLogService;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BuildingPatrolLogServiceImpl
/*     */   implements IBuildingPatrolLogService
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(BuildingPatrolLogServiceImpl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolLogMapper buildingPatrolLogMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolLogDetailMapper buildingPatrolLogDetailMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */ 
/*     */ 
/*     */   
/*     */   public BuildingPatrolLog selectBuildingPatrolLogById(Long id) {
/*  54 */     return this.buildingPatrolLogMapper.selectBuildingPatrolLogById(id);
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
/*     */   public List<BuildingPatrolLog> selectBuildingPatrolLogList(BuildingPatrolLog buildingPatrolLog) {
/*  66 */     return this.buildingPatrolLogMapper.selectBuildingPatrolLogList(buildingPatrolLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertBuildingPatrolLog(BuildingPatrolLog buildingPatrolLog) {
/*  77 */     buildingPatrolLog.setCreateTime(DateUtils.getNowDate());
/*  78 */     return this.buildingPatrolLogMapper.insertBuildingPatrolLog(buildingPatrolLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateBuildingPatrolLog(BuildingPatrolLog buildingPatrolLog) {
/*  89 */     return this.buildingPatrolLogMapper.updateBuildingPatrolLog(buildingPatrolLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPatrolLogByIds(Long[] ids) {
/* 100 */     return this.buildingPatrolLogMapper.deleteBuildingPatrolLogByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPatrolLogById(Long id) {
/* 111 */     return this.buildingPatrolLogMapper.deleteBuildingPatrolLogById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BuildingPatrolLogDetail> selectCurrentPatrolLogDetailList(BuildingPatrolLogDetail buildingPatrolLogDetail) {
/* 116 */     List<BuildingPatrolLogDetail> buildingPatrolLogDetailList = this.buildingPatrolLogDetailMapper.selectCurrentPatrolLogDetailList(buildingPatrolLogDetail);
/* 117 */     return buildingPatrolLogDetailList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handlePatrolDetailData(String gateway, String terSn) {
/* 123 */     BuildingPatrolLogDetail queryParam = new BuildingPatrolLogDetail();
/* 124 */     queryParam.setTerSn(terSn);
/* 125 */     queryParam.setCompleteFlag("0");
/* 126 */     List<BuildingPatrolLogDetail> buildingPatrolLogDetailList = this.buildingPatrolLogDetailMapper.selectCurrentPatrolLogDetailList(queryParam);
/* 127 */     if (CollectionUtil.isEmpty(buildingPatrolLogDetailList) || buildingPatrolLogDetailList.size() > 1) {
/* 128 */       throw new ServiceException(MessageUtils.message("buildingPatrolLog.param.error", new Object[0]));
/*     */     }
/*     */     
/* 131 */     queryParam = new BuildingPatrolLogDetail();
/* 132 */     queryParam.setPatrolLogId(((BuildingPatrolLogDetail)buildingPatrolLogDetailList.get(0)).getPatrolLogId());
/* 133 */     List<BuildingPatrolLogDetail> currentBuildingPatrolLogDetailList = this.buildingPatrolLogDetailMapper.selectCurrentPatrolLogDetailList(queryParam);
/*     */     
/* 135 */     Date perPointTime = ((BuildingPatrolLogDetail)buildingPatrolLogDetailList.get(0)).getStartTime();
/*     */     
/* 137 */     Date now = new Date();
/*     */     
/* 139 */     boolean isLastPoint = false;
/* 140 */     for (BuildingPatrolLogDetail buildingPatrolLogDetail : currentBuildingPatrolLogDetailList) {
/*     */       
/* 142 */       Date date = buildingPatrolLogDetail.getCreateTime();
/*     */       
/* 144 */       if (ObjectUtil.isNotEmpty(date)) {
/* 145 */         perPointTime = date;
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 151 */       Long maxWaitTime = Long.valueOf(buildingPatrolLogDetail.getWaitTime().longValue() + buildingPatrolLogDetail.getTolerantTime().longValue());
/* 152 */       Long minWaitTime = Long.valueOf(buildingPatrolLogDetail.getWaitTime().longValue() - buildingPatrolLogDetail.getTolerantTime().longValue());
/* 153 */       Date maxDate = DateUtils.addSeconds(perPointTime, maxWaitTime.intValue());
/* 154 */       Date minDate = DateUtils.addSeconds(perPointTime, minWaitTime.intValue());
/* 155 */       if (minDate.after(now))
/*     */       {
/* 157 */         throw new ServiceException(MessageUtils.message("buildingPatrolLog.time.error", new Object[0]));
/*     */       }
/* 159 */       if (maxDate.before(now))
/*     */       {
/* 161 */         throw new ServiceException(MessageUtils.message("buildingPatrolLog.timeout.error", new Object[0]));
/*     */       }
/*     */       
/* 164 */       if (!StringUtils.equals(buildingPatrolLogDetail.getTerSn(), terSn))
/*     */       {
/* 166 */         throw new ServiceException(MessageUtils.message("buildingPatrolLog.number.error", new Object[0]));
/*     */       }
/*     */       
/* 169 */       if (buildingPatrolLogDetail == currentBuildingPatrolLogDetailList.get(currentBuildingPatrolLogDetailList.size() - 1)) {
/* 170 */         isLastPoint = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 176 */     BuildingPatrolLogDetail detail = new BuildingPatrolLogDetail();
/* 177 */     detail.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 178 */     detail.setPatrolId(((BuildingPatrolLogDetail)buildingPatrolLogDetailList.get(0)).getPatrolId());
/* 179 */     detail.setPatrolLogId(((BuildingPatrolLogDetail)buildingPatrolLogDetailList.get(0)).getPatrolLogId());
/* 180 */     detail.setCreateTime(new Date());
/* 181 */     detail.setCompleteFlag("1");
/*     */     
/* 183 */     if (CollectionUtil.isNotEmpty(this.buildingPatrolLogDetailMapper.selectBuildingPatrolLogDetail(detail))) {
/*     */       return;
/*     */     }
/* 186 */     this.buildingPatrolLogDetailMapper.insertBuildingPatrolLogDetail(detail);
/*     */ 
/*     */     
/* 189 */     BuildingPatrolLog buildingPatrolLog = this.buildingPatrolLogMapper.selectBuildingPatrolLogByRouteId(((BuildingPatrolLogDetail)buildingPatrolLogDetailList.get(0)).getRouteId());
/* 190 */     if (ObjectUtil.isEmpty(buildingPatrolLog) || buildingPatrolLog.getPatrolPointComplete().longValue() >= buildingPatrolLog.getPatrolPointTotal().longValue()) {
/* 191 */       throw new ServiceException(MessageUtils.message("buildingPatrolLog.param.error", new Object[0]));
/*     */     }
/* 193 */     Long patrolPointComplete = Long.valueOf(buildingPatrolLog.getPatrolPointComplete().longValue() + 1L);
/*     */     
/* 195 */     buildingPatrolLog.setPatrolPointComplete(patrolPointComplete);
/*     */     
/* 197 */     if (isLastPoint) {
/* 198 */       buildingPatrolLog.setCompleteFlag("1");
/* 199 */       buildingPatrolLog.setCompleteTime(new Date());
/*     */       
/* 201 */       for (BuildingPatrolLogDetail buildingPatrolLogDetail : currentBuildingPatrolLogDetailList) {
/*     */         
/* 203 */         SysMqttBo sysMqttBo = new SysMqttBo();
/* 204 */         sysMqttBo.setTopic("gateway/" + gateway + "/lightingstatus/patrol/" + buildingPatrolLogDetail.getTerSn());
/* 205 */         sysMqttBo.setQos(Integer.valueOf(1));
/* 206 */         sysMqttBo.setRetained(true);
/* 207 */         sysMqttBo.setData("0");
/* 208 */         this.sysMqttService.sendMsgToTopic(sysMqttBo);
/* 209 */         log.info("发送消息:{}", JSONObject.toJSONString(sysMqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     this.buildingPatrolLogMapper.updateBuildingPatrolLog(buildingPatrolLog);
/*     */ 
/*     */     
/* 216 */     SysMqttBo mqttBo = new SysMqttBo();
/* 217 */     mqttBo.setTopic("server/patrol");
/* 218 */     mqttBo.setQos(Integer.valueOf(1));
/* 219 */     mqttBo.setRetained(false);
/* 220 */     mqttBo.setData("0");
/* 221 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 222 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingPatrolLogServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */