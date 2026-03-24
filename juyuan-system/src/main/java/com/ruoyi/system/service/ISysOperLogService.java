package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysOperLog;
import java.util.List;

public interface ISysOperLogService {
  void insertOperlog(SysOperLog paramSysOperLog);
  
  List<SysOperLog> selectOperLogList(SysOperLog paramSysOperLog);
  
  int deleteOperLogByIds(Long[] paramArrayOfLong);
  
  SysOperLog selectOperLogById(Long paramLong);
  
  void cleanOperLog();
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysOperLogService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */