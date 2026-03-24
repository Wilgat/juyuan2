/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPointInfo;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingPointInfoMapper;
/*     */ import com.ruoyi.iotlighting.service.IBuildingPointInfoService;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
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
/*     */ public class BuildingPointInfoServiceImpl
/*     */   implements IBuildingPointInfoService
/*     */ {
/*     */   @Autowired
/*     */   private BuildingPointInfoMapper buildingPointInfoMapper;
/*     */   
/*     */   public BuildingPointInfo selectBuildingPointInfoById(Long id) {
/*  34 */     return this.buildingPointInfoMapper.selectBuildingPointInfoById(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BuildingPointInfo> selectBuildingPointInfoList(BuildingPointInfo buildingPointInfo) {
/*  45 */     return this.buildingPointInfoMapper.selectBuildingPointInfoList(buildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BuildingPointInfo> selectBuildingPointTerList(BuildingPointInfo buildingPointInfo) {
/*  56 */     return this.buildingPointInfoMapper.selectBuildingPointTerList(buildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertBuildingPointInfo(BuildingPointInfo buildingPointInfo) {
/*  67 */     buildingPointInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  68 */     return this.buildingPointInfoMapper.insertBuildingPointInfo(buildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateBuildingPointInfo(BuildingPointInfo buildingPointInfo) {
/*  79 */     return this.buildingPointInfoMapper.updateBuildingPointInfo(buildingPointInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPointInfoByIds(Long[] ids) {
/*  90 */     return this.buildingPointInfoMapper.deleteBuildingPointInfoByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingPointInfoById(Long id) {
/* 101 */     return this.buildingPointInfoMapper.deleteBuildingPointInfoById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public BuildingPointInfo selectBuildingPointInfo(BuildingPointInfo buildingPointInfo) {
/* 106 */     return this.buildingPointInfoMapper.selectBuildingPointInfo(buildingPointInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public Map<String, List<String>> selectBuildingAreaInfo(BuildingPointInfo buildingPointInfo) {
/* 112 */     Map<String, List<String>> result = new HashMap<>();
/* 113 */     List<String> blockList = this.buildingPointInfoMapper.selectBuildingBlockInfo(buildingPointInfo);
/* 114 */     List<String> zoneList = this.buildingPointInfoMapper.selectBuildingZoneInfo(buildingPointInfo);
/* 115 */     List<String> zoneSubList = this.buildingPointInfoMapper.selectBuildingZoneSubInfo(buildingPointInfo);
/* 116 */     result.put("block", blockList);
/* 117 */     result.put("zone", zoneList);
/* 118 */     result.put("zoneSub", zoneSubList);
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<String> selectBuildingBlockInfo(BuildingPointInfo buildingPointInfo) {
/* 125 */     return this.buildingPointInfoMapper.selectBuildingBlockInfo(buildingPointInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<String> selectBuildingZoneInfo(BuildingPointInfo buildingPointInfo) {
/* 131 */     return this.buildingPointInfoMapper.selectBuildingZoneInfo(buildingPointInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<String> selectBuildingZoneSubInfo(BuildingPointInfo buildingPointInfo) {
/* 137 */     return this.buildingPointInfoMapper.selectBuildingZoneSubInfo(buildingPointInfo);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingPointInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */