package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerDataLog;
import java.util.List;

public interface TerDataLogMapper {
  TerDataLog selectTerDataLogById(Long paramLong);
  
  List<TerDataLog> selectTerDataLogList(TerDataLog paramTerDataLog);
  
  int insertTerDataLog(TerDataLog paramTerDataLog);
  
  int updateTerDataLog(TerDataLog paramTerDataLog);
  
  int deleteTerDataLogById(Long paramLong);
  
  int deleteTerDataLogByIds(Long[] paramArrayOfLong);
  
  List<TerDataLog> selectTerAlertLogList(TerDataLog paramTerDataLog);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerDataLogMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */