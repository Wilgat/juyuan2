package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
import com.ruoyi.iotlighting.domain.BuildingTerStatus;
import java.util.List;

public interface BuildingFloorInfoMapper {
  BuildingFloorInfo selectBuildingFloorInfoById(Long paramLong);
  
  List<BuildingFloorInfo> selectBuildingFloorInfoList(BuildingFloorInfo paramBuildingFloorInfo);
  
  List<BuildingTerStatus> selectBuildingFloorInfoListStatus(BuildingFloorInfo paramBuildingFloorInfo);
  
  int insertBuildingFloorInfo(BuildingFloorInfo paramBuildingFloorInfo);
  
  int updateBuildingFloorInfo(BuildingFloorInfo paramBuildingFloorInfo);
  
  int deleteBuildingFloorInfoById(Long paramLong);
  
  int deleteBuildingFloorInfoByIds(Long[] paramArrayOfLong);
  
  int insertBuildingFloorInfoBatch(List<BuildingFloorInfo> paramList);
  
  int deleteBuildingFloorInfoByFloorId(Integer[] paramArrayOfInteger);
  
  int deleteBuildingFloorInfoByBuildingId(Long paramLong);
  
  BuildingFloorInfo selectBuildingFloorByName(BuildingFloorInfo paramBuildingFloorInfo);
  
  int selectTerCountByBuildingFloorIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingFloorInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */