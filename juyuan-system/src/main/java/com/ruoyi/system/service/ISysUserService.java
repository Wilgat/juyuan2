package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import java.util.List;

public interface ISysUserService {
  List<SysUser> selectUserList(SysUser paramSysUser);
  
  List<SysUser> selectAllocatedList(SysUser paramSysUser);
  
  List<SysUser> selectUnallocatedList(SysUser paramSysUser);
  
  SysUser selectUserByUserName(String paramString);
  
  SysUser selectUserById(Long paramLong);
  
  String selectUserRoleGroup(String paramString);
  
  String selectUserPostGroup(String paramString);
  
  boolean checkUserNameUnique(SysUser paramSysUser);
  
  boolean checkPhoneUnique(SysUser paramSysUser);
  
  boolean checkEmailUnique(SysUser paramSysUser);
  
  void checkUserAllowed(SysUser paramSysUser);
  
  void checkUserDataScope(Long paramLong);
  
  int insertUser(SysUser paramSysUser);
  
  boolean registerUser(SysUser paramSysUser);
  
  int updateUser(SysUser paramSysUser);
  
  void insertUserAuth(Long paramLong, Long[] paramArrayOfLong);
  
  int updateUserStatus(SysUser paramSysUser);
  
  int updateUserProfile(SysUser paramSysUser);
  
  boolean updateUserAvatar(String paramString1, String paramString2);
  
  int resetPwd(SysUser paramSysUser);
  
  int resetUserPwd(String paramString1, String paramString2);
  
  int deleteUserById(Long paramLong);
  
  int deleteUserByIds(Long[] paramArrayOfLong);
  
  String importUser(List<SysUser> paramList, Boolean paramBoolean, String paramString);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysUserService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */