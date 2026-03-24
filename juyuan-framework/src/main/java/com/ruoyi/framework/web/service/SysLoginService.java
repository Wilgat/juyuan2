/*     */ package com.ruoyi.framework.web.service;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.redis.RedisCache;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.exception.user.BlackListException;
/*     */ import com.ruoyi.common.exception.user.CaptchaException;
/*     */ import com.ruoyi.common.exception.user.CaptchaExpireException;
/*     */ import com.ruoyi.common.exception.user.UserNotExistsException;
/*     */ import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.ip.IpUtils;
/*     */ import com.ruoyi.framework.manager.AsyncManager;
/*     */ import com.ruoyi.framework.manager.factory.AsyncFactory;
/*     */ import com.ruoyi.framework.security.context.AuthenticationContextHolder;
/*     */ import com.ruoyi.system.service.ISysConfigService;
/*     */ import com.ruoyi.system.service.ISysUserService;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.authentication.AuthenticationManager;
/*     */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class SysLoginService
/*     */ {
/*     */   @Autowired
/*     */   private TokenService tokenService;
/*     */   @Resource
/*     */   private AuthenticationManager authenticationManager;
/*     */   @Autowired
/*     */   private RedisCache redisCache;
/*     */   @Autowired
/*     */   private ISysUserService userService;
/*     */   @Autowired
/*     */   private ISysConfigService configService;
/*     */   
/*     */   public String login(String username, String password, String code, String uuid, String cid) {
/*  67 */     validateCaptcha(username, code, uuid);
/*     */     
/*  69 */     loginPreCheck(username, password);
/*     */     
/*  71 */     Authentication authentication = null;
/*     */     
/*     */     try {
/*  74 */       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
/*  75 */       AuthenticationContextHolder.setContext((Authentication)authenticationToken);
/*     */       
/*  77 */       authentication = this.authenticationManager.authenticate((Authentication)authenticationToken);
/*     */     }
/*  79 */     catch (Exception e) {
/*     */       
/*  81 */       if (e instanceof org.springframework.security.authentication.BadCredentialsException) {
/*     */         
/*  83 */         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.password.not.match", new Object[0]), new Object[0]));
/*  84 */         throw new UserPasswordNotMatchException();
/*     */       } 
/*     */ 
/*     */       
/*  88 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", e.getMessage(), new Object[0]));
/*  89 */       throw new ServiceException(e.getMessage());
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/*  94 */       AuthenticationContextHolder.clearContext();
/*     */     } 
/*  96 */     AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Success", MessageUtils.message("user.login.success", new Object[0]), new Object[0]));
/*  97 */     LoginUser loginUser = (LoginUser)authentication.getPrincipal();
/*  98 */     recordLoginInfo(loginUser.getUserId(), cid);
/*     */     
/* 100 */     return this.tokenService.createToken(loginUser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateCaptcha(String username, String code, String uuid) {
/* 113 */     boolean captchaEnabled = this.configService.selectCaptchaEnabled();
/* 114 */     if (captchaEnabled) {
/*     */       
/* 116 */       String verifyKey = "captcha_codes:" + (String)StringUtils.nvl(uuid, "");
/* 117 */       String captcha = (String)this.redisCache.getCacheObject(verifyKey);
/* 118 */       this.redisCache.deleteObject(verifyKey);
/* 119 */       if (captcha == null) {
/*     */         
/* 121 */         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.jcaptcha.expire", new Object[0]), new Object[0]));
/* 122 */         throw new CaptchaExpireException();
/*     */       } 
/* 124 */       if (!code.equalsIgnoreCase(captcha)) {
/*     */         
/* 126 */         AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.jcaptcha.error", new Object[0]), new Object[0]));
/* 127 */         throw new CaptchaException();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loginPreCheck(String username, String password) {
/* 140 */     if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
/*     */       
/* 142 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("not.null", new Object[0]), new Object[0]));
/* 143 */       throw new UserNotExistsException();
/*     */     } 
/*     */     
/* 146 */     if (password.length() < 5 || password
/* 147 */       .length() > 20) {
/*     */       
/* 149 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.password.not.match", new Object[0]), new Object[0]));
/* 150 */       throw new UserPasswordNotMatchException();
/*     */     } 
/*     */     
/* 153 */     if (username.length() < 2 || username
/* 154 */       .length() > 20) {
/*     */       
/* 156 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("user.password.not.match", new Object[0]), new Object[0]));
/* 157 */       throw new UserPasswordNotMatchException();
/*     */     } 
/*     */     
/* 160 */     String blackStr = this.configService.selectConfigByKey("sys.login.blackIPList");
/* 161 */     if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
/*     */       
/* 163 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", MessageUtils.message("login.blocked", new Object[0]), new Object[0]));
/* 164 */       throw new BlackListException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordLoginInfo(Long userId, String cid) {
/* 175 */     SysUser sysUser = new SysUser();
/* 176 */     sysUser.setUserId(userId);
/* 177 */     sysUser.setLoginIp(IpUtils.getIpAddr());
/* 178 */     sysUser.setLoginDate(DateUtils.getNowDate());
/* 179 */     sysUser.setCid(cid);
/* 180 */     this.userService.updateUserProfile(sysUser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appLogout(Long userId) {
/* 192 */     SysUser sysUser = new SysUser();
/* 193 */     sysUser.setUserId(userId);
/* 194 */     sysUser.setCid("");
/* 195 */     this.userService.updateUserProfile(sysUser);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/SysLoginService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */