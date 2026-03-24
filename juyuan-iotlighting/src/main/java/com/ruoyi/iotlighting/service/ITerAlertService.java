package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerAlert;
import com.ruoyi.iotlighting.domain.TerAlertCount;
import java.util.List;

public interface ITerAlertService {
  int insertTerAlert(TerAlert paramTerAlert);
  
  int updateTerAlert(TerAlert paramTerAlert);
  
  List<TerAlert> selectTerAlertList(TerAlert paramTerAlert);
  
  List<TerAlert> selectTerAlertListNotLogin(TerAlert paramTerAlert);
  
  int dealTerAlert(Long paramLong);
  
  List<TerAlertCount> selectTerAlertCount(TerAlertCount paramTerAlertCount);
  
  int dealTerAlertBatch(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerAlertService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */