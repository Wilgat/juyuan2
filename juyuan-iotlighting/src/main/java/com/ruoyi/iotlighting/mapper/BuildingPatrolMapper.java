package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingPatrol;
import java.util.List;

public interface BuildingPatrolMapper {
  BuildingPatrol selectBuildingPatrolById(Long paramLong);
  
  List<BuildingPatrol> selectBuildingPatrolList(BuildingPatrol paramBuildingPatrol);
  
  List<BuildingPatrol> selectActiveBuildingPatrolList(BuildingPatrol paramBuildingPatrol);
  
  int insertBuildingPatrol(BuildingPatrol paramBuildingPatrol);
  
  int updateBuildingPatrol(BuildingPatrol paramBuildingPatrol);
  
  int deleteBuildingPatrolById(Long paramLong);
  
  int deleteBuildingPatrolByIds(Long[] paramArrayOfLong);
  
  List<BuildingPatrol> selectBuildingPatrolSelect(BuildingPatrol paramBuildingPatrol);
  
  int deleteBuildingPatrolByTerSn(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingPatrolMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */