package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingPointInfo;
import java.util.List;
import java.util.Map;

public interface IBuildingPointInfoService {
  BuildingPointInfo selectBuildingPointInfoById(Long paramLong);
  
  List<BuildingPointInfo> selectBuildingPointInfoList(BuildingPointInfo paramBuildingPointInfo);
  
  List<BuildingPointInfo> selectBuildingPointTerList(BuildingPointInfo paramBuildingPointInfo);
  
  int insertBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  int updateBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  int deleteBuildingPointInfoByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingPointInfoById(Long paramLong);
  
  BuildingPointInfo selectBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  Map<String, List<String>> selectBuildingAreaInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingBlockInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingZoneInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingZoneSubInfo(BuildingPointInfo paramBuildingPointInfo);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingPointInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */