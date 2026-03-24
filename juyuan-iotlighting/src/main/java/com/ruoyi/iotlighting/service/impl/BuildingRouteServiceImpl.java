/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrol;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
/*     */ import com.ruoyi.iotlighting.domain.BuildingRoute;
/*     */ import com.ruoyi.iotlighting.domain.SysStaff;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolLogMapper;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolMapper;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingRouteMapper;
/*     */ import com.ruoyi.iotlighting.mapper.SysStaffMapper;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingRouteService;
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
/*     */ 
/*     */ @Service
/*     */ public class BuildingRouteServiceImpl
/*     */   implements IBuildingRouteService
/*     */ {
/*  37 */   private static final Logger log = LoggerFactory.getLogger(BuildingRouteServiceImpl.class);
/*     */   
/*     */   @Autowired
/*     */   private BuildingRouteMapper buildingRouteMapper;
/*     */   
/*     */   @Autowired
/*     */   private SysStaffMapper sysStaffMapper;
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolMapper buildingPatrolMapper;
/*     */   
/*     */   @Autowired
/*     */   private BuildingPatrolLogMapper buildingPatrolLogMapper;
/*     */   
/*     */   @Autowired
/*     */   private ISysMqttService sysMqttService;
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<BuildingRoute> selectBuildingRouteList(BuildingRoute buildingRoute) {
/*  57 */     return this.buildingRouteMapper.selectBuildingRouteList(buildingRoute);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Long startRoutePatrol(Long routeId, Long userId) {
/*  63 */     BuildingPatrolLog startBuildingPatrolLog = this.buildingPatrolLogMapper.selectBuildingPatrolLogByRouteId(routeId);
/*     */     
/*  65 */     if (ObjectUtil.isNotNull(startBuildingPatrolLog)) {
/*  66 */       throw new ServiceException(MessageUtils.message("buildingRoute.status.error", new Object[0]));
/*     */     }
/*     */     
/*  69 */     SysStaff sysStaff = this.sysStaffMapper.selectSysStaffByUserId(userId);
/*  70 */     if (ObjectUtil.isEmpty(sysStaff) || StringUtils.isEmpty(sysStaff.getLicenseNumber())) {
/*  71 */       throw new ServiceException(MessageUtils.message("buildingRoute.start.error", new Object[0]));
/*     */     }
/*     */     
/*  74 */     BuildingPatrol queryParam = new BuildingPatrol();
/*  75 */     queryParam.setRouteId(routeId);
/*  76 */     List<BuildingPatrol> buildingPatrolList = this.buildingPatrolMapper.selectActiveBuildingPatrolList(queryParam);
/*  77 */     String routeName = "";
/*  78 */     Long patrolCount = Long.valueOf(0L);
/*  79 */     Long timeCount = Long.valueOf(0L);
/*     */     
/*  81 */     for (BuildingPatrol buildingPatrol : buildingPatrolList) {
/*  82 */       if (ObjectUtil.isEmpty(buildingPatrol.getPatrolNumber()) || 
/*  83 */         ObjectUtil.isEmpty(buildingPatrol.getWaitTime()) || 
/*  84 */         ObjectUtil.isEmpty(buildingPatrol.getTolerantTime())) {
/*     */         continue;
/*     */       }
/*  87 */       if (StringUtils.isEmpty(routeName)) {
/*  88 */         routeName = buildingPatrol.getRouteName();
/*     */       }
/*  90 */       timeCount = Long.valueOf(timeCount.longValue() + buildingPatrol.getWaitTime().longValue() + buildingPatrol.getTolerantTime().longValue());
/*  91 */       patrolCount = Long.valueOf(patrolCount.longValue() + 1L);
/*  92 */       SysMqttBo mqttBo = new SysMqttBo();
/*  93 */       mqttBo.setTopic("gateway/" + buildingPatrol.getGateway() + "/lightingstatus/patrol/" + buildingPatrol.getTerSn());
/*  94 */       mqttBo.setQos(Integer.valueOf(1));
/*  95 */       mqttBo.setRetained(true);
/*  96 */       mqttBo.setData(sysStaff.getLicenseNumber());
/*  97 */       this.sysMqttService.sendMsgToTopic(mqttBo);
/*  98 */       log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*     */     } 
/* 100 */     if (patrolCount.longValue() == 0L) {
/* 101 */       throw new ServiceException(MessageUtils.message("buildingRoute.point.error", new Object[0]));
/*     */     }
/* 103 */     BuildingPatrolLog buildingPatrolLog = buildBuildingPatrolLog(sysStaff, routeId, routeName, patrolCount);
/* 104 */     buildingPatrolLog.setTimeCount(timeCount);
/* 105 */     this.buildingPatrolLogMapper.insertBuildingPatrolLog(buildingPatrolLog);
/* 106 */     return buildingPatrolLog.getId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BuildingPatrolLog buildBuildingPatrolLog(SysStaff sysStaff, Long routeId, String routeName, Long patrolCount) {
/* 112 */     BuildingPatrolLog buildingPatrolLog = new BuildingPatrolLog();
/* 113 */     buildingPatrolLog.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 114 */     buildingPatrolLog.setRouteId(routeId);
/* 115 */     buildingPatrolLog.setRouteName(routeName);
/* 116 */     buildingPatrolLog.setUserId(sysStaff.getUserId());
/* 117 */     buildingPatrolLog.setStartTime(new Date());
/* 118 */     buildingPatrolLog.setPatrolPointTotal(patrolCount);
/* 119 */     buildingPatrolLog.setPatrolPointComplete(Long.valueOf(0L));
/* 120 */     buildingPatrolLog.setCompleteFlag("0");
/* 121 */     buildingPatrolLog.setNickName(sysStaff.getNickName());
/* 122 */     buildingPatrolLog.setCreateTime(new Date());
/* 123 */     return buildingPatrolLog;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingRouteServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */