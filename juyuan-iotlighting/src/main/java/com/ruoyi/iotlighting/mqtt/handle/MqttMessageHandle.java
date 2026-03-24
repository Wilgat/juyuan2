/*     */ package com.ruoyi.iotlighting.mqtt.handle;
/*     */ 
/*     */ import com.ruoyi.common.annotation.MqttService;
/*     */ import com.ruoyi.common.annotation.MqttTopic;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.messaging.Message;
/*     */ import org.springframework.messaging.MessageHandler;
/*     */ import org.springframework.messaging.MessagingException;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class MqttMessageHandle
/*     */   implements MessageHandler
/*     */ {
/*  23 */   private static final Logger log = LoggerFactory.getLogger(MqttMessageHandle.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, Object> mqttServices;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMessage(Message<?> message) throws MessagingException {
/*  37 */     getMqttTopicService(message);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getMqttServices() {
/*  41 */     if (mqttServices == null) {
/*  42 */       mqttServices = SpringUtils.getBeansByAnnotation(MqttService.class);
/*     */     }
/*  44 */     return mqttServices;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getMqttTopicService(Message<?> message) {
/*  49 */     String receivedTopic = (String)message.getHeaders().get("mqtt_receivedTopic", String.class);
/*  50 */     if (receivedTopic == null || "".equals(receivedTopic)) {
/*     */       return;
/*     */     }
/*  53 */     for (Map.Entry<String, Object> entry : getMqttServices().entrySet()) {
/*     */       
/*  55 */       Class<?> clazz = entry.getValue().getClass();
/*     */       
/*  57 */       Method[] methods = clazz.getDeclaredMethods();
/*  58 */       for (Method method : methods) {
/*  59 */         if (method.isAnnotationPresent((Class)MqttTopic.class)) {
/*     */           
/*  61 */           MqttTopic handleTopic = method.<MqttTopic>getAnnotation(MqttTopic.class);
/*  62 */           if (isMatch(receivedTopic, handleTopic.value())) {
/*     */             
/*     */             try {
/*  65 */               method.invoke(SpringUtils.getBean(clazz), new Object[] { message });
/*     */               return;
/*  67 */             } catch (IllegalAccessException e) {
/*  68 */               e.printStackTrace();
/*  69 */               log.error("代理炸了");
/*  70 */             } catch (InvocationTargetException e) {
/*  71 */               log.error("执行 {} 方法出现错误", handleTopic.value(), e);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMatch(String topic, String pattern) {
/*  89 */     if (topic == null || pattern == null) {
/*  90 */       return false;
/*     */     }
/*     */     
/*  93 */     if (topic.equals(pattern))
/*     */     {
/*  95 */       return true;
/*     */     }
/*     */     
/*  98 */     if ("#".equals(pattern))
/*     */     {
/* 100 */       return true;
/*     */     }
/* 102 */     String[] splitTopic = topic.split("/");
/* 103 */     String[] splitPattern = pattern.split("/");
/*     */     
/* 105 */     boolean match = true;
/*     */ 
/*     */     
/* 108 */     for (int i = 0; i < splitPattern.length && 
/* 109 */       !"#".equals(splitPattern[i]); i++) {
/*     */       
/* 111 */       if (i >= splitTopic.length) {
/*     */         
/* 113 */         match = false;
/*     */         break;
/*     */       } 
/* 116 */       if (!splitTopic[i].equals(splitPattern[i]) && !"+".equals(splitPattern[i])) {
/*     */         
/* 118 */         match = false;
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return match;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/handle/MqttMessageHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */