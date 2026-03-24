/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import cn.hutool.core.util.IdUtil;
/*    */ import com.ruoyi.common.annotation.DataScope;
/*    */ import com.ruoyi.iotlighting.domain.BuildingArea;
/*    */ import com.ruoyi.iotlighting.domain.BuildingAreaStaff;
/*    */ import com.ruoyi.iotlighting.mapper.BuildingAreaMapper;
/*    */ import com.ruoyi.iotlighting.mapper.SysStaffMapper;
/*    */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*    */ import com.ruoyi.iotlighting.service.IBuildingAreaService;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class BuildingAreaServiceImpl
/*    */   implements IBuildingAreaService
/*    */ {
/* 25 */   private static final Logger log = LoggerFactory.getLogger(BuildingAreaServiceImpl.class);
/*    */   
/*    */   @Autowired
/*    */   private BuildingAreaMapper buildingAreaMapper;
/*    */   
/*    */   @Autowired
/*    */   private SysStaffMapper sysStaffMapper;
/*    */   
/*    */   @Autowired
/*    */   private ISysMqttService sysMqttService;
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<BuildingArea> selectBuildingAreaList(BuildingArea buildingArea) {
/* 39 */     return this.buildingAreaMapper.selectBuildingAreaList(buildingArea);
/*    */   }
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<BuildingAreaStaff> selectBuildingAreaStaffList(BuildingAreaStaff buildingAreaStaff) {
/* 45 */     return this.buildingAreaMapper.selectBuildingAreaStaffList(buildingAreaStaff);
/*    */   }
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<BuildingAreaStaff> selectBuildingAreaStaffConfigList(BuildingAreaStaff buildingAreaStaff) {
/* 51 */     return this.buildingAreaMapper.selectBuildingAreaStaffConfigList(buildingAreaStaff);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Transactional(rollbackFor = {Exception.class})
/*    */   public int updateAreaStaffConfig(Long buildingAreaId, List<BuildingAreaStaff> buildingAreaStaffList) {
/* 58 */     this.buildingAreaMapper.deletBuildingAreaStaffConfigByAreaId(buildingAreaId);
/*    */     
/* 60 */     for (BuildingAreaStaff item : buildingAreaStaffList) {
/* 61 */       item.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*    */     }
/* 63 */     return this.buildingAreaMapper.insertBuildingAreaStaffConfigBatch(buildingAreaStaffList);
/*    */   }
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<BuildingArea> selectBuildingAreaListAll(BuildingArea buildingArea) {
/* 69 */     return this.buildingAreaMapper.selectBuildingAreaListAll(buildingArea);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingAreaServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */