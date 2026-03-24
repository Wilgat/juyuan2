package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingPatrol;
import java.util.List;

public interface IBuildingPatrolService {
  BuildingPatrol selectBuildingPatrolById(Long paramLong);
  
  List<BuildingPatrol> selectBuildingPatrolList(BuildingPatrol paramBuildingPatrol);
  
  int insertBuildingPatrol(BuildingPatrol paramBuildingPatrol);
  
  int updateBuildingPatrol(List<BuildingPatrol> paramList);
  
  int deleteBuildingPatrolByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingPatrolById(Long paramLong);
  
  List<BuildingPatrol> selectBuildingPatrolSelect(BuildingPatrol paramBuildingPatrol);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingPatrolService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */