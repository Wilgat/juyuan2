package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.IotMessage;
import com.ruoyi.iotlighting.domain.TerAlert;
import com.ruoyi.iotlighting.domain.TerInfo;

public interface IMqttMessageService {
  TerAlert handlSensorData(IotMessage paramIotMessage);
  
  TerInfo handlTerOnline(String paramString);
  
  void handlTerOffline(String paramString);
  
  void handleDiagnosis(IotMessage paramIotMessage);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/IMqttMessageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */