/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPatrol;
/*     */ import com.ruoyi.iotlighting.domain.BuildingRoute;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPatrolMapper;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingRouteMapper;
/*     */ import com.ruoyi.iotlighting.service.IBuildingPatrolService;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BuildingPatrolServiceImpl
/*     */   implements IBuildingPatrolService
/*     */ {
/*     */   @Autowired
/*     */   private BuildingPatrolMapper buildingPatrolMapper;
/*     */   @Autowired
/*     */   private BuildingRouteMapper buildingRouteMapper;
/*     */   
/*     */   public BuildingPatrol selectBuildingPatrolById(Long id) {
/*  44 */     return this.buildingPatrolMapper.selectBuildingPatrolById(id);
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
/*     */   public List<BuildingPatrol> selectBuildingPatrolList(BuildingPatrol buildingPatrol) {
/*  56 */     return this.buildingPatrolMapper.selectBuildingPatrolList(buildingPatrol);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertBuildingPatrol(BuildingPatrol buildingPatrol) {
/*  67 */     buildingPatrol.setCreateTime(DateUtils.getNowDate());
/*  68 */     return this.buildingPatrolMapper.insertBuildingPatrol(buildingPatrol);
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
/*     */   public int updateBuildingPatrol(List<BuildingPatrol> buildingPatrolList) {
/*  80 */     if (CollectionUtil.isEmpty(buildingPatrolList)) {
/*  81 */       throw new ServiceException(MessageUtils.message("buildingPatrol.param.error", new Object[0]));
/*     */     }
/*  83 */     Long routeId = ((BuildingPatrol)buildingPatrolList.get(0)).getRouteId();
/*  84 */     String routeName = ((BuildingPatrol)buildingPatrolList.get(0)).getRouteName();
/*  85 */     Long buildingId = ((BuildingPatrol)buildingPatrolList.get(0)).getBuildingId();
/*     */     
/*  87 */     if (ObjectUtil.isNull(routeId)) {
/*  88 */       BuildingRoute buildingRoute = new BuildingRoute();
/*  89 */       buildingRoute.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  90 */       buildingRoute.setRouteName(routeName);
/*  91 */       buildingRoute.setBuildingId(buildingId);
/*  92 */       this.buildingRouteMapper.insertBuildingRoute(buildingRoute);
/*  93 */       for (BuildingPatrol buildingPatrol : buildingPatrolList) {
/*  94 */         buildingPatrol.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  95 */         buildingPatrol.setRouteId(buildingRoute.getId());
/*  96 */         this.buildingPatrolMapper.insertBuildingPatrol(buildingPatrol);
/*     */       } 
/*     */     } else {
/*     */       
/* 100 */       if (StringUtils.isNotEmpty(routeName)) {
/* 101 */         BuildingRoute buildingRoute = new BuildingRoute();
/* 102 */         buildingRoute.setId(routeId);
/* 103 */         buildingRoute.setRouteName(routeName);
/* 104 */         this.buildingRouteMapper.updateBuildingRoute(buildingRoute);
/*     */       } 
/* 106 */       for (BuildingPatrol buildingPatrol : buildingPatrolList) {
/* 107 */         if (ObjectUtil.isEmpty(buildingPatrol.getId())) {
/* 108 */           buildingPatrol.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 109 */           this.buildingPatrolMapper.insertBuildingPatrol(buildingPatrol); continue;
/*     */         } 
/* 111 */         if (ObjectUtil.isEmpty(buildingPatrol.getPatrolNumber()) || 
/* 112 */           ObjectUtil.isEmpty(buildingPatrol.getWaitTime()) || 
/* 113 */           ObjectUtil.isEmpty(buildingPatrol.getTolerantTime())) {
/*     */           
/* 115 */           this.buildingPatrolMapper.deleteBuildingPatrolByIds(new Long[] { buildingPatrol.getId() }); continue;
/*     */         } 
/* 117 */         this.buildingPatrolMapper.updateBuildingPatrol(buildingPatrol);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPatrolByIds(Long[] ids) {
/* 134 */     return this.buildingPatrolMapper.deleteBuildingPatrolByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPatrolById(Long id) {
/* 145 */     this.buildingRouteMapper.deleteBuildingRouteById(id);
/* 146 */     return this.buildingPatrolMapper.deleteBuildingPatrolById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<BuildingPatrol> selectBuildingPatrolSelect(BuildingPatrol buildingPatrol) {
/* 152 */     return this.buildingPatrolMapper.selectBuildingPatrolSelect(buildingPatrol);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingPatrolServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */