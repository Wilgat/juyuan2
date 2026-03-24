package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingRoute;
import java.util.List;

public interface BuildingRouteMapper {
  List<BuildingRoute> selectBuildingRouteList(BuildingRoute paramBuildingRoute);
  
  int insertBuildingRoute(BuildingRoute paramBuildingRoute);
  
  int updateBuildingRoute(BuildingRoute paramBuildingRoute);
  
  int deleteBuildingRouteById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingRouteMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */