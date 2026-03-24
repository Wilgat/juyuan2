/*     */ package com.ruoyi.iotlighting.mqtt.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.MqttTopic;
/*     */ import com.ruoyi.common.exception.base.BaseException;
/*     */ import com.ruoyi.common.utils.DataUtils;
/*     */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*     */ import com.ruoyi.iotlighting.mqtt.gateway.MqttGateway;
/*     */ import com.ruoyi.iotlighting.mqtt.handle.MqttMessageHandle;
/*     */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service
/*     */ public class ISysMqttServiceImpl
/*     */   implements ISysMqttService {
/*     */   public ISysMqttServiceImpl(MqttPahoMessageDrivenChannelAdapter adapter, MqttGateway mqttGateway) {
/*  27 */     this.adapter = adapter; this.mqttGateway = mqttGateway;
/*  28 */   } private static final Logger log = LoggerFactory.getLogger(ISysMqttServiceImpl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private final MqttPahoMessageDrivenChannelAdapter adapter;
/*     */ 
/*     */ 
/*     */   
/*     */   private final MqttGateway mqttGateway;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private MqttMessageHandle mqttMessageHandle;
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map<String, Object>> getTopics() {
/*  45 */     List<Map<String, Object>> result = new ArrayList<>();
/*     */     
/*  47 */     String[] topics = this.adapter.getTopic();
/*  48 */     int[] qos = this.adapter.getQos();
/*     */     
/*  50 */     if (topics != null && topics.length > 0) {
/*  51 */       for (int i = 0; i < topics.length; i++) {
/*  52 */         Map<String, Object> map = new HashMap<>();
/*  53 */         map.put("topic", topics[i]);
/*  54 */         map.put("qos", Integer.valueOf(qos[i]));
/*  55 */         result.add(map);
/*     */       } 
/*     */     }
/*  58 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMsgToTopic(SysMqttBo mqttBo) {
/*  67 */     this.mqttGateway.sendToMqttRetained(mqttBo.getTopic(), mqttBo.getQos(), mqttBo.isRetained(), mqttBo.getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendAudioToTopic(SysMqttBo mqttBo) {
/*  75 */     String payload = mqttBo.getData();
/*  76 */     byte[] bytes = DataUtils.hexToByteArray(payload);
/*  77 */     this.mqttGateway.sendToMqttAudio(mqttBo.getTopic(), mqttBo.getQos(), mqttBo.isRetained(), bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTopic(SysMqttBo mqttBo) {
/*  85 */     String[] topics = this.adapter.getTopic();
/*  86 */     if (!Arrays.<String>asList(topics).contains(mqttBo.getTopic())) {
/*  87 */       this.adapter.addTopic(mqttBo.getTopic(), mqttBo.getQos().intValue());
/*     */     } else {
/*  89 */       throw new BaseException("当前主题已订阅！");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTopic(SysMqttBo mqttBo) {
/*  98 */     this.adapter.removeTopic(new String[] { mqttBo.getTopic() });
/*     */   }
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void registerTopic() {
/* 104 */     for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)this.mqttMessageHandle.getMqttServices().entrySet()) {
/*     */       
/* 106 */       Class<?> clazz = entry.getValue().getClass();
/*     */       
/* 108 */       Method[] methods = clazz.getDeclaredMethods();
/* 109 */       for (Method method : methods) {
/* 110 */         if (method.isAnnotationPresent((Class)MqttTopic.class)) {
/*     */           
/* 112 */           MqttTopic handleTopic = method.<MqttTopic>getAnnotation(MqttTopic.class);
/* 113 */           String topic = handleTopic.value();
/* 114 */           int qos = handleTopic.qos();
/* 115 */           SysMqttBo sysMqttBo = new SysMqttBo();
/* 116 */           sysMqttBo.setTopic(topic);
/* 117 */           sysMqttBo.setQos(Integer.valueOf(qos));
/* 118 */           addTopic(sysMqttBo);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/service/impl/ISysMqttServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */