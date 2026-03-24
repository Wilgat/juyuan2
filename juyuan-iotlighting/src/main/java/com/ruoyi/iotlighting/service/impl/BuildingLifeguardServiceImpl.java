/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.iotlighting.domain.BuildingLifeguard;
/*     */ import com.ruoyi.iotlighting.domain.TerInfo;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingLifeguardMapper;
/*     */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingLifeguardService;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BuildingLifeguardServiceImpl
/*     */   implements IBuildingLifeguardService
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(BuildingLifeguardServiceImpl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingLifeguardMapper buildingLifeguardMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private TerInfoMapper terInfoMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */ 
/*     */ 
/*     */   
/*     */   public BuildingLifeguard selectBuildingLifeguardById(Long id) {
/*  48 */     return this.buildingLifeguardMapper.selectBuildingLifeguardById(id);
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
/*     */   public List<BuildingLifeguard> selectBuildingLifeguardList(BuildingLifeguard buildingLifeguard) {
/*  60 */     return this.buildingLifeguardMapper.selectBuildingLifeguardList(buildingLifeguard);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertBuildingLifeguard(BuildingLifeguard buildingLifeguard) {
/*  71 */     return this.buildingLifeguardMapper.insertBuildingLifeguard(buildingLifeguard);
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
/*     */   public int updateBuildingLifeguard(List<BuildingLifeguard> buildingLifeguardList) {
/*  83 */     for (BuildingLifeguard buildingLifeguard : buildingLifeguardList) {
/*     */       
/*  85 */       if (ObjectUtil.isEmpty(buildingLifeguard.getId())) {
/*  86 */         this.buildingLifeguardMapper.insertBuildingLifeguard(buildingLifeguard);
/*     */         continue;
/*     */       } 
/*  89 */       BuildingLifeguard buildingLifeguardInDb = this.buildingLifeguardMapper.selectBuildingLifeguardById(buildingLifeguard.getId());
/*  90 */       boolean userIdOneChange = ObjectUtil.equal(buildingLifeguardInDb.getUserIdOne(), buildingLifeguard.getUserIdOne());
/*  91 */       if (ObjectUtil.isNull(buildingLifeguardInDb.getUserIdOne()) && ObjectUtil.isNull(buildingLifeguard.getUserIdOne())) {
/*  92 */         userIdOneChange = true;
/*     */       }
/*  94 */       boolean userIdTwoChange = ObjectUtil.equal(buildingLifeguardInDb.getUserIdTwo(), buildingLifeguard.getUserIdTwo());
/*  95 */       if (ObjectUtil.isNull(buildingLifeguardInDb.getUserIdTwo()) && ObjectUtil.isNull(buildingLifeguard.getUserIdTwo())) {
/*  96 */         userIdTwoChange = true;
/*     */       }
/*  98 */       boolean userIdThreeChange = ObjectUtil.equal(buildingLifeguardInDb.getUserIdThree(), buildingLifeguard.getUserIdThree());
/*  99 */       if (ObjectUtil.isNull(buildingLifeguardInDb.getUserIdThree()) && ObjectUtil.isNull(buildingLifeguard.getUserIdThree())) {
/* 100 */         userIdThreeChange = true;
/*     */       }
/*     */       
/* 103 */       if (!userIdOneChange || !userIdTwoChange || !userIdThreeChange) {
/* 104 */         this.buildingLifeguardMapper.updateBuildingLifeguard(buildingLifeguard);
/* 105 */         TerInfo terInfoDb = this.terInfoMapper.selectTerInfoBySn(buildingLifeguard.getTerSn());
/* 106 */         SysMqttBo mqttBo = new SysMqttBo();
/* 107 */         mqttBo.setTopic("gateway/" + terInfoDb.getGateway() + "/lightinglist/" + terInfoDb.getSn());
/* 108 */         mqttBo.setQos(Integer.valueOf(1));
/* 109 */         mqttBo.setRetained(true);
/* 110 */         mqttBo.setData(terInfoDb.buildingPayload());
/* 111 */         this.sysMqttService.sendMsgToTopic(mqttBo);
/* 112 */         log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return 1;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingLifeguardServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */