package com.ruoyi.quartz.service;

import com.ruoyi.quartz.domain.SysJobLog;
import java.util.List;

public interface ISysJobLogService {
  List<SysJobLog> selectJobLogList(SysJobLog paramSysJobLog);
  
  SysJobLog selectJobLogById(Long paramLong);
  
  void addJobLog(SysJobLog paramSysJobLog);
  
  int deleteJobLogByIds(Long[] paramArrayOfLong);
  
  int deleteJobLogById(Long paramLong);
  
  void cleanJobLog();
}


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/service/ISysJobLogService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */