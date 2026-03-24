package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysRoleDept;
import java.util.List;

public interface SysRoleDeptMapper {
  int deleteRoleDeptByRoleId(Long paramLong);
  
  int deleteRoleDept(Long[] paramArrayOfLong);
  
  int selectCountRoleDeptByDeptId(Long paramLong);
  
  int batchRoleDept(List<SysRoleDept> paramList);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysRoleDeptMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */