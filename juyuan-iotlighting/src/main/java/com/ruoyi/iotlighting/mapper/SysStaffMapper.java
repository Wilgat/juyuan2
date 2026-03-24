package com.ruoyi.iotlighting.mapper;

import com.ruoyi.iotlighting.domain.SysStaff;
import java.util.List;

public interface SysStaffMapper {
  SysStaff selectSysStaffByUserId(Long paramLong);
  
  List<SysStaff> selectSysStaffList(SysStaff paramSysStaff);
  
  int insertSysStaff(SysStaff paramSysStaff);
  
  int updateSysStaff(SysStaff paramSysStaff);
  
  int deleteSysStaffByStaffId(Long paramLong);
  
  int deleteSysStaffByStaffIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-iotlighting-3.8.6/!/com/ruoyi/iotlighting/mapper/SysStaffMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */