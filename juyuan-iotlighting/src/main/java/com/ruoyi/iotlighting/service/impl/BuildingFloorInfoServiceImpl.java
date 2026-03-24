/*     */ package com.ruoyi.iotlighting.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
/*     */ import com.ruoyi.iotlighting.domain.BuildingTerStatus;
/*     */ import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
/*     */ import com.ruoyi.iotlighting.mapper.BuildingFloorInfoMapper;
/*     */ import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.CollectionUtils;
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
/*     */ public class BuildingFloorInfoServiceImpl
/*     */   implements IBuildingFloorInfoService
/*     */ {
/*     */   @Autowired
/*     */   private BuildingFloorInfoMapper buildingFloorInfoMapper;
/*     */   
/*     */   public BuildingFloorInfo selectBuildingFloorInfoById(Long id) {
/*  41 */     return this.buildingFloorInfoMapper.selectBuildingFloorInfoById(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BuildingFloorInfo> selectBuildingFloorInfoList(BuildingFloorInfo buildingFloorInfo) {
/*  52 */     return this.buildingFloorInfoMapper.selectBuildingFloorInfoList(buildingFloorInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public BuildingFloorInfo selectBuildingFloorByName(BuildingFloorInfo buildingFloorInfo) {
/*  58 */     return this.buildingFloorInfoMapper.selectBuildingFloorByName(buildingFloorInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BuildingTerStatusVo> selectBuildingFloorInfoListStatus(BuildingFloorInfo buildingFloorInfo) {
/*  69 */     List<BuildingTerStatus> buildingTerStatusList = this.buildingFloorInfoMapper.selectBuildingFloorInfoListStatus(buildingFloorInfo);
/*  70 */     List<BuildingTerStatusVo> buildingTerStatusVoList = new ArrayList<>();
/*  71 */     if (CollectionUtils.isEmpty(buildingTerStatusList)) {
/*  72 */       return buildingTerStatusVoList;
/*     */     }
/*     */ 
/*     */     
            Map<Long, List<BuildingTerStatus>> map = buildingTerStatusList.stream().collect(
                Collectors.toMap(
                    BuildingTerStatus::getId,                           // keyMapper
                    p -> {                                              // valueMapper
                        List<BuildingTerStatus> list = new ArrayList<>();
                        list.add(p);
                        return list;
                    },                                                  // ← ADD THIS COMMA HERE !!!
                    (value1, value2) -> {                               // mergeFunction (for duplicate keys)
                        value1.addAll(value2);
                        return value1;
                    }
                )
            );
/*  86 */     for (List<BuildingTerStatus> buildingTerStatuses : map.values()) {
/*  87 */       buildingTerStatusVoList.add(caculteBuildingTerStatusVo(buildingTerStatuses));
/*     */     }
/*  89 */     buildingTerStatusVoList.sort(Comparator.comparing(BuildingTerStatusVo::getId));
/*  90 */     return buildingTerStatusVoList;
/*     */   }
/*     */   
/*     */   private BuildingTerStatusVo caculteBuildingTerStatusVo(List<BuildingTerStatus> buildingTerStatuses) {
/*  94 */     BuildingTerStatusVo buildingTerStatusVo = new BuildingTerStatusVo();
/*  95 */     String terSn = null;
/*  96 */     for (BuildingTerStatus buildingTerStatus : buildingTerStatuses) {
/*  97 */       buildingTerStatusVo.setId(buildingTerStatus.getId());
/*  98 */       buildingTerStatusVo.setBuildingId(buildingTerStatus.getBuildingId());
/*  99 */       buildingTerStatusVo.setFloorName(buildingTerStatus.getFloorName());
/* 100 */       if (StringUtils.isEmpty(buildingTerStatus.getTerSn())) {
/*     */         continue;
/*     */       }
/* 103 */       if (!StringUtils.equals(terSn, buildingTerStatus.getTerSn())) {
/*     */         
/* 105 */         buildingTerStatusVo.setTerCount(buildingTerStatusVo.getTerCount() + 1);
/* 106 */         terSn = buildingTerStatus.getTerSn();
/*     */ 
/*     */         
/* 109 */         if (StringUtils.equalsIgnoreCase("online", buildingTerStatus.getTerStatus())) {
/* 110 */           buildingTerStatusVo.setTerCountOnline(buildingTerStatusVo.getTerCountOnline() + 1);
/*     */         }
/*     */         
/* 113 */         if (StringUtils.equalsIgnoreCase("offline", buildingTerStatus.getTerStatus())) {
/* 114 */           buildingTerStatusVo.setTerCountOffline(buildingTerStatusVo.getTerCountOffline() + 1);
/*     */         }
/*     */         
/* 117 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getTerStatus())) {
/* 118 */           buildingTerStatusVo.setTerCountAlarm(buildingTerStatusVo.getTerCountAlarm() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 122 */       if (StringUtils.equalsIgnoreCase("motion", buildingTerStatus.getSensorType())) {
/* 123 */         buildingTerStatusVo.setSensorCountOnlineMovement(buildingTerStatusVo.getSensorCountOnlineMovement() + 1);
/*     */         
/* 125 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 126 */           buildingTerStatusVo.setSensorCountAlarmMovement(buildingTerStatusVo.getSensorCountAlarmMovement() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 130 */       if (StringUtils.equalsIgnoreCase("light", buildingTerStatus.getSensorType())) {
/* 131 */         buildingTerStatusVo.setSensorCountOnlineLight(buildingTerStatusVo.getSensorCountOnlineLight() + 1);
/*     */         
/* 133 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 134 */           buildingTerStatusVo.setSensorCountAlarmLight(buildingTerStatusVo.getSensorCountAlarmLight() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 138 */       if (StringUtils.equalsIgnoreCase("post", buildingTerStatus.getSensorType())) {
/* 139 */         buildingTerStatusVo.setSensorCountOnlinePost(buildingTerStatusVo.getSensorCountOnlinePost() + 1);
/*     */         
/* 141 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 142 */           buildingTerStatusVo.setSensorCountAlarmPost(buildingTerStatusVo.getSensorCountAlarmPost() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 146 */       if (StringUtils.equalsIgnoreCase("smoke", buildingTerStatus.getSensorType())) {
/* 147 */         buildingTerStatusVo.setSensorCountOnlineSmoking(buildingTerStatusVo.getSensorCountOnlineSmoking() + 1);
/*     */         
/* 149 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 150 */           buildingTerStatusVo.setSensorCountAlarmSmoking(buildingTerStatusVo.getSensorCountAlarmSmoking() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 154 */       if (StringUtils.equalsIgnoreCase("h2s", buildingTerStatus.getSensorType())) {
/* 155 */         buildingTerStatusVo.setSensorCountOnlineTemperature(buildingTerStatusVo.getSensorCountOnlineTemperature() + 1);
/*     */         
/* 157 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 158 */           buildingTerStatusVo.setSensorCountAlarmTemperature(buildingTerStatusVo.getSensorCountAlarmTemperature() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 162 */       if (StringUtils.equalsIgnoreCase("flooding", buildingTerStatus.getSensorType())) {
/* 163 */         buildingTerStatusVo.setSensorCountOnlineWater(buildingTerStatusVo.getSensorCountOnlineWater() + 1);
/*     */         
/* 165 */         if (StringUtils.equalsIgnoreCase("alarm", buildingTerStatus.getSensorStatus())) {
/* 166 */           buildingTerStatusVo.setSensorCountAlarmWater(buildingTerStatusVo.getSensorCountAlarmWater() + 1);
/*     */         }
/*     */       } 
/*     */     } 
/* 170 */     return buildingTerStatusVo;
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
/*     */   public int insertBuildingFloorInfo(BuildingFloorInfo buildingFloorInfo) {
/* 182 */     return this.buildingFloorInfoMapper.insertBuildingFloorInfo(buildingFloorInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int updateBuildingFloorInfo(BuildingFloorInfo buildingFloorInfo) {
/* 193 */     return this.buildingFloorInfoMapper.updateBuildingFloorInfo(buildingFloorInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingFloorInfoByIds(Long[] ids) {
/* 204 */     checkBuildingFloorStatus(ids);
/* 205 */     return this.buildingFloorInfoMapper.deleteBuildingFloorInfoByIds(ids);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkBuildingFloorStatus(Long[] ids) {
/* 215 */     int terCount = this.buildingFloorInfoMapper.selectTerCountByBuildingFloorIds(ids);
/* 216 */     if (terCount > 0) {
/* 217 */       throw new ServiceException("删除失败，楼层下有点位绑定灯管，请解除绑定后再操作");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deleteBuildingFloorInfoById(Long id) {
/* 229 */     return this.buildingFloorInfoMapper.deleteBuildingFloorInfoById(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int insertBuildingFloorInfoBatch(List<BuildingFloorInfo> buildingFloorInfoList) {
/* 234 */     return this.buildingFloorInfoMapper.insertBuildingFloorInfoBatch(buildingFloorInfoList);
/*     */   }
/*     */ 
/*     */   
/*     */   public int deleteBuildingFloorInfoByFloorId(Integer[] floorIds) {
/* 239 */     return this.buildingFloorInfoMapper.deleteBuildingFloorInfoByFloorId(floorIds);
/*     */   }
/*     */ 
/*     */   
/*     */   public int deleteBuildingFloorInfoByBuildingId(Long buildingId) {
/* 244 */     return this.buildingFloorInfoMapper.deleteBuildingFloorInfoByBuildingId(buildingId);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/BuildingFloorInfoServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */