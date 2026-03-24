/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPointInfo;
/*     */ import com.ruoyi.iotlighting.domain.TerBuildingPointInfo;
/*     */ import com.ruoyi.iotlighting.domain.TerInfo;
/*     */ import com.ruoyi.iotlighting.mapper.TerBuildingPointInfoMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingPointInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerBuildingPointInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerSensorStatusService;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ @Service
/*     */ public class TerBuildingPointInfoServiceImpl
/*     */   implements ITerBuildingPointInfoService
/*     */ {
/*  41 */   private static final Logger log = LoggerFactory.getLogger(TerBuildingPointInfoServiceImpl.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private TerBuildingPointInfoMapper terBuildingPointInfoMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ITerInfoService terInfoService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ITerSensorStatusService terSensorStatusService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private IBuildingPointInfoService buildingPointInfoService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private TerInfoMapper terInfoMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */ 
/*     */   
/*     */   public TerBuildingPointInfo selectTerBuildingPointInfoByTerSn(String terSn) {
/*  69 */     return this.terBuildingPointInfoMapper.selectTerBuildingPointInfoByTerSn(terSn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TerBuildingPointInfo> selectTerBuildingPointInfoList(TerBuildingPointInfo terBuildingPointInfo) {
/*  80 */     return this.terBuildingPointInfoMapper.selectTerBuildingPointInfoList(terBuildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int insertTerBuildingPointInfo(TerBuildingPointInfo terBuildingPointInfo) {
/*  92 */     String terSn = terBuildingPointInfo.getTerSn();
/*  93 */     String gateway = terBuildingPointInfo.getGateway();
/*  94 */     TerInfo terInfo = this.terInfoService.selectTerInfoBySn(terSn);
/*  95 */     if (ObjectUtils.isEmpty(terInfo)) {
/*  96 */       throw new ServiceException(MessageUtils.message("terBuildingPointInfo.bind.error", new Object[0]));
/*     */     }
/*  98 */     terBuildingPointInfo.setCreatedTime(new Date());
/*  99 */     int count = this.terBuildingPointInfoMapper.insertTerBuildingPointInfo(terBuildingPointInfo);
/*     */     
/* 101 */     if (StringUtils.isNotEmpty(gateway)) {
/* 102 */       TerInfo terInfoUpdate = new TerInfo();
/* 103 */       terInfoUpdate.setId(terInfo.getId());
/* 104 */       terInfoUpdate.setGateway(terBuildingPointInfo.getGateway());
/* 105 */       this.terInfoMapper.updateTerInfo(terInfoUpdate);
/* 106 */       terInfo.setGateway(terBuildingPointInfo.getGateway());
/*     */     } 
/*     */     
/* 109 */     if (StringUtils.isNotEmpty(terInfo.getGateway())) {
/* 110 */       terInfo = this.terInfoService.selectTerInfoBySn(terSn);
/* 111 */       SysMqttBo mqttBo = new SysMqttBo();
/* 112 */       mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
/* 113 */       mqttBo.setQos(Integer.valueOf(1));
/* 114 */       mqttBo.setRetained(true);
/* 115 */       mqttBo.setData(terInfo.buildingPayload());
/* 116 */       this.sysMqttService.sendMsgToTopic(mqttBo);
/* 117 */       log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */     } 
/* 119 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateTerBuildingPointInfo(TerBuildingPointInfo terBuildingPointInfo) {
/* 130 */     return this.terBuildingPointInfoMapper.updateTerBuildingPointInfo(terBuildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerBuildingPointInfoByTerSns(String[] terSns) {
/* 141 */     return this.terBuildingPointInfoMapper.deleteTerBuildingPointInfoByTerSns(terSns);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerBuildingPointInfoByTerSn(String terSn) {
/* 153 */     TerInfo terInfo = this.terInfoService.selectTerInfoBySn(terSn);
/* 154 */     int count = this.terBuildingPointInfoMapper.deleteTerBuildingPointInfoByTerSn(terSn);
/* 155 */     SysMqttBo mqttBo = new SysMqttBo();
/* 156 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/update/" + terInfo.getSn());
/* 157 */     mqttBo.setQos(Integer.valueOf(1));
/* 158 */     mqttBo.setRetained(false);
/* 159 */     mqttBo.setData(terInfo.buildingPayload());
/* 160 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 161 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/* 162 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public TerBuildingPointInfo selectTerBuildingPointInfoByPointid(Long pointId) {
/* 167 */     return this.terBuildingPointInfoMapper.selectTerBuildingPointInfoByPointId(pointId);
/*     */   }
/*     */ 
/*     */   
/*     */   public AjaxResult checkPointAndTer(TerBuildingPointInfo terBuildingPointInfo) {
/* 172 */     TerBuildingPointInfo dbTerBuildingPointInfo = selectTerBuildingPointInfoByTerSn(terBuildingPointInfo.getTerSn());
/* 173 */     if (!ObjectUtils.isEmpty(dbTerBuildingPointInfo)) {
/* 174 */       return AjaxResult.error(MessageUtils.message("terBuildingPointInfo.ter.error", new Object[0]));
/*     */     }
/*     */     
/* 177 */     BuildingPointInfo buildingPointInfo = this.buildingPointInfoService.selectBuildingPointInfoById(terBuildingPointInfo.getPointId());
/* 178 */     if (ObjectUtils.isEmpty(buildingPointInfo)) {
/* 179 */       return AjaxResult.error(MessageUtils.message("terBuildingPointInfo.point.error", new Object[0]));
/*     */     }
/* 181 */     if (StringUtils.isNotEmpty(buildingPointInfo.getTerSn())) {
/* 182 */       return AjaxResult.error(MessageUtils.message("terBuildingPointInfo.terPoint.error", new Object[0]) + buildingPointInfo.getTerSn());
/*     */     }
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
/* 196 */     return AjaxResult.success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void installConfirm(String terSn) {
/* 205 */     TerInfo terInfo = this.terInfoService.selectTerInfoBySn(terSn);
/* 206 */     if (ObjectUtils.isEmpty(terInfo)) {
/* 207 */       throw new ServiceException(MessageUtils.message("terBuildingPointInfo.bind.error", new Object[0]));
/*     */     }
/* 209 */     SysMqttBo mqttBo = new SysMqttBo();
/* 210 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/install/" + terInfo.getSn());
/* 211 */     mqttBo.setQos(Integer.valueOf(1));
/* 212 */     mqttBo.setRetained(false);
/* 213 */     mqttBo.setData("1");
/* 214 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 215 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTerInfoBySnOrUpc(String key) {
/* 220 */     List<TerInfo> terInfoList = this.terInfoService.selectTerInfoBySnOrUpc(key);
/* 221 */     if (terInfoList.size() == 1) {
/* 222 */       return ((TerInfo)terInfoList.get(0)).getSn();
/*     */     }
/*     */     
/* 225 */     for (TerInfo terInfo : terInfoList) {
/* 226 */       if (StringUtils.equals(terInfo.getSn(), key)) {
/* 227 */         return terInfo.getSn();
/*     */       }
/*     */     } 
/*     */     
/* 231 */     for (TerInfo terInfo : terInfoList) {
/* 232 */       if (StringUtils.equals(terInfo.getUpc(), key)) {
/* 233 */         return terInfo.getSn();
/*     */       }
/*     */     } 
/* 236 */     throw new ServiceException(MessageUtils.message("terBuildingPointInfo.bind.error", new Object[0]));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerBuildingPointInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */