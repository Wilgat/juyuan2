package com.ruoyi.quartz.mapper;

import com.ruoyi.quartz.domain.SysJob;
import java.util.List;

public interface SysJobMapper {
  List<SysJob> selectJobList(SysJob paramSysJob);
  
  List<SysJob> selectJobAll();
  
  SysJob selectJobById(Long paramLong);
  
  int deleteJobById(Long paramLong);
  
  int deleteJobByIds(Long[] paramArrayOfLong);
  
  int updateJob(SysJob paramSysJob);
  
  int insertJob(SysJob paramSysJob);
}


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/mapper/SysJobMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */