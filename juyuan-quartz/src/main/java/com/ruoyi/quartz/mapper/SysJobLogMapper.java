package com.ruoyi.quartz.mapper;

import com.ruoyi.quartz.domain.SysJobLog;
import java.util.List;

public interface SysJobLogMapper {
  List<SysJobLog> selectJobLogList(SysJobLog paramSysJobLog);
  
  List<SysJobLog> selectJobLogAll();
  
  SysJobLog selectJobLogById(Long paramLong);
  
  int insertJobLog(SysJobLog paramSysJobLog);
  
  int deleteJobLogByIds(Long[] paramArrayOfLong);
  
  int deleteJobLogById(Long paramLong);
  
  void cleanJobLog();
}


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/mapper/SysJobLogMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */