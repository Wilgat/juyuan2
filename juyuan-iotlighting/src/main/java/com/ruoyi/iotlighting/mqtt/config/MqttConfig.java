/*     */ package com.ruoyi.iotlighting.mqtt.config;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.iotlighting.mqtt.config.properties.MqttProperties;
/*     */ import com.ruoyi.iotlighting.mqtt.handle.MqttMessageHandle;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.integration.annotation.IntegrationComponentScan;
/*     */ import org.springframework.integration.channel.ExecutorChannel;
/*     */ import org.springframework.integration.dsl.IntegrationFlow;
/*     */ import org.springframework.integration.dsl.IntegrationFlowBuilder;
/*     */ import org.springframework.integration.dsl.IntegrationFlows;
/*     */ import org.springframework.integration.endpoint.MessageProducerSupport;
/*     */ import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
/*     */ import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
/*     */ import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
/*     */ import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
/*     */ import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
/*     */ import org.springframework.integration.mqtt.support.MqttMessageConverter;
/*     */ import org.springframework.messaging.MessageChannel;
/*     */ import org.springframework.messaging.MessageHandler;
/*     */ import org.springframework.messaging.converter.MessageConverter;
/*     */ import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Configuration
/*     */ @IntegrationComponentScan({"com.ruoyi.iotlighting"})
/*     */ public class MqttConfig
/*     */ {
/*     */   @Autowired
/*     */   private MqttProperties mqttProperties;
/*     */   @Autowired
/*     */   private MqttMessageHandle mqttMessageHandle;
/*     */   
/*     */   @Bean
/*     */   public MqttPahoClientFactory mqttPahoClientFactory() {
/*  45 */     DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
/*  46 */     MqttConnectOptions options = new MqttConnectOptions();
/*  47 */     options.setServerURIs(this.mqttProperties.getHostUrl().split(","));
/*  48 */     options.setUserName(this.mqttProperties.getUsername());
/*  49 */     options.setPassword(this.mqttProperties.getPassword().toCharArray());
/*     */     
/*  51 */     options.setAutomaticReconnect(true);
/*     */     
/*  53 */     options.setKeepAliveInterval(this.mqttProperties.getKeepalive());
/*     */     
/*  55 */     options.setCleanSession(this.mqttProperties.isClearSession());
/*     */     
/*  57 */     options.setConnectionTimeout(this.mqttProperties.getTimeout());
/*  58 */     options.setMaxInflight(1000);
/*  59 */     factory.setConnectionOptions(options);
/*  60 */     return (MqttPahoClientFactory)factory;
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public MqttPahoMessageDrivenChannelAdapter adapter(MqttPahoClientFactory factory) {
/*  66 */     return new MqttPahoMessageDrivenChannelAdapter(this.mqttProperties.getInClientId(), factory, this.mqttProperties.getDefaultTopic().split(","));
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public IntegrationFlow mqttInbound(MqttPahoMessageDrivenChannelAdapter adapter) {
/*  72 */     adapter.setCompletionTimeout(5000L);
/*  73 */     adapter.setQos(new int[] { 1 });
/*     */     
/*  75 */     DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
/*  76 */     converter.setPayloadAsBytes(true);
/*  77 */     adapter.setConverter((MqttMessageConverter)converter);
/*     */     
/*  79 */     return (IntegrationFlow)((IntegrationFlowBuilder)((IntegrationFlowBuilder)IntegrationFlows.from((MessageProducerSupport)adapter)
/*  80 */       .channel((MessageChannel)new ExecutorChannel((Executor)mqttThreadPoolTaskExecutor())))
/*  81 */       .handle((MessageHandler)this.mqttMessageHandle))
/*  82 */       .get();
/*     */   }
/*     */   
/*     */   @Bean
/*     */   public ThreadPoolTaskExecutor mqttThreadPoolTaskExecutor() {
/*  87 */     ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
/*     */     
/*  89 */     int maxPoolSize = 200;
/*     */     
/*  91 */     int corePoolSize = 50;
/*     */     
/*  93 */     int queueCapacity = 1000;
/*     */     
/*  95 */     int keepAliveSeconds = 300;
/*     */     
/*  97 */     executor.setMaxPoolSize(maxPoolSize);
/*  98 */     executor.setCorePoolSize(corePoolSize);
/*  99 */     executor.setQueueCapacity(queueCapacity);
/* 100 */     executor.setKeepAliveSeconds(keepAliveSeconds);
/*     */     
/* 102 */     executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
/* 103 */     return executor;
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public IntegrationFlow mqttOutboundFlow(MqttPahoClientFactory factory) {
/* 109 */     MqttPahoMessageHandler handler = new MqttPahoMessageHandler(this.mqttProperties.getOutClientId(), factory);
/* 110 */     handler.setAsync(true);
/* 111 */     handler.setConverter((MessageConverter)new DefaultPahoMessageConverter());
/* 112 */     if (StringUtils.isNotEmpty(this.mqttProperties.getDefaultTopic())) {
/* 113 */       handler.setDefaultTopic(this.mqttProperties.getDefaultTopic().split(",")[0]);
/*     */     }
/* 115 */     return (IntegrationFlow)((IntegrationFlowBuilder)IntegrationFlows.from("mqttOutboundChannel").handle((MessageHandler)handler)).get();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mqtt/config/MqttConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */