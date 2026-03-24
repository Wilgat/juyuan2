package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
import java.util.List;

public interface IBuildingFloorInfoService {
  BuildingFloorInfo selectBuildingFloorInfoById(Long paramLong);
  
  List<BuildingFloorInfo> selectBuildingFloorInfoList(BuildingFloorInfo paramBuildingFloorInfo);
  
  BuildingFloorInfo selectBuildingFloorByName(BuildingFloorInfo paramBuildingFloorInfo);
  
  List<BuildingTerStatusVo> selectBuildingFloorInfoListStatus(BuildingFloorInfo paramBuildingFloorInfo);
  
  int insertBuildingFloorInfo(BuildingFloorInfo paramBuildingFloorInfo);
  
  int updateBuildingFloorInfo(BuildingFloorInfo paramBuildingFloorInfo);
  
  int deleteBuildingFloorInfoByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingFloorInfoById(Long paramLong);
  
  int insertBuildingFloorInfoBatch(List<BuildingFloorInfo> paramList);
  
  int deleteBuildingFloorInfoByFloorId(Integer[] paramArrayOfInteger);
  
  int deleteBuildingFloorInfoByBuildingId(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingFloorInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */