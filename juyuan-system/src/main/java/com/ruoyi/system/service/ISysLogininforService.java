package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysLogininfor;
import java.util.List;

public interface ISysLogininforService {
  void insertLogininfor(SysLogininfor paramSysLogininfor);
  
  List<SysLogininfor> selectLogininforList(SysLogininfor paramSysLogininfor);
  
  int deleteLogininforByIds(Long[] paramArrayOfLong);
  
  void cleanLogininfor();
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysLogininforService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */