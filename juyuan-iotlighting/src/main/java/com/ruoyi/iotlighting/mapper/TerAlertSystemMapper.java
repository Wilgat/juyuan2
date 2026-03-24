package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerAlertCount;
import com.ruoyi.iotlighting.domain.TerAlertSystem;
import java.util.List;

public interface TerAlertSystemMapper {
  List<TerAlertSystem> listTerAlertSystem(TerAlertSystem paramTerAlertSystem);
  
  TerAlertSystem queryAlertSystemLaste(TerAlertSystem paramTerAlertSystem);
  
  int insertTerAlertSystem(TerAlertSystem paramTerAlertSystem);
  
  int updateTerAlertSystem(TerAlertSystem paramTerAlertSystem);
  
  int updateTerAlertSystemBatch(Long[] paramArrayOfLong);
  
  TerAlertSystem selectTerAlertSystemById(Long paramLong);
  
  List<TerAlertCount> selectTerAlertSystemCount(TerAlertCount paramTerAlertCount);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerAlertSystemMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */