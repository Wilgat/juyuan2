package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerAlert;
import com.ruoyi.iotlighting.domain.TerAlertCount;
import java.util.List;

public interface TerAlertMapper {
  int insertTerAlert(TerAlert paramTerAlert);
  
  int updateTerAlert(TerAlert paramTerAlert);
  
  List<TerAlert> selectTerAlertList(TerAlert paramTerAlert);
  
  List<TerAlert> selectTerAlertListNotLogin(TerAlert paramTerAlert);
  
  List<TerAlertCount> selectTerAlertCount(TerAlertCount paramTerAlertCount);
  
  int updateTerAlertBatch(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerAlertMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */