package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
  int deleteUserRoleByUserId(Long paramLong);
  
  int deleteUserRole(Long[] paramArrayOfLong);
  
  int countUserRoleByRoleId(Long paramLong);
  
  int batchUserRole(List<SysUserRole> paramList);
  
  int deleteUserRoleInfo(SysUserRole paramSysUserRole);
  
  int deleteUserRoleInfos(@Param("roleId") Long paramLong, @Param("userIds") Long[] paramArrayOfLong);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysUserRoleMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */