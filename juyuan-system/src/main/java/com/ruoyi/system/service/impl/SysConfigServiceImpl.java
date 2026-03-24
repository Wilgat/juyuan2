/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataSource;
/*     */ import com.ruoyi.common.core.redis.RedisCache;
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import com.ruoyi.common.enums.DataSourceType;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.domain.SysConfig;
/*     */ import com.ruoyi.system.mapper.SysConfigMapper;
/*     */ import com.ruoyi.system.service.ISysConfigService;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
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
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class SysConfigServiceImpl
/*     */   implements ISysConfigService
/*     */ {
/*     */   @Autowired
/*     */   private SysConfigMapper configMapper;
/*     */   @Autowired
/*     */   private RedisCache redisCache;
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  40 */     loadingConfigCache();
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
/*     */   @DataSource(DataSourceType.MASTER)
/*     */   public SysConfig selectConfigById(Long configId) {
/*  53 */     SysConfig config = new SysConfig();
/*  54 */     config.setConfigId(configId);
/*  55 */     return this.configMapper.selectConfig(config);
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
/*     */   public String selectConfigByKey(String configKey) {
/*  67 */     String configValue = Convert.toStr(this.redisCache.getCacheObject(getCacheKey(configKey)));
/*  68 */     if (StringUtils.isNotEmpty(configValue))
/*     */     {
/*  70 */       return configValue;
/*     */     }
/*  72 */     SysConfig config = new SysConfig();
/*  73 */     config.setConfigKey(configKey);
/*  74 */     SysConfig retConfig = this.configMapper.selectConfig(config);
/*  75 */     if (StringUtils.isNotNull(retConfig)) {
/*     */       
/*  77 */       this.redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
/*  78 */       return retConfig.getConfigValue();
/*     */     } 
/*  80 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean selectCaptchaEnabled() {
/*  91 */     String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
/*  92 */     if (StringUtils.isEmpty(captchaEnabled))
/*     */     {
/*  94 */       return true;
/*     */     }
/*  96 */     return Convert.toBool(captchaEnabled).booleanValue();
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
/*     */   public List<SysConfig> selectConfigList(SysConfig config) {
/* 108 */     return this.configMapper.selectConfigList(config);
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
/*     */   public int insertConfig(SysConfig config) {
/* 120 */     int row = this.configMapper.insertConfig(config);
/* 121 */     if (row > 0)
/*     */     {
/* 123 */       this.redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
/*     */     }
/* 125 */     return row;
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
/*     */   public int updateConfig(SysConfig config) {
/* 137 */     SysConfig temp = this.configMapper.selectConfigById(config.getConfigId());
/* 138 */     if (!StringUtils.equals(temp.getConfigKey(), config.getConfigKey()))
/*     */     {
/* 140 */       this.redisCache.deleteObject(getCacheKey(temp.getConfigKey()));
/*     */     }
/*     */     
/* 143 */     int row = this.configMapper.updateConfig(config);
/* 144 */     if (row > 0)
/*     */     {
/* 146 */       this.redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
/*     */     }
/* 148 */     return row;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteConfigByIds(Long[] configIds) {
/* 159 */     for (Long configId : configIds) {
/*     */       
/* 161 */       SysConfig config = selectConfigById(configId);
/* 162 */       if (StringUtils.equals("Y", config.getConfigType()))
/*     */       {
/* 164 */         throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", new Object[] { config.getConfigKey() }));
/*     */       }
/* 166 */       this.configMapper.deleteConfigById(configId);
/* 167 */       this.redisCache.deleteObject(getCacheKey(config.getConfigKey()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadingConfigCache() {
/* 177 */     List<SysConfig> configsList = this.configMapper.selectConfigList(new SysConfig());
/* 178 */     for (SysConfig config : configsList)
/*     */     {
/* 180 */       this.redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearConfigCache() {
/* 190 */     Collection<String> keys = this.redisCache.keys("sys_config:*");
/* 191 */     this.redisCache.deleteObject(keys);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetConfigCache() {
/* 200 */     clearConfigCache();
/* 201 */     loadingConfigCache();
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
/*     */   public boolean checkConfigKeyUnique(SysConfig config) {
/* 213 */     Long configId = Long.valueOf(StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId().longValue());
/* 214 */     SysConfig info = this.configMapper.checkConfigKeyUnique(config.getConfigKey());
/* 215 */     if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
/*     */     {
/* 217 */       return false;
/*     */     }
/* 219 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getCacheKey(String configKey) {
/* 230 */     return "sys_config:" + configKey;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysConfigServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */