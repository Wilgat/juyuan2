/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
/*     */ import com.ruoyi.iotlighting.domain.BuildingZoneInfo;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingZoneInfoMapper;
/*     */ import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
/*     */ import com.ruoyi.iotlighting.service.IBuildingZoneInfoService;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class BuildingZoneInfoServiceImpl
/*     */   implements IBuildingZoneInfoService
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(BuildingZoneInfoServiceImpl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private BuildingZoneInfoMapper buildingZoneInfoMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private IBuildingFloorInfoService buildingFloorInfoService;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BuildingZoneInfo selectBuildingZoneInfoById(Long id) {
/*  42 */     return this.buildingZoneInfoMapper.selectBuildingZoneInfoById(id);
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
/*     */   public List<BuildingZoneInfo> selectBuildingZoneInfoList(BuildingZoneInfo buildingZoneInfo) {
/*  54 */     return this.buildingZoneInfoMapper.selectBuildingZoneInfoList(buildingZoneInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertBuildingZoneInfo(BuildingZoneInfo buildingZoneInfo) {
/*  65 */     return this.buildingZoneInfoMapper.insertBuildingZoneInfo(buildingZoneInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateBuildingZoneInfo(BuildingZoneInfo buildingZoneInfo) {
/*  76 */     return this.buildingZoneInfoMapper.updateBuildingZoneInfo(buildingZoneInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingZoneInfoByIds(Long[] ids) {
/*  87 */     return this.buildingZoneInfoMapper.deleteBuildingZoneInfoByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingZoneInfoById(Long id) {
/*  98 */     return this.buildingZoneInfoMapper.deleteBuildingZoneInfoById(id);
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
/*     */   public String importZoneInfo(List<BuildingZoneInfo> buildingZoneInfoList) {
/* 110 */     if (CollectionUtil.isEmpty(buildingZoneInfoList)) {
/* 111 */       throw new ServiceException("导入区域数据不能为空！");
/*     */     }
/* 113 */     int successNum = 0;
/* 114 */     int failureNum = 0;
/* 115 */     StringBuilder successMsg = new StringBuilder();
/* 116 */     StringBuilder failureMsg = new StringBuilder();
/*     */     
/* 118 */     for (BuildingZoneInfo buildingZoneInfo : buildingZoneInfoList) {
/*     */       try {
/* 120 */         BuildingFloorInfo queryParam = new BuildingFloorInfo();
/* 121 */         queryParam.setBuildingName(buildingZoneInfo.getBuildingName());
/* 122 */         queryParam.setFloorName(buildingZoneInfo.getFloorName());
/* 123 */         BuildingFloorInfo buildingFloorInfo = this.buildingFloorInfoService.selectBuildingFloorByName(queryParam);
/* 124 */         if (ObjectUtil.isEmpty(buildingFloorInfo)) {
/* 125 */           failureNum++;
/* 126 */           failureMsg.append("<br/>" + failureNum + "、楼层名称：" + buildingZoneInfo.getFloorName() + "，建筑名称：" + buildingZoneInfo.getBuildingName() + "，未找到对应数据！");
/*     */           
/*     */           continue;
/*     */         } 
/* 130 */         buildingZoneInfo.setBuildingId(buildingFloorInfo.getBuildingId());
/*     */         
/* 132 */         buildingZoneInfo.setFloorId(buildingFloorInfo.getId());
/* 133 */         buildingZoneInfo.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*     */         
/* 135 */         if (CollectionUtil.isNotEmpty(this.buildingZoneInfoMapper.selectBuildingZoneInfoList(buildingZoneInfo))) {
/* 136 */           log.info("插入数据重复，区域名称：{}", buildingZoneInfo.getZone());
/* 137 */           failureNum++;
/* 138 */           failureMsg.append("<br/>" + failureNum + "、区域名称：" + buildingZoneInfo.getZone() + "，已存在！");
/*     */           
/*     */           continue;
/*     */         } 
/* 142 */         this.buildingZoneInfoMapper.insertBuildingZoneInfo(buildingZoneInfo);
/* 143 */         successNum++;
/* 144 */         successMsg.append("<br/>" + successNum + "、区域名称 " + buildingZoneInfo.getZone() + " 导入成功");
/* 145 */       } catch (Exception e) {
/* 146 */         failureNum++;
/* 147 */         String msg = "<br/>" + failureNum + "、区域名称 " + buildingZoneInfo.getZone() + " 导入失败：";
/* 148 */         failureMsg.append(msg + e.getMessage());
/*     */       } 
/*     */     } 
/* 151 */     if (failureNum > 0) {
/* 152 */       failureMsg.insert(0, "导入完成，成功 " + successNum + "条，失败 " + failureNum + " 条，错误如下：");
/* 153 */       throw new ServiceException(failureMsg.toString());
/*     */     } 
/* 155 */     successMsg.insert(0, "导入完成，共 " + successNum + " 条，数据如下：");
/*     */     
/* 157 */     return successMsg.toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingZoneInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */