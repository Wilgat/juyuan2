package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysOperLog;
import java.util.List;

public interface SysOperLogMapper {
  void insertOperlog(SysOperLog paramSysOperLog);
  
  List<SysOperLog> selectOperLogList(SysOperLog paramSysOperLog);
  
  int deleteOperLogByIds(Long[] paramArrayOfLong);
  
  SysOperLog selectOperLogById(Long paramLong);
  
  void cleanOperLog();
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysOperLogMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */