package com.ruoyi.iotlighting.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
  void sendToMqtt(@Header("mqtt_topic") String paramString1, String paramString2);
  
  void sendToMqtt(@Header("mqtt_topic") String paramString1, @Header("mqtt_qos") Integer paramInteger, String paramString2);
  
  void sendToMqttRetained(@Header("mqtt_topic") String paramString1, @Header("mqtt_qos") Integer paramInteger, @Header("mqtt_retained") boolean paramBoolean, String paramString2);
  
  void sendToMqttAudio(@Header("mqtt_topic") String paramString, @Header("mqtt_qos") Integer paramInteger, @Header("mqtt_retained") boolean paramBoolean, byte[] paramArrayOfbyte);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/gateway/MqttGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */