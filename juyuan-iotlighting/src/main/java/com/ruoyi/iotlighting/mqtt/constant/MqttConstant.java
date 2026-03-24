package com.ruoyi.iotlighting.mqtt.constant;

public interface MqttConstant {
  public static final String MQTT_TOPIC_ALL = "lighting/+/+/+/+/+/+";
  
  public static final String MQTT_TOPIC_STATUS = "lighting/+/status";
  
  public static final String MQTT_TOPIC_GATEWAY_CONFIG = "gateway/{gateway}/lightinglist/{sn}";
  
  public static final String MQTT_TOPIC_MOVEMENT = "lighting/+/+/+/+/movement";
  
  public static final String MQTT_TOPIC_SPEED = "lighting/+/+/+/+/speed";
  
  public static final String MQTT_TOPIC_SMOKING = "lighting/+/+/+/+/smoking";
  
  public static final String MQTT_TOPIC_WATER = "lighting/+/+/+/+/water";
  
  public static final String MQTT_TOPIC_POST = "lighting/+/+/+/+/post";
  
  public static final String MQTT_TOPIC_TEMPERATURE = "lighting/+/+/+/+/temperature";
  
  public static final String MQTT_TOPIC_LIGHT = "lighting/+/+/+/+/light";
  
  public static final int QOS_0 = 0;
  
  public static final int QOS_1 = 1;
  
  public static final int QOS_2 = 2;
  
  public static final String MQTT_TOPIC_DIAGNOSIS = "gateway/+/health/+";
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/constant/MqttConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */