package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.TerSensorStatus;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerSensorStatusMapper {
  List<TerSensorStatus> selectTerSensorStatusByTerSn(String paramString);
  
  List<TerSensorStatus> selectTerSensorStatusList(TerSensorStatus paramTerSensorStatus);
  
  List<TerSensorStatus> selectTerSensorStatusListForTask(TerSensorStatus paramTerSensorStatus);
  
  int insertTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  int updateTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  int deleteTerSensorStatusByTerSn(String paramString);
  
  int deleteTerSensorStatusByTerSns(String[] paramArrayOfString);
  
  int updateSertTerSensorStatus(TerSensorStatus paramTerSensorStatus);
  
  List<TerSensorStatus> selectTerSensorStatusListAlarm(TerSensorStatus paramTerSensorStatus);
  
  TerSensorStatus selectTerSensor(TerSensorStatus paramTerSensorStatus);
  
  String selectLightStatus(String paramString);
  
  int insertLightStatus(@Param("id") Long paramLong, @Param("terSn") String paramString1, @Param("status") String paramString2);
  
  int updateLightStatus(@Param("terSn") String paramString1, @Param("status") String paramString2);
  
  int deleteLightStatus(@Param("terSn") String paramString);
  
  int deleteTerLightStatusByTerSn(String paramString);
  
  Date getTerLightStatusUpdateTime();
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/TerSensorStatusMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */