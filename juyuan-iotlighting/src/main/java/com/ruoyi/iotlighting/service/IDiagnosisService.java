package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.Diagnosis;
import java.util.List;

public interface IDiagnosisService {
  List<Diagnosis> selectDiagnosisList(Diagnosis paramDiagnosis);
  
  int updateGatewayStatus(Diagnosis paramDiagnosis);
  
  int updateTerStatus(Diagnosis paramDiagnosis);
  
  int updateSensorStatus(String paramString, List<String> paramList);
  
  int updateTerStatusOffline(Diagnosis paramDiagnosis);
  
  int updateSensorExtendStatus(String paramString, List<String> paramList);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IDiagnosisService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */