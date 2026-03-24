/*    */ package com.ruoyi.iotlighting.task;
/*    */ 
/*    */ import cn.hutool.core.util.IdUtil;
/*    */ import com.alibaba.fastjson2.JSONObject;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.iotlighting.domain.TerSensorStatus;
/*    */ import com.ruoyi.iotlighting.mapper.TerSensorStatusMapper;
/*    */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*    */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*    */ import java.time.LocalTime;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("lightingTask")
/*    */ public class LightingTask
/*    */ {
/* 24 */   private static final Logger log = LoggerFactory.getLogger(LightingTask.class);
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private TerSensorStatusMapper terSensorStatusMapper;
/*    */   
/*    */   @Autowired
/*    */   private ISysMqttService sysMqttService;
/*    */ 
/*    */   
/*    */   public void statusChange() {
/* 35 */     TerSensorStatus queryParam = new TerSensorStatus();
/* 36 */     queryParam.setSensorType("light");
/* 37 */     List<TerSensorStatus> terSensorStatusList = this.terSensorStatusMapper.selectTerSensorStatusListForTask(queryParam);
/* 38 */     for (TerSensorStatus terSensorStatus : terSensorStatusList) {
/*    */       try {
/* 40 */         String sensorConfigStr = terSensorStatus.getSensorConfig();
/* 41 */         if (StringUtils.isEmpty(sensorConfigStr)) {
/*    */           continue;
/*    */         }
/* 44 */         JSONObject sensorConfigJson = JSONObject.parseObject(sensorConfigStr);
/* 45 */         JSONObject dayConfigJson = sensorConfigJson.getJSONObject("day");
/* 46 */         String start = dayConfigJson.getString("start");
/* 47 */         String end = dayConfigJson.getString("end");
/*    */         
/* 49 */         LocalTime currentTime = LocalTime.now();
/*    */         
/* 51 */         LocalTime startTime = LocalTime.parse(start);
/* 52 */         LocalTime endTime = LocalTime.parse(end);
/*    */         
/* 54 */         String mode = sensorConfigJson.getString("mode");
/*    */         
/* 56 */         String status = "1";
/*    */         
/* 58 */         if (StringUtils.equals("2", mode)) {
/* 59 */           status = "2";
/*    */         
/*    */         }
/* 62 */         else if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
/* 63 */           status = "0";
/*    */         } 
/*    */         
/* 66 */         String lightStatusInDb = this.terSensorStatusMapper.selectLightStatus(terSensorStatus.getTerSn());
/* 67 */         boolean statusChange = false;
/* 68 */         if (StringUtils.isEmpty(lightStatusInDb)) {
/* 69 */           statusChange = true;
/* 70 */           this.terSensorStatusMapper.insertLightStatus(Long.valueOf(IdUtil.getSnowflakeNextId()), terSensorStatus.getTerSn(), status);
/* 71 */         } else if (!StringUtils.equals(status, lightStatusInDb)) {
/* 72 */           statusChange = true;
/* 73 */           this.terSensorStatusMapper.updateLightStatus(terSensorStatus.getTerSn(), status);
/*    */         } 
/* 75 */         if (statusChange) {
/* 76 */           SysMqttBo mqttBo = new SysMqttBo();
/* 77 */           mqttBo.setTopic("gateway/" + terSensorStatus.getGateway() + "/lightingstatus/light/" + terSensorStatus.getTerSn());
/* 78 */           mqttBo.setQos(Integer.valueOf(1));
/* 79 */           mqttBo.setRetained(true);
/* 80 */           mqttBo.setData(status);
/* 81 */           this.sysMqttService.sendMsgToTopic(mqttBo);
/* 82 */           log.info("发送消息:{}", JSONObject.toJSONString(mqttBo, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/*    */         } 
/* 84 */       } catch (Exception e) {
/* 85 */         log.info("灯管信息：{}", JSONObject.toJSONString(terSensorStatus, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/* 86 */         log.error("定时任务执行异常", e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/task/LightingTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */