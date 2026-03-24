package com.ruoyi.iotlighting.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.iotlighting.domain.TerBuildingPointInfo;
import java.util.List;

public interface ITerBuildingPointInfoService {
  TerBuildingPointInfo selectTerBuildingPointInfoByTerSn(String paramString);
  
  List<TerBuildingPointInfo> selectTerBuildingPointInfoList(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int insertTerBuildingPointInfo(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int updateTerBuildingPointInfo(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  int deleteTerBuildingPointInfoByTerSns(String[] paramArrayOfString);
  
  int deleteTerBuildingPointInfoByTerSn(String paramString);
  
  TerBuildingPointInfo selectTerBuildingPointInfoByPointid(Long paramLong);
  
  AjaxResult checkPointAndTer(TerBuildingPointInfo paramTerBuildingPointInfo);
  
  void installConfirm(String paramString);
  
  String getTerInfoBySnOrUpc(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerBuildingPointInfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */