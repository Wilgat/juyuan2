package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.BuildingInfo;
import java.util.List;

public interface BuildingInfoMapper {
  BuildingInfo selectBuildingInfoById(Long paramLong);
  
  List<BuildingInfo> selectBuildingInfoList(BuildingInfo paramBuildingInfo);
  
  int insertBuildingInfo(BuildingInfo paramBuildingInfo);
  
  int updateBuildingInfo(BuildingInfo paramBuildingInfo);
  
  int deleteBuildingInfoById(Long paramLong);
  
  int deleteBuildingInfoByIds(Long[] paramArrayOfLong);
  
  int selectBuildingTerCountByBuildingIds(Long[] paramArrayOfLong);
  
  int selectBuildingGatewayCountByBuildingIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/BuildingInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */