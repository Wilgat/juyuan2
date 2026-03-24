package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
import com.ruoyi.iotlighting.domain.BuildingPatrolLogDetail;
import java.util.List;

public interface IBuildingPatrolLogService {
  BuildingPatrolLog selectBuildingPatrolLogById(Long paramLong);
  
  List<BuildingPatrolLog> selectBuildingPatrolLogList(BuildingPatrolLog paramBuildingPatrolLog);
  
  int insertBuildingPatrolLog(BuildingPatrolLog paramBuildingPatrolLog);
  
  int updateBuildingPatrolLog(BuildingPatrolLog paramBuildingPatrolLog);
  
  int deleteBuildingPatrolLogByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingPatrolLogById(Long paramLong);
  
  List<BuildingPatrolLogDetail> selectCurrentPatrolLogDetailList(BuildingPatrolLogDetail paramBuildingPatrolLogDetail);
  
  void handlePatrolDetailData(String paramString1, String paramString2);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingPatrolLogService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */