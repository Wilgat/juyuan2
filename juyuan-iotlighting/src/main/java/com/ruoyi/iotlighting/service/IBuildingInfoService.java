package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingInfo;
import java.util.List;

public interface IBuildingInfoService {
  BuildingInfo selectBuildingInfoById(Long paramLong);
  
  List<BuildingInfo> selectBuildingInfoList(BuildingInfo paramBuildingInfo);
  
  int insertBuildingInfo(BuildingInfo paramBuildingInfo);
  
  int updateBuildingInfo(BuildingInfo paramBuildingInfo);
  
  int deleteBuildingInfoByIds(Long[] paramArrayOfLong);
  
  int deleteBuildingInfoById(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IBuildingInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */