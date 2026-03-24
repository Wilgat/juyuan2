package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysConfig;
import java.util.List;

public interface ISysConfigService {
  SysConfig selectConfigById(Long paramLong);
  
  String selectConfigByKey(String paramString);
  
  boolean selectCaptchaEnabled();
  
  List<SysConfig> selectConfigList(SysConfig paramSysConfig);
  
  int insertConfig(SysConfig paramSysConfig);
  
  int updateConfig(SysConfig paramSysConfig);
  
  void deleteConfigByIds(Long[] paramArrayOfLong);
  
  void loadingConfigCache();
  
  void clearConfigCache();
  
  void resetConfigCache();
  
  boolean checkConfigKeyUnique(SysConfig paramSysConfig);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysConfigService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */