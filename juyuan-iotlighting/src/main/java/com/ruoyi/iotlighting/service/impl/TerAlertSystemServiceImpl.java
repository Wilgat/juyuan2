/*    */ package com.ruoyi.iotlighting.service.impl;
/*    */ 
/*    */ import cn.hutool.core.util.ObjectUtil;
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.ruoyi.common.annotation.DataScope;
/*    */ import com.ruoyi.common.exception.ServiceException;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.iotlighting.domain.TerAlertCount;
/*    */ import com.ruoyi.iotlighting.domain.TerAlertSystem;
/*    */ import com.ruoyi.iotlighting.domain.TerInfo;
/*    */ import com.ruoyi.iotlighting.mapper.TerAlertSystemMapper;
/*    */ import com.ruoyi.iotlighting.mapper.TerInfoMapper;
/*    */ import com.ruoyi.iotlighting.mqtt.bo.SysMqttBo;
/*    */ import com.ruoyi.iotlighting.mqtt.service.ISysMqttService;
/*    */ import com.ruoyi.iotlighting.service.ITerAlertSystemService;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class TerAlertSystemServiceImpl implements ITerAlertSystemService {
/* 25 */   private static final Logger log = LoggerFactory.getLogger(TerAlertSystemServiceImpl.class);
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private TerAlertSystemMapper terAlertSystemMapper;
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private TerInfoMapper terInfoMapper;
/*    */   
/*    */   @Autowired
/*    */   private ISysMqttService sysMqttService;
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<TerAlertSystem> selectSystemAlertList(TerAlertSystem terAlertSystem) {
/* 41 */     return this.terAlertSystemMapper.listTerAlertSystem(terAlertSystem);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateTerAlertSystem(TerAlertSystem terAlertSystem) {
/* 52 */     return this.terAlertSystemMapper.updateTerAlertSystem(terAlertSystem);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int solverTerAlert(Long id) {
/* 63 */     TerAlertSystem terAlertSystem = this.terAlertSystemMapper.selectTerAlertSystemById(id);
/* 64 */     if (ObjectUtil.isNull(terAlertSystem)) {
/* 65 */       throw new ServiceException("记录不存在");
/*    */     }
/* 67 */     SysMqttBo mqttBo = new SysMqttBo();
/* 68 */     if (StringUtils.equals(terAlertSystem.getAlertType(), "gateway")) {
/* 69 */       mqttBo.setTopic("gateway/" + terAlertSystem.getTerSn() + "/function/" + terAlertSystem.getTerSn());
/* 70 */       mqttBo.setData("restart");
/*    */     } else {
/* 72 */       TerInfo terInfo = this.terInfoMapper.selectTerInfoBySn(terAlertSystem.getTerSn());
/* 73 */       mqttBo.setTopic("gateway/" + terInfo.getGateway() + "/function/" + terAlertSystem.getTerSn());
/* 74 */       mqttBo.setData("reboot");
/*    */     } 
/* 76 */     mqttBo.setQos(Integer.valueOf(1));
/* 77 */     this.sysMqttService.sendMsgToTopic(mqttBo);
/* 78 */     log.info("发送重启指令:{}", JSON.toJSONString(mqttBo));
/* 79 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int dealTerAlertSystem(Long id) {
/* 84 */     TerAlertSystem terAlertSystem = new TerAlertSystem();
/* 85 */     terAlertSystem.setDealTime(new Date());
/* 86 */     terAlertSystem.setDealFlag("1");
/* 87 */     terAlertSystem.setId(id);
/* 88 */     return updateTerAlertSystem(terAlertSystem);
/*    */   }
/*    */ 
/*    */   
/*    */   public int dealTerAlertSystemBatch(Long[] ids) {
/* 93 */     return this.terAlertSystemMapper.updateTerAlertSystemBatch(ids);
/*    */   }
/*    */ 
/*    */   
/*    */   @DataScope(deptAlias = "d")
/*    */   public List<TerAlertCount> selectTerAlertSystemCount(TerAlertCount terAlertCount) {
/* 99 */     return this.terAlertSystemMapper.selectTerAlertSystemCount(terAlertCount);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/impl/TerAlertSystemServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */