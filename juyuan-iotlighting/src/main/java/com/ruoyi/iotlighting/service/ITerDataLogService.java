package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerDataLog;
import java.util.List;

public interface ITerDataLogService {
  TerDataLog selectTerDataLogById(Long paramLong);
  
  List<TerDataLog> selectTerDataLogList(TerDataLog paramTerDataLog);
  
  int insertTerDataLog(TerDataLog paramTerDataLog);
  
  int updateTerDataLog(TerDataLog paramTerDataLog);
  
  int deleteTerDataLogByIds(Long[] paramArrayOfLong);
  
  int deleteTerDataLogById(Long paramLong);
  
  List<TerDataLog> selectTerAlertLogList(TerDataLog paramTerDataLog);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerDataLogService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */