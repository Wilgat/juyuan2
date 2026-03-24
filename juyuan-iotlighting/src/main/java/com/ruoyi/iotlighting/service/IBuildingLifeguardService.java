package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingLifeguard;
import java.util.List;

public interface IBuildingLifeguardService {
  BuildingLifeguard selectBuildingLifeguardById(Long paramLong);
  
  List<BuildingLifeguard> selectBuildingLifeguardList(BuildingLifeguard paramBuildingLifeguard);
  
  int insertBuildingLifeguard(BuildingLifeguard paramBuildingLifeguard);
  
  int updateBuildingLifeguard(List<BuildingLifeguard> paramList);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingLifeguardService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */