package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingPatrolLogDetail;
import java.util.List;

public interface BuildingPatrolLogDetailMapper {
  List<BuildingPatrolLogDetail> selectCurrentPatrolLogDetailList(BuildingPatrolLogDetail paramBuildingPatrolLogDetail);
  
  void insertBuildingPatrolLogDetail(BuildingPatrolLogDetail paramBuildingPatrolLogDetail);
  
  List<BuildingPatrolLogDetail> selectBuildingPatrolLogDetail(BuildingPatrolLogDetail paramBuildingPatrolLogDetail);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingPatrolLogDetailMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */