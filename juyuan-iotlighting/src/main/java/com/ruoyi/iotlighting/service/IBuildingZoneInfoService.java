package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingZoneInfo;
import java.util.List;

public interface IBuildingZoneInfoService {
  BuildingZoneInfo selectBuildingZoneInfoById(Long paramLong);
  
  List<BuildingZoneInfo> selectBuildingZoneInfoList(BuildingZoneInfo paramBuildingZoneInfo);
  
  int insertBuildingZoneInfo(BuildingZoneInfo paramBuildingZoneInfo);
  
  int updateBuildingZoneInfo(BuildingZoneInfo paramBuildingZoneInfo);
  
  int deleteBuildingZoneInfoByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingZoneInfoById(Long paramLong);
  
  String importZoneInfo(List<BuildingZoneInfo> paramList);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingZoneInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */