package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingZoneInfo;
import java.util.List;

public interface BuildingZoneInfoMapper {
  BuildingZoneInfo selectBuildingZoneInfoById(Long paramLong);
  
  List<BuildingZoneInfo> selectBuildingZoneInfoList(BuildingZoneInfo paramBuildingZoneInfo);
  
  int insertBuildingZoneInfo(BuildingZoneInfo paramBuildingZoneInfo);
  
  int updateBuildingZoneInfo(BuildingZoneInfo paramBuildingZoneInfo);
  
  int deleteBuildingZoneInfoById(Long paramLong);
  
  int deleteBuildingZoneInfoByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingZoneInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */