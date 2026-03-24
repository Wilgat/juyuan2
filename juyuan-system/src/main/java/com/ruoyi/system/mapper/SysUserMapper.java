package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
  List<SysUser> selectUserList(SysUser paramSysUser);
  
  List<SysUser> selectAllocatedList(SysUser paramSysUser);
  
  List<SysUser> selectUnallocatedList(SysUser paramSysUser);
  
  SysUser selectUserByUserName(String paramString);
  
  SysUser selectUserById(Long paramLong);
  
  int insertUser(SysUser paramSysUser);
  
  int updateUser(SysUser paramSysUser);
  
  int updateUserAvatar(@Param("userName") String paramString1, @Param("avatar") String paramString2);
  
  int resetUserPwd(@Param("userName") String paramString1, @Param("password") String paramString2);
  
  int deleteUserById(Long paramLong);
  
  int deleteUserByIds(Long[] paramArrayOfLong);
  
  int deleteStaffByIds(Long[] paramArrayOfLong);
  
  SysUser checkUserNameUnique(String paramString);
  
  SysUser checkPhoneUnique(String paramString);
  
  SysUser checkEmailUnique(String paramString);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/mapper/SysUserMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */