/*    */ package com.ruoyi.framework.web.service;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.common.core.domain.model.RegisterBody;
/*    */ import com.ruoyi.common.core.redis.RedisCache;
/*    */ import com.ruoyi.common.exception.user.CaptchaException;
/*    */ import com.ruoyi.common.exception.user.CaptchaExpireException;
/*    */ import com.ruoyi.common.utils.MessageUtils;
/*    */ import com.ruoyi.common.utils.SecurityUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.framework.manager.AsyncManager;
/*    */ import com.ruoyi.framework.manager.factory.AsyncFactory;
/*    */ import com.ruoyi.system.service.ISysConfigService;
/*    */ import com.ruoyi.system.service.ISysUserService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ @Component
/*    */ public class SysRegisterService
/*    */ {
/*    */   @Autowired
/*    */   private ISysUserService userService;
/*    */   @Autowired
/*    */   private ISysConfigService configService;
/*    */   @Autowired
/*    */   private RedisCache redisCache;
/*    */   
/*    */   public String register(RegisterBody registerBody) {
/* 41 */     String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
/* 42 */     SysUser sysUser = new SysUser();
/* 43 */     sysUser.setUserName(username);
/*    */ 
/*    */     
/* 46 */     boolean captchaEnabled = this.configService.selectCaptchaEnabled();
/* 47 */     if (captchaEnabled) {
/* 48 */       validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
/*    */     }
/*    */     
/* 51 */     if (StringUtils.isEmpty(username)) {
/* 52 */       msg = "用户名不能为空";
/* 53 */     } else if (StringUtils.isEmpty(password)) {
/* 54 */       msg = "用户密码不能为空";
/* 55 */     } else if (username.length() < 2 || username
/* 56 */       .length() > 20) {
/* 57 */       msg = "账户长度必须在2到20个字符之间";
/* 58 */     } else if (password.length() < 5 || password
/* 59 */       .length() > 20) {
/* 60 */       msg = "密码长度必须在5到20个字符之间";
/* 61 */     } else if (!this.userService.checkUserNameUnique(sysUser)) {
/* 62 */       msg = "保存用户'" + username + "'失败，注册账号已存在";
/*    */     } else {
/* 64 */       sysUser.setNickName(username);
/* 65 */       sysUser.setPassword(SecurityUtils.encryptPassword(password));
/* 66 */       boolean regFlag = this.userService.registerUser(sysUser);
/* 67 */       if (!regFlag) {
/* 68 */         msg = "注册失败,请联系系统管理人员";
/*    */       } else {
/* 70 */         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Register", MessageUtils.message("user.register.success", new Object[0]), new Object[0]));
/*    */       } 
/*    */     } 
/* 73 */     return msg;
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
/*    */   public void validateCaptcha(String username, String code, String uuid) {
/* 85 */     String verifyKey = "captcha_codes:" + (String)StringUtils.nvl(uuid, "");
/* 86 */     String captcha = (String)this.redisCache.getCacheObject(verifyKey);
/* 87 */     this.redisCache.deleteObject(verifyKey);
/* 88 */     if (captcha == null) {
/* 89 */       throw new CaptchaExpireException();
/*    */     }
/* 91 */     if (!code.equalsIgnoreCase(captcha))
/* 92 */       throw new CaptchaException(); 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/SysRegisterService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */