package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerBuildingPointInfo;
import java.util.List;

public interface TerBuildingPointInfoMapper {
  TerBuildingPointInfo selectTerBuildingPointInfoByTerSn(String paramString);
  
  List<TerBuildingPointInfo> selectTerBuildingPointInfoList(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int insertTerBuildingPointInfo(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int updateTerBuildingPointInfo(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int deleteTerBuildingPointInfoByTerSn(String paramString);
  
  int deleteTerBuildingPointInfoByTerSns(String[] paramArrayOfString);
  
  TerBuildingPointInfo selectTerBuildingPointInfoByPointId(Long paramLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerBuildingPointInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */