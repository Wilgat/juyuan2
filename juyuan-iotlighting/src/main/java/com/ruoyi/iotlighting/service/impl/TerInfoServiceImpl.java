/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
/*     */ import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
/*     */ import com.ruoyi.iotlighting.domain.TerInfo;
/*     */ import com.ruoyi.iotlighting.domain.TerSensorStatus;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerBuildingPointInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerInfoService;
/*     */ import com.ruoyi.iotlighting.service.ITerSensorStatusService;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.core.io.ClassPathResource;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.util.ObjectUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class TerInfoServiceImpl
/*     */   implements ITerInfoService
/*     */ {
/*  45 */   private static final Logger log = LoggerFactory.getLogger(TerInfoServiceImpl.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private TerInfoMapper terInfoMapper;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private IBuildingFloorInfoService buildingFloorInfoService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ITerSensorStatusService terSensorStatusService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ITerBuildingPointInfoService terBuildingPointInfoService;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolMapper buildingPatrolMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   public TerInfo selectTerInfoById(Long id) {
/*  74 */     return this.terInfoMapper.selectTerInfoById(id);
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
/*     */   public List<TerInfo> selectTerInfoList(TerInfo terInfo) {
/*  86 */     return this.terInfoMapper.selectTerInfoStatusList(terInfo);
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
/*     */   public List<TerInfo> selectTerInfoListAll(TerInfo terInfo) {
/*  98 */     return this.terInfoMapper.selectTerInfoStatusListAll(terInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TerInfo> selectTerInfoListNotLogin(TerInfo terInfo) {
/* 103 */     return this.terInfoMapper.selectTerInfoStatusListNotLogin(terInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int insertTerInfo(TerInfo terInfo) {
/* 115 */     TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(terInfo.getSn());
/* 116 */     if (!ObjectUtils.isEmpty(terInfoDb)) {
/* 117 */       throw new ServiceException(MessageUtils.message("terInfo.sn.error", new Object[0]));
/*     */     }
/* 119 */     TerInfo queryParam = new TerInfo();
/* 120 */     queryParam.setSn(terInfo.getUpc());
/* 121 */     List<TerInfo> terInfoList = this.terInfoMapper.selectTerInfoBySnOrUpc(queryParam);
/* 122 */     if (!ObjectUtils.isEmpty(terInfoList)) {
/* 123 */       throw new ServiceException(MessageUtils.message("terInfo.upc.error", new Object[0]));
/*     */     }
/*     */     
/* 126 */     terInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 127 */     terInfo.setTerStatus("offline");
/* 128 */     terInfo.setDelFlag("0");
/* 129 */     terInfo.setCreatedTime(new Date());
/* 130 */     terInfo.setUpdatedTime(new Date());
/*     */     
/* 132 */     List<TerSensorStatus> terSensorStatusList = terInfo.getTerSensorStatusList();
/* 133 */     for (TerSensorStatus terSensorStatus : terSensorStatusList) {
/* 134 */       terSensorStatus.setTerSn(terInfo.getSn());
/* 135 */       terSensorStatus.setData("——");
/* 136 */       terSensorStatus.setUpdatedTime(new Date());
/* 137 */       terSensorStatus.setSensorStatus("offline");
/* 138 */       this.terSensorStatusService.insertTerSensorStatus(terSensorStatus);
/*     */     } 
/*     */     
/* 141 */     if (StringUtils.isNotEmpty(terInfo.getGateway())) {
/* 142 */       SysMqttBo mqttBo = new SysMqttBo();
/* 143 */       mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/add/" + terInfo.getSn());
/* 144 */       mqttBo.setQos(Integer.valueOf(1));
/* 145 */       mqttBo.setRetained(false);
/* 146 */       mqttBo.setData(terInfo.buildingPayload());
/* 147 */       this.sysMqttService.sendMsgToTopic(mqttBo);
/*     */     } 
/* 149 */     return this.terInfoMapper.insertTerInfo(terInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int updateTerInfo(TerInfo terInfo) {
/* 161 */     List<TerInfo> terInfoList = this.terInfoMapper.selectTerInfoBySnOrUpc(terInfo);
/* 162 */     if (!ObjectUtils.isEmpty(terInfoList)) {
/* 163 */       for (TerInfo item : terInfoList) {
/* 164 */         if (item.getId().longValue() != terInfo.getId().longValue()) {
/* 165 */           throw new ServiceException(MessageUtils.message("terInfo.sn.error", new Object[0]));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 170 */     TerInfo terInfoOld = this.terInfoMapper.selectTerInfoById(terInfo.getId());
/* 171 */     String oldSn = terInfoOld.getSn();
/*     */     
/* 173 */     List<TerSensorStatus> terSensorStatusList = terInfo.getTerSensorStatusList();
/*     */     
/* 175 */     this.terSensorStatusService.deleteTerSensorStatusByTerSn(oldSn);
/* 176 */     this.terSensorStatusService.deleteTerLightStatusByTerSn(oldSn);
/* 177 */     boolean patrolFlag = false;
/* 178 */     for (TerSensorStatus terSensorStatus : terSensorStatusList) {
/* 179 */       terSensorStatus.setTerSn(terInfo.getSn());
/* 180 */       terSensorStatus.setData("——");
/* 181 */       terSensorStatus.setUpdatedTime(new Date());
/* 182 */       terSensorStatus.setSensorStatus("offline");
/* 183 */       if (StringUtils.isEmpty(terSensorStatus.getSensorConfig())) {
/* 184 */         terSensorStatus.setSensorConfig("0");
/*     */       }
/* 186 */       this.terSensorStatusService.insertTerSensorStatus(terSensorStatus);
/*     */       
/* 188 */       if (StringUtils.equals(terSensorStatus.getSensorType(), "patrol")) {
/* 189 */         patrolFlag = true;
/*     */       }
/*     */     } 
/* 192 */     terInfo.setUpdatedTime(new Date());
/*     */     
/* 194 */     if (!patrolFlag) {
/* 195 */       this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(oldSn);
/*     */     }
/*     */     
/* 198 */     TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(oldSn);
/* 199 */     int count = this.terInfoMapper.updateTerInfo(terInfo);
/*     */     
/* 201 */     if (StringUtils.isNotEmpty(terInfo.getGateway())) {
/* 202 */       terInfo = this.terInfoMapper.selectTerInfoBySn(terInfo.getSn());
/*     */       
/* 204 */       if (!StringUtils.equals(terInfoDb.getGateway(), terInfo.getGateway())) {
/*     */ 
/*     */         
/* 207 */         SysMqttBo mqttBo = new SysMqttBo();
/* 208 */         mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
/* 209 */         mqttBo.setQos(Integer.valueOf(1));
/* 210 */         mqttBo.setRetained(true);
/* 211 */         mqttBo.setData(terInfo.buildingPayload());
/* 212 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 213 */         log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */         
/* 215 */         delRetainedTopic(terInfoDb);
/*     */       } else {
/*     */         
/* 218 */         SysMqttBo mqttBo = new SysMqttBo();
/* 219 */         mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
/* 220 */         mqttBo.setQos(Integer.valueOf(1));
/* 221 */         mqttBo.setRetained(true);
/* 222 */         mqttBo.setData(terInfo.buildingPayload());
/* 223 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 224 */         log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */       } 
/* 226 */     } else if (StringUtils.isNotEmpty(terInfoDb.getGateway())) {
/*     */       
/* 228 */       delRetainedTopic(terInfoDb);
/*     */     } 
/* 230 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int updateTerStatus(TerInfo terInfo) {
/* 235 */     return this.terInfoMapper.updateTerInfo(terInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public int updateTerOtaByIds(Long[] ids) {
/* 240 */     if (ObjectUtils.isEmpty((Object[])ids)) {
/* 241 */       throw new ServiceException("设备id为空，升级失败");
/*     */     }
/* 243 */     ClassPathResource classPathResource = new ClassPathResource("AU.OPU");
/* 244 */     InputStream inputStream = null;
/*     */     try {
/* 246 */       inputStream = classPathResource.getInputStream();
/* 247 */       Map<Integer, String> fileCache = new LinkedHashMap<>();
/*     */       
/* 249 */       byte[] tempbytes = new byte[256];
/* 250 */       int byteread = 0;
/* 251 */       int index = 0;
/*     */       
/* 253 */       while ((byteread = inputStream.read(tempbytes)) != -1) {
/* 254 */         index++;
/*     */ 
/*     */         
/* 257 */         StringBuilder hexString = new StringBuilder();
/* 258 */         for (byte b : tempbytes) {
/* 259 */           hexString.append(String.format("%02X ", new Object[] { Byte.valueOf(b) }));
/*     */         } 
/* 261 */         fileCache.put(Integer.valueOf(index), hexString.toString());
/*     */       } 
/* 263 */       for (Long id : ids) {
/* 264 */         TerInfo terInfo = this.terInfoMapper.selectTerInfoById(id);
/* 265 */         SysMqttBo mqttBo = new SysMqttBo();
/* 266 */         mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
/* 267 */         mqttBo.setQos(Integer.valueOf(2));
/* 268 */         mqttBo.setRetained(false);
/* 269 */         mqttBo.setData("begin," + fileCache.size());
/* 270 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 271 */         for (Integer key : fileCache.keySet()) {
/* 272 */           mqttBo = new SysMqttBo();
/* 273 */           mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
/* 274 */           mqttBo.setQos(Integer.valueOf(2));
/* 275 */           mqttBo.setRetained(false);
/* 276 */           mqttBo.setData(key + "," + (String)fileCache.get(key));
/* 277 */           this.sysMqttService.sendMsgToTopic(mqttBo);
/* 278 */           Thread.sleep(100L);
/*     */         } 
/* 280 */         mqttBo = new SysMqttBo();
/* 281 */         mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/ota/" + terInfo.getSn());
/* 282 */         mqttBo.setQos(Integer.valueOf(2));
/* 283 */         mqttBo.setRetained(false);
/* 284 */         mqttBo.setData("end");
/* 285 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/*     */       } 
/* 287 */     } catch (Exception e1) {
/* 288 */       e1.printStackTrace();
/*     */     } finally {
/* 290 */       if (inputStream != null) {
/*     */         try {
/* 292 */           inputStream.close();
/* 293 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */     
/* 297 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int unbind(String terSn) {
/* 303 */     TerInfo terInfo = this.terInfoMapper.selectTerInfoBySn(terSn);
/* 304 */     if (ObjectUtils.isEmpty(terInfo)) {
/* 305 */       throw new ServiceException("设备不存在");
/*     */     }
/* 307 */     log.info("解绑设备信息：{}", JSONObject.toJSONString(terInfo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */     
/* 309 */     this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(terInfo.getSn());
/*     */     
/* 311 */     this.terBuildingPointInfoService.deleteTerBuildingPointInfoByTerSns(new String[] { terInfo.getSn() });
/*     */     
/* 313 */     if (StringUtils.isNotEmpty(terInfo.getGateway())) {
/* 314 */       delRetainedTopic(terInfo);
/*     */     }
/* 316 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional(rollbackFor = {Exception.class})
/*     */   public int deleteTerInfoByIds(Long[] ids) {
/* 328 */     for (Long id : ids) {
/* 329 */       TerInfo terInfo = this.terInfoMapper.selectTerInfoById(id);
/* 330 */       if (!ObjectUtils.isEmpty(terInfo)) {
/*     */ 
/*     */         
/* 333 */         if (ObjectUtil.isNotNull(terInfo.getPointId())) {
/* 334 */           throw new ServiceException("灯管已绑定点位，请解绑后再删除");
/*     */         }
/* 336 */         log.info("删除设备信息：{}", JSONObject.toJSONString(terInfo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */         
/* 338 */         this.buildingPatrolMapper.deleteBuildingPatrolByTerSn(terInfo.getSn());
/*     */         
/* 340 */         this.terSensorStatusService.deleteTerSensorStatusByTerSn(terInfo.getSn());
/*     */         
/* 342 */         this.terBuildingPointInfoService.deleteTerBuildingPointInfoByTerSns(new String[] { terInfo.getSn() });
/*     */         
/* 344 */         this.terInfoMapper.deleteTerInfoById(id);
/*     */         
/* 346 */         this.terSensorStatusService.deleteTerLightStatusByTerSn(terInfo.getSn());
/*     */         
/* 348 */         if (StringUtils.isNotEmpty(terInfo.getGateway()))
/* 349 */           delRetainedTopic(terInfo); 
/*     */       } 
/*     */     } 
/* 352 */     return 1;
/*     */   }
/*     */   
/*     */   private void delRetainedTopic(TerInfo terInfo) {
/* 356 */     SysMqttBo mqttBo = new SysMqttBo();
/* 357 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/delete/" + terInfo.getSn());
/* 358 */     mqttBo.setQos(Integer.valueOf(1));
/* 359 */     mqttBo.setRetained(false);
/* 360 */     mqttBo.setData("1");
/* 361 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 362 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */ 
/*     */     
/* 365 */     mqttBo = new SysMqttBo();
/* 366 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightinglist/" + terInfo.getSn());
/* 367 */     mqttBo.setQos(Integer.valueOf(1));
/* 368 */     mqttBo.setRetained(true);
/* 369 */     mqttBo.setData("");
/* 370 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 371 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */ 
/*     */     
/* 374 */     mqttBo = new SysMqttBo();
/* 375 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightingstatus/light/" + terInfo.getSn());
/* 376 */     mqttBo.setQos(Integer.valueOf(1));
/* 377 */     mqttBo.setRetained(true);
/* 378 */     mqttBo.setData("");
/* 379 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 380 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */ 
/*     */     
/* 383 */     mqttBo = new SysMqttBo();
/* 384 */     mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/lightingstatus/patrol/" + terInfo.getSn());
/* 385 */     mqttBo.setQos(Integer.valueOf(1));
/* 386 */     mqttBo.setRetained(true);
/* 387 */     mqttBo.setData("");
/* 388 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 389 */     log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteTerInfoById(Long id) {
/* 400 */     return this.terInfoMapper.deleteTerInfoById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public TerInfo selectTerInfoBySn(String terSn) {
/* 405 */     return this.terInfoMapper.selectTerInfoBySn(terSn);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<TerInfo> selectTerInfoBySnOrUpc(String key) {
/* 411 */     TerInfo terInfoParam = new TerInfo();
/* 412 */     terInfoParam.setSn(key);
/* 413 */     return this.terInfoMapper.selectTerInfoBySnOrUpc(terInfoParam);
/*     */   }
/*     */ 
/*     */   
/*     */   public BuildingTerStatusVo selectTerCount() {
/* 418 */     BuildingFloorInfo buildingFloorInfo = new BuildingFloorInfo();
/* 419 */     List<BuildingTerStatusVo> buildingTerStatusVoList = this.buildingFloorInfoService.selectBuildingFloorInfoListStatus(buildingFloorInfo);
/* 420 */     BuildingTerStatusVo buildingTerStatusVo = new BuildingTerStatusVo();
/* 421 */     for (BuildingTerStatusVo item : buildingTerStatusVoList) {
/* 422 */       buildingTerStatusVo.setTerCount(buildingTerStatusVo.getTerCount() + item.getTerCount());
/*     */ 
/*     */       
/* 425 */       buildingTerStatusVo.setSensorCountOnlineMovement(buildingTerStatusVo.getSensorCountOnlineMovement() + item.getSensorCountOnlineMovement());
/* 426 */       buildingTerStatusVo.setSensorCountAlarmMovement(buildingTerStatusVo.getSensorCountAlarmMovement() + item.getSensorCountAlarmMovement());
/*     */ 
/*     */       
/* 429 */       buildingTerStatusVo.setSensorCountOnlineLight(buildingTerStatusVo.getSensorCountOnlineLight() + item.getSensorCountOnlineLight());
/* 430 */       buildingTerStatusVo.setSensorCountAlarmLight(buildingTerStatusVo.getSensorCountAlarmLight() + item.getSensorCountAlarmLight());
/*     */ 
/*     */       
/* 433 */       buildingTerStatusVo.setSensorCountOnlinePost(buildingTerStatusVo.getSensorCountOnlinePost() + item.getSensorCountOnlinePost());
/* 434 */       buildingTerStatusVo.setSensorCountAlarmPost(buildingTerStatusVo.getSensorCountAlarmPost() + item.getSensorCountAlarmPost());
/*     */ 
/*     */       
/* 437 */       buildingTerStatusVo.setSensorCountOnlineSmoking(buildingTerStatusVo.getSensorCountOnlineSmoking() + item.getSensorCountOnlineSmoking());
/* 438 */       buildingTerStatusVo.setSensorCountAlarmSmoking(buildingTerStatusVo.getSensorCountAlarmSmoking() + item.getSensorCountAlarmSmoking());
/*     */ 
/*     */       
/* 441 */       buildingTerStatusVo.setSensorCountOnlineTemperature(buildingTerStatusVo.getSensorCountOnlineTemperature() + item.getSensorCountOnlineTemperature());
/* 442 */       buildingTerStatusVo.setSensorCountAlarmTemperature(buildingTerStatusVo.getSensorCountAlarmTemperature() + item.getSensorCountAlarmTemperature());
/*     */ 
/*     */       
/* 445 */       buildingTerStatusVo.setSensorCountOnlineWater(buildingTerStatusVo.getSensorCountOnlineWater() + item.getSensorCountOnlineWater());
/* 446 */       buildingTerStatusVo.setSensorCountAlarmWater(buildingTerStatusVo.getSensorCountAlarmWater() + item.getSensorCountAlarmWater());
/*     */     } 
/*     */     
/* 449 */     return buildingTerStatusVo;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */