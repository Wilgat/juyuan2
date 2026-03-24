/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.iotlighting.domain.TerInfo;
/*     */ import com.ruoyi.iotlighting.domain.TerSensorStatus;
/*     */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerSensorStatusMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.ITerSensorStatusService;
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
/*     */ 
/*     */ @Service
/*     */ public class TerSensorStatusServiceImpl
/*     */   implements ITerSensorStatusService
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(TerSensorStatusServiceImpl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private TerSensorStatusMapper terSensorStatusMapper;
/*     */ 
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
/*     */   
/*     */   public List<TerSensorStatus> selectTerSensorStatusByTerSn(String terSn) {
/*  48 */     return this.terSensorStatusMapper.selectTerSensorStatusByTerSn(terSn);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TerSensorStatus> selectTerSensorStatusListAlarm(TerSensorStatus terSensorStatus) {
/*  53 */     return this.terSensorStatusMapper.selectTerSensorStatusListAlarm(terSensorStatus);
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
/*     */   public List<TerSensorStatus> selectTerSensorStatusList(TerSensorStatus terSensorStatus) {
/*  65 */     return this.terSensorStatusMapper.selectTerSensorStatusList(terSensorStatus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertTerSensorStatus(TerSensorStatus terSensorStatus) {
/*  76 */     return this.terSensorStatusMapper.insertTerSensorStatus(terSensorStatus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateTerSensorStatus(TerSensorStatus terSensorStatus) {
/*  87 */     return this.terSensorStatusMapper.updateTerSensorStatus(terSensorStatus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateTerSensorConfig(TerSensorStatus terSensorStatus) {
/*  98 */     int count = this.terSensorStatusMapper.updateTerSensorStatus(terSensorStatus);
/*  99 */     List<String> terSnList = terSensorStatus.getTerSnList();
/* 100 */     if (CollectionUtil.isEmpty(terSnList)) {
/* 101 */       throw new ServiceException("修改失败，参数不全");
/*     */     }
/* 103 */     for (String terSn : terSnList) {
/* 104 */       TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(terSn);
/* 105 */       SysMqttBo mqttBo = new SysMqttBo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       mqttBo.setTopic("gateway/" + terInfoDb.getGateway() + "/lightinglist/" + terInfoDb.getSn());
/* 113 */       mqttBo.setQos(Integer.valueOf(1));
/* 114 */       mqttBo.setRetained(true);
/* 115 */       mqttBo.setData(terInfoDb.buildingPayload());
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
/*     */   public int deleteTerSensorStatusByTerSns(String[] terSns) {
/* 130 */     return this.terSensorStatusMapper.deleteTerSensorStatusByTerSns(terSns);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerSensorStatusByTerSn(String terSn) {
/* 141 */     return this.terSensorStatusMapper.deleteTerSensorStatusByTerSn(terSn);
/*     */   }
/*     */ 
/*     */   
/*     */   public int updateSertTerSensorStatus(TerSensorStatus terSensorStatus) {
/* 146 */     return this.terSensorStatusMapper.updateSertTerSensorStatus(terSensorStatus);
/*     */   }
/*     */ 
/*     */   
/*     */   public TerSensorStatus selectTerSensorByType(String sn, String type) {
/* 151 */     TerSensorStatus param = new TerSensorStatus();
/* 152 */     param.setTerSn(sn);
/* 153 */     param.setSensorType(type);
/* 154 */     return this.terSensorStatusMapper.selectTerSensor(param);
/*     */   }
/*     */ 
/*     */   
/*     */   public int deleteTerLightStatusByTerSn(String sn) {
/* 159 */     return this.terSensorStatusMapper.deleteTerLightStatusByTerSn(sn);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerSensorStatusServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */