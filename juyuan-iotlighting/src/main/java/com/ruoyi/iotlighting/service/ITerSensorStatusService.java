package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.TerSensorStatus;
import java.util.List;

public interface ITerSensorStatusService {
  List<TerSensorStatus> selectTerSensorStatusByTerSn(String paramString);
  
  List<TerSensorStatus> selectTerSensorStatusList(TerSensorStatus paramTerSensorStatus);
  
  List<TerSensorStatus> selectTerSensorStatusListAlarm(TerSensorStatus paramTerSensorStatus);
  
  int insertTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  int updateTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  int updateTerSensorConfig(TerSensorStatus paramTerSensorStatus);
  
  int deleteTerSensorStatusByTerSns(String[] paramArrayOfString);
  
  int deleteTerSensorStatusByTerSn(String paramString);
  
  int updateSertTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  TerSensorStatus selectTerSensorByType(String paramString1, String paramString2);
  
  int deleteTerLightStatusByTerSn(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ITerSensorStatusService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */