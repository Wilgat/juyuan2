package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerInfo;
import java.util.List;

public interface TerInfoMapper {
  TerInfo selectTerInfoById(Long paramLong);
  
  List<TerInfo> selectTerInfoList(TerInfo paramTerInfo);
  
  List<TerInfo> selectTerInfoStatusList(TerInfo paramTerInfo);
  
  List<TerInfo> selectTerInfoStatusListAll(TerInfo paramTerInfo);
  
  List<TerInfo> selectTerInfoStatusListNotLogin(TerInfo paramTerInfo);
  
  int insertTerInfo(TerInfo paramTerInfo);
  
  int updateTerInfo(TerInfo paramTerInfo);
  
  int deleteTerInfoById(Long paramLong);
  
  int deleteTerInfoByIds(Long[] paramArrayOfLong);
  
  TerInfo selectTerInfoBySn(String paramString);
  
  List<TerInfo> selectTerInfoBySnOrUpc(TerInfo paramTerInfo);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerInfoMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */