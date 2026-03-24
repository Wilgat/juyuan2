package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerAlertCount;
import com.ruoyi.iotlighting.domain.TerAlertSystem;
import java.util.List;

public interface ITerAlertSystemService {
  List<TerAlertSystem> selectSystemAlertList(TerAlertSystem paramTerAlertSystem);
  
  int dealTerAlertSystem(Long paramLong);
  
  int dealTerAlertSystemBatch(Long[] paramArrayOfLong);
  
  int updateTerAlertSystem(TerAlertSystem paramTerAlertSystem);
  
  int solverTerAlert(Long paramLong);
  
  List<TerAlertCount> selectTerAlertSystemCount(TerAlertCount paramTerAlertCount);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerAlertSystemService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */