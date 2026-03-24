package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysConfig;
import java.util.List;

public interface SysConfigMapper {
  SysConfig selectConfig(SysConfig paramSysConfig);
  
  SysConfig selectConfigById(Long paramLong);
  
  List<SysConfig> selectConfigList(SysConfig paramSysConfig);
  
  SysConfig checkConfigKeyUnique(String paramString);
  
  int insertConfig(SysConfig paramSysConfig);
  
  int updateConfig(SysConfig paramSysConfig);
  
  int deleteConfigById(Long paramLong);
  
  int deleteConfigByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysConfigMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */