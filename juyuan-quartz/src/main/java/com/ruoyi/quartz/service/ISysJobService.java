package com.ruoyi.quartz.service;

import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.quartz.domain.SysJob;
import java.util.List;
import org.quartz.SchedulerException;

public interface ISysJobService {
  List<SysJob> selectJobList(SysJob paramSysJob);
  
  SysJob selectJobById(Long paramLong);
  
  int pauseJob(SysJob paramSysJob) throws SchedulerException;
  
  int resumeJob(SysJob paramSysJob) throws SchedulerException;
  
  int deleteJob(SysJob paramSysJob) throws SchedulerException;
  
  void deleteJobByIds(Long[] paramArrayOfLong) throws SchedulerException;
  
  int changeStatus(SysJob paramSysJob) throws SchedulerException;
  
  boolean run(SysJob paramSysJob) throws SchedulerException;
  
  int insertJob(SysJob paramSysJob) throws SchedulerException, TaskException;
  
  int updateJob(SysJob paramSysJob) throws SchedulerException, TaskException;
  
  boolean checkCronExpressionIsValid(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/service/ISysJobService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */