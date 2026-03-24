package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingLifeguard;
import java.util.List;

public interface BuildingLifeguardMapper {
  BuildingLifeguard selectBuildingLifeguardById(Long paramLong);
  
  List<BuildingLifeguard> selectBuildingLifeguardList(BuildingLifeguard paramBuildingLifeguard);
  
  int insertBuildingLifeguard(BuildingLifeguard paramBuildingLifeguard);
  
  int updateBuildingLifeguard(BuildingLifeguard paramBuildingLifeguard);
  
  int deleteBuildingLifeguardById(Long paramLong);
  
  int deleteBuildingLifeguardByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingLifeguardMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */