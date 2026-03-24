package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.Diagnosis;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiagnosisMapper {
  List<Diagnosis> selectDiagnosisList(Diagnosis paramDiagnosis);
  
  int updateGatewayStatus(Diagnosis paramDiagnosis);
  
  int updateTerStatus(Diagnosis paramDiagnosis);
  
  int updateSensorStatus(@Param("terSn") String paramString, @Param("list") List<String> paramList);
  
  int updateTerStatusOffline(Diagnosis paramDiagnosis);
  
  int updateSensorExtendStatus(@Param("terSn") String paramString, @Param("list") List<String> paramList);
  
  List<Diagnosis> selectSensorExtendList(Diagnosis paramDiagnosis);
  
  void insertSensorExtendStatus(@Param("terSn") String paramString, @Param("list") List<String> paramList);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/DiagnosisMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */