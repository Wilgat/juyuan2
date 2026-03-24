package com.ruoyi.iotlighting.service;

import com.ruoyi.iotlighting.domain.SysStaff;
import java.util.List;

public interface ISysStaffService {
  SysStaff selectSysStaffByUserId(Long paramLong);
  
  List<SysStaff> selectSysStaffList(SysStaff paramSysStaff);
  
  int insertSysStaff(SysStaff paramSysStaff);
  
  int updateSysStaff(SysStaff paramSysStaff);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/service/ISysStaffService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */