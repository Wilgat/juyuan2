package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.system.domain.SysUserOnline;

public interface ISysUserOnlineService {
  SysUserOnline selectOnlineByIpaddr(String paramString, LoginUser paramLoginUser);
  
  SysUserOnline selectOnlineByUserName(String paramString, LoginUser paramLoginUser);
  
  SysUserOnline selectOnlineByInfo(String paramString1, String paramString2, LoginUser paramLoginUser);
  
  SysUserOnline loginUserToUserOnline(LoginUser paramLoginUser);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysUserOnlineService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */