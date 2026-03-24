package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
import com.ruoyi.iotlighting.domain.TerInfo;
import java.util.List;

public interface ITerInfoService {
  TerInfo selectTerInfoById(Long paramLong);
  
  List<TerInfo> selectTerInfoList(TerInfo paramTerInfo);
  
  List<TerInfo> selectTerInfoListAll(TerInfo paramTerInfo);
  
  List<TerInfo> selectTerInfoListNotLogin(TerInfo paramTerInfo);
  
  int insertTerInfo(TerInfo paramTerInfo);
  
  int updateTerInfo(TerInfo paramTerInfo);
  
  int deleteTerInfoByIds(Long[] paramArrayOfLong);
  
  int deleteTerInfoById(Long paramLong);
  
  TerInfo selectTerInfoBySn(String paramString);
  
  BuildingTerStatusVo selectTerCount();
  
  int updateTerStatus(TerInfo paramTerInfo);
  
  int updateTerOtaByIds(Long[] paramArrayOfLong);
  
  List<TerInfo> selectTerInfoBySnOrUpc(String paramString);
  
  int unbind(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */