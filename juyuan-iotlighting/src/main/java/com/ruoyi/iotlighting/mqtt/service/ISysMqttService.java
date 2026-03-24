package com.ruoyi.iotlighting.mqtt.service;

import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
import java.util.List;
import java.util.Map;

public interface ISysMqttService {
  List<Map<String, Object>> getTopics();
  
  void sendMsgToTopic(SysMqttBo paramSysMqttBo);
  
  void sendAudioToTopic(SysMqttBo paramSysMqttBo);
  
  void addTopic(SysMqttBo paramSysMqttBo);
  
  void removeTopic(SysMqttBo paramSysMqttBo);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/service/ISysMqttService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */