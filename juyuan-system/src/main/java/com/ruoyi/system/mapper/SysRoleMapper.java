package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysRole;
import java.util.List;

public interface SysRoleMapper {
  List<SysRole> selectRoleList(SysRole paramSysRole);
  
  List<SysRole> selectRolePermissionByUserId(Long paramLong);
  
  List<SysRole> selectRoleAll();
  
  List<Long> selectRoleListByUserId(Long paramLong);
  
  SysRole selectRoleById(Long paramLong);
  
  List<SysRole> selectRolesByUserName(String paramString);
  
  SysRole checkRoleNameUnique(String paramString);
  
  SysRole checkRoleKeyUnique(String paramString);
  
  int updateRole(SysRole paramSysRole);
  
  int insertRole(SysRole paramSysRole);
  
  int deleteRoleById(Long paramLong);
  
  int deleteRoleByIds(Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysRoleMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */