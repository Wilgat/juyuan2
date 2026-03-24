/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.constant.LightingConstants;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
/*     */ import com.ruoyi.iotlighting.domain.BuildingInfo;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingInfoMapper;
/*     */ import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingInfoService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
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
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BuildingInfoServiceImpl
/*     */   implements IBuildingInfoService
/*     */ {
/*     */   @Autowired
/*     */   private BuildingInfoMapper buildingInfoMapper;
/*     */   @Autowired
/*     */   private IBuildingFloorInfoService buildingFloorInfoService;
/*     */   
/*     */   public BuildingInfo selectBuildingInfoById(Long id) {
/*  45 */     return this.buildingInfoMapper.selectBuildingInfoById(id);
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
/*     */   public List<BuildingInfo> selectBuildingInfoList(BuildingInfo buildingInfo) {
/*  57 */     return this.buildingInfoMapper.selectBuildingInfoList(buildingInfo);
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
/*     */   public int insertBuildingInfo(BuildingInfo buildingInfo) {
/*  69 */     buildingInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  70 */     buildingInfo.setBuildingWidth(LightingConstants.BUILDING_WIDTH);
/*  71 */     buildingInfo.setBuildingLength(LightingConstants.BUILDING_LENGTH);
/*  72 */     this.buildingInfoMapper.insertBuildingInfo(buildingInfo);
/*     */     
/*  74 */     String[] buildingFloors = buildingInfo.getFloors().split(",");
/*  75 */     List<BuildingFloorInfo> buildingFloorInfoList = new ArrayList<>();
/*  76 */     for (int i = 0; i < buildingFloors.length; i++) {
/*  77 */       BuildingFloorInfo buildingFloorInfo = createBuildingFloorInfo(Integer.valueOf(Integer.parseInt(buildingFloors[i])), LightingConstants.FLOOR_TYPE_1, buildingInfo);
/*  78 */       buildingFloorInfo.setCreatedBy(buildingInfo.getCreatedBy());
/*  79 */       buildingFloorInfo.setCreatedTime(buildingInfo.getCreatedTime());
/*  80 */       buildingFloorInfoList.add(buildingFloorInfo);
/*     */     } 
/*  82 */     return this.buildingFloorInfoService.insertBuildingFloorInfoBatch(buildingFloorInfoList);
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
/*     */   @Transactional
/*     */   public int updateBuildingInfo(BuildingInfo buildingInfo) {
/*  95 */     checkBuildingStatus(new Long[] { buildingInfo.getId() });
/*     */     
/*  97 */     BuildingFloorInfo buildingFloorInfoParam = new BuildingFloorInfo();
/*  98 */     buildingFloorInfoParam.setBuildingId(buildingInfo.getId());
/*  99 */     List<BuildingFloorInfo> buildingFloorInfos = this.buildingFloorInfoService.selectBuildingFloorInfoList(buildingFloorInfoParam);
/*     */ 
/*     */     
/* 102 */     String[] buildingFloors = buildingInfo.getFloors().split(",");
/* 103 */     List<String> newBuildingFloors = new ArrayList<>(Arrays.asList(buildingFloors));
/*     */ 
/*     */ 
/*     */     
/* 107 */     List<Integer> deleteBuildingFloorId = (List<Integer>)buildingFloorInfos.stream().filter(item -> !newBuildingFloors.contains(item.getFloorNumber().toString())).map(item -> Integer.valueOf(item.getId().intValue())).collect(Collectors.toList());
/*     */     
/* 109 */     List<Integer> oldBuildingFloors = (List<Integer>)buildingFloorInfos.stream().map(BuildingFloorInfo::getFloorNumber).collect(Collectors.toList());
/* 110 */     List<String> addBuildingFloorId = (List<String>)newBuildingFloors.stream().filter(item -> !oldBuildingFloors.contains(Integer.valueOf(Integer.parseInt(item)))).collect(Collectors.toList());
/*     */     
/* 112 */     List<BuildingFloorInfo> addBuildingFloorInfoList = new ArrayList<>();
/*     */     
/* 114 */     for (int i = 0; i < addBuildingFloorId.size(); i++) {
/* 115 */       BuildingFloorInfo buildingFloorInfo = createBuildingFloorInfo(Integer.valueOf(Integer.parseInt(addBuildingFloorId.get(i))), LightingConstants.FLOOR_TYPE_1, buildingInfo);
/* 116 */       buildingFloorInfo.setCreatedBy(buildingInfo.getCreatedBy());
/* 117 */       buildingFloorInfo.setCreatedTime(buildingInfo.getCreatedTime());
/* 118 */       addBuildingFloorInfoList.add(buildingFloorInfo);
/*     */     } 
/*     */ 
/*     */     
/* 122 */     if (!addBuildingFloorInfoList.isEmpty()) {
/* 123 */       this.buildingFloorInfoService.insertBuildingFloorInfoBatch(addBuildingFloorInfoList);
/*     */     }
/* 125 */     if (!deleteBuildingFloorId.isEmpty()) {
/* 126 */       Integer[] deleteArray = (Integer[])deleteBuildingFloorId.stream().toArray(x$0 -> new Integer[x$0]);
/* 127 */       this.buildingFloorInfoService.deleteBuildingFloorInfoByFloorId(deleteArray);
/*     */     } 
/* 129 */     return this.buildingInfoMapper.updateBuildingInfo(buildingInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BuildingFloorInfo createBuildingFloorInfo(Integer floorIndex, Integer type, BuildingInfo buildingInfo) {
/* 135 */     BuildingFloorInfo buildingFloorInfo = new BuildingFloorInfo();
/* 136 */     buildingFloorInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/* 137 */     buildingFloorInfo.setBuildingId(buildingInfo.getId());
/* 138 */     if (floorIndex.intValue() > 0) {
/* 139 */       buildingFloorInfo.setFloorName("F" + floorIndex);
/* 140 */       buildingFloorInfo.setFloorNumber(floorIndex);
/* 141 */       buildingFloorInfo.setType(LightingConstants.FLOOR_TYPE_1);
/*     */     } else {
/* 143 */       buildingFloorInfo.setFloorName("B" + -floorIndex.intValue());
/* 144 */       buildingFloorInfo.setFloorNumber(floorIndex);
/* 145 */       buildingFloorInfo.setType(LightingConstants.FLOOR_TYPE_2);
/*     */     } 
/* 147 */     buildingFloorInfo.setDeptId(buildingInfo.getDeptId());
/* 148 */     buildingFloorInfo.setDelFlag("0");
/* 149 */     buildingFloorInfo.setUpdatedBy(buildingInfo.getUpdatedBy());
/* 150 */     buildingFloorInfo.setUpdatedTime(buildingInfo.getUpdatedTime());
/* 151 */     return buildingFloorInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingInfoByIds(Long[] ids) {
/* 162 */     checkBuildingStatus(ids);
/* 163 */     return this.buildingInfoMapper.deleteBuildingInfoByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingInfoById(Long id) {
/* 174 */     checkBuildingStatus(new Long[] { id });
/* 175 */     return this.buildingInfoMapper.deleteBuildingInfoById(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkBuildingStatus(Long[] ids) {
/* 184 */     int terCount = this.buildingInfoMapper.selectBuildingTerCountByBuildingIds(ids);
/* 185 */     if (terCount > 0) {
/* 186 */       throw new ServiceException("操作失败，建筑下存在绑定的灯管，请解绑后操作");
/*     */     }
/* 188 */     int gatewayCount = this.buildingInfoMapper.selectBuildingGatewayCountByBuildingIds(ids);
/* 189 */     if (gatewayCount > 0)
/* 190 */       throw new ServiceException("操作失败，建筑下存在绑定的网关，请解绑后操作"); 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */