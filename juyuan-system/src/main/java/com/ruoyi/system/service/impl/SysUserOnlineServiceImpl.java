/*    */ package com.ruoyi.system.service.impl;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.system.domain.SysUserOnline;
/*    */ import com.ruoyi.system.service.ISysUserOnlineService;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class SysUserOnlineServiceImpl
/*    */   implements ISysUserOnlineService
/*    */ {
/*    */   public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
/* 27 */     if (StringUtils.equals(ipaddr, user.getIpaddr()))
/*    */     {
/* 29 */       return loginUserToUserOnline(user);
/*    */     }
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SysUserOnline selectOnlineByUserName(String userName, LoginUser user) {
/* 44 */     if (StringUtils.equals(userName, user.getUsername()))
/*    */     {
/* 46 */       return loginUserToUserOnline(user);
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
/* 62 */     if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
/*    */     {
/* 64 */       return loginUserToUserOnline(user);
/*    */     }
/* 66 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SysUserOnline loginUserToUserOnline(LoginUser user) {
/* 78 */     if (StringUtils.isNull(user) || StringUtils.isNull(user.getUser()))
/*    */     {
/* 80 */       return null;
/*    */     }
/* 82 */     SysUserOnline sysUserOnline = new SysUserOnline();
/* 83 */     sysUserOnline.setTokenId(user.getToken());
/* 84 */     sysUserOnline.setUserName(user.getUsername());
/* 85 */     sysUserOnline.setIpaddr(user.getIpaddr());
/* 86 */     sysUserOnline.setLoginLocation(user.getLoginLocation());
/* 87 */     sysUserOnline.setBrowser(user.getBrowser());
/* 88 */     sysUserOnline.setOs(user.getOs());
/* 89 */     sysUserOnline.setLoginTime(user.getLoginTime());
/* 90 */     if (StringUtils.isNotNull(user.getUser().getDept()))
/*    */     {
/* 92 */       sysUserOnline.setDeptName(user.getUser().getDept().getDeptName());
/*    */     }
/* 94 */     return sysUserOnline;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysUserOnlineServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */