package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingPointInfo;
import java.util.List;

public interface BuildingPointInfoMapper {
  BuildingPointInfo selectBuildingPointInfoById(Long paramLong);
  
  List<BuildingPointInfo> selectBuildingPointInfoList(BuildingPointInfo paramBuildingPointInfo);
  
  List<BuildingPointInfo> selectBuildingPointTerList(BuildingPointInfo paramBuildingPointInfo);
  
  int insertBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  int updateBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  int deleteBuildingPointInfoById(Long paramLong);
  
  int deleteBuildingPointInfoByIds(Long[] paramArrayOfLong);
  
  BuildingPointInfo selectBuildingPointInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingBlockInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingZoneInfo(BuildingPointInfo paramBuildingPointInfo);
  
  List<String> selectBuildingZoneSubInfo(BuildingPointInfo paramBuildingPointInfo);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingPointInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */