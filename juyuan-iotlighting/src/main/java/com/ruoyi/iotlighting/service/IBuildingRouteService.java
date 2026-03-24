package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingRoute;
import java.util.List;

public interface IBuildingRouteService {
  List<BuildingRoute> selectBuildingRouteList(BuildingRoute paramBuildingRoute);
  
  Long startRoutePatrol(Long paramLong1, Long paramLong2);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingRouteService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */