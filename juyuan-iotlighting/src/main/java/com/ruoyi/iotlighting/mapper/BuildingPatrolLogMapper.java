package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
import java.util.List;

public interface BuildingPatrolLogMapper {
  BuildingPatrolLog selectBuildingPatrolLogById(Long paramLong);
  
  List<BuildingPatrolLog> selectBuildingPatrolLogList(BuildingPatrolLog paramBuildingPatrolLog);
  
  int insertBuildingPatrolLog(BuildingPatrolLog paramBuildingPatrolLog);
  
  int updateBuildingPatrolLog(BuildingPatrolLog paramBuildingPatrolLog);
  
  int deleteBuildingPatrolLogById(Long paramLong);
  
  int deleteBuildingPatrolLogByIds(Long[] paramArrayOfLong);
  
  BuildingPatrolLog selectBuildingPatrolLogByRouteId(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingPatrolLogMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */