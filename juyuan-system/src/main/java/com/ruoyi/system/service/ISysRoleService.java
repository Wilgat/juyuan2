package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.domain.SysUserRole;
import java.util.List;
import java.util.Set;

public interface ISysRoleService {
  List<SysRole> selectRoleList(SysRole paramSysRole);
  
  List<SysRole> selectRolesByUserId(Long paramLong);
  
  Set<String> selectRolePermissionByUserId(Long paramLong);
  
  List<SysRole> selectRoleAll();
  
  List<Long> selectRoleListByUserId(Long paramLong);
  
  SysRole selectRoleById(Long paramLong);
  
  boolean checkRoleNameUnique(SysRole paramSysRole);
  
  boolean checkRoleKeyUnique(SysRole paramSysRole);
  
  void checkRoleAllowed(SysRole paramSysRole);
  
  void checkRoleDataScope(Long paramLong);
  
  int countUserRoleByRoleId(Long paramLong);
  
  int insertRole(SysRole paramSysRole);
  
  int updateRole(SysRole paramSysRole);
  
  int updateRoleStatus(SysRole paramSysRole);
  
  int authDataScope(SysRole paramSysRole);
  
  int deleteRoleById(Long paramLong);
  
  int deleteRoleByIds(Long[] paramArrayOfLong);
  
  int deleteAuthUser(SysUserRole paramSysUserRole);
  
  int deleteAuthUsers(Long paramLong, Long[] paramArrayOfLong);
  
  int insertAuthUsers(Long paramLong, Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysRoleService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */