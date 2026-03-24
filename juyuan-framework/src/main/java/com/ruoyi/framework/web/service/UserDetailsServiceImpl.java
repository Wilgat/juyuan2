/*    */ package com.ruoyi.framework.web.service;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.enums.UserStatus;
/*    */ import com.ruoyi.common.exception.ServiceException;
/*    */ import com.ruoyi.common.utils.MessageUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.system.service.ISysUserService;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.core.userdetails.UserDetails;
/*    */ import org.springframework.security.core.userdetails.UserDetailsService;
/*    */ import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UserDetailsServiceImpl
/*    */   implements UserDetailsService
/*    */ {
/* 26 */   private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private ISysUserService userService;
/*    */   
/*    */   @Autowired
/*    */   private SysPasswordService passwordService;
/*    */   
/*    */   @Autowired
/*    */   private SysPermissionService permissionService;
/*    */ 
/*    */   
/*    */   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/* 40 */     SysUser user = this.userService.selectUserByUserName(username);
/* 41 */     if (StringUtils.isNull(user)) {
/*    */       
/* 43 */       log.info("登录用户：{} 不存在.", username);
/* 44 */       throw new ServiceException(MessageUtils.message("user.not.exists", new Object[0]));
/*    */     } 
/* 46 */     if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
/*    */       
/* 48 */       log.info("登录用户：{} 已被删除.", username);
/* 49 */       throw new ServiceException(MessageUtils.message("user.password.delete", new Object[0]));
/*    */     } 
/* 51 */     if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
/*    */       
/* 53 */       log.info("登录用户：{} 已被停用.", username);
/* 54 */       throw new ServiceException(MessageUtils.message("user.blocked", new Object[0]));
/*    */     } 
/*    */     
/* 57 */     this.passwordService.validate(user);
/*    */     
/* 59 */     return createLoginUser(user);
/*    */   }
/*    */ 
/*    */   
/*    */   public UserDetails createLoginUser(SysUser user) {
/* 64 */     return (UserDetails)new LoginUser(user.getUserId(), user.getDeptId(), user, this.permissionService.getMenuPermission(user));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/UserDetailsServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */