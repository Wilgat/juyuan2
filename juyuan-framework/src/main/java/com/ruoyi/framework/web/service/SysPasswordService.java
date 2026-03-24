/*    */ package com.ruoyi.framework.web.service;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.common.core.redis.RedisCache;
/*    */ import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
/*    */ import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
/*    */ import com.ruoyi.common.utils.MessageUtils;
/*    */ import com.ruoyi.common.utils.SecurityUtils;
/*    */ import com.ruoyi.framework.manager.AsyncManager;
/*    */ import com.ruoyi.framework.manager.factory.AsyncFactory;
/*    */ import com.ruoyi.framework.security.context.AuthenticationContextHolder;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.security.core.Authentication;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class SysPasswordService
/*    */ {
/*    */   @Autowired
/*    */   private RedisCache redisCache;
/*    */   @Value("${user.password.maxRetryCount}")
/*    */   private int maxRetryCount;
/*    */   @Value("${user.password.lockTime}")
/*    */   private int lockTime;
/*    */   
/*    */   private String getCacheKey(String username) {
/* 45 */     return "pwd_err_cnt:" + username;
/*    */   }
/*    */ 
/*    */   
/*    */   public void validate(SysUser user) {
/* 50 */     Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
/* 51 */     String username = usernamePasswordAuthenticationToken.getName();
/* 52 */     String password = usernamePasswordAuthenticationToken.getCredentials().toString();
/*    */     
/* 54 */     Integer retryCount = (Integer)this.redisCache.getCacheObject(getCacheKey(username));
/*    */     
/* 56 */     if (retryCount == null)
/*    */     {
/* 58 */       retryCount = Integer.valueOf(0);
/*    */     }
/*    */     
/* 61 */     if (retryCount.intValue() >= Integer.valueOf(this.maxRetryCount).intValue()) {
/*    */       
/* 63 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", 
/* 64 */             MessageUtils.message("user.password.retry.limit.exceed", new Object[] { Integer.valueOf(this.maxRetryCount), Integer.valueOf(this.lockTime) }), new Object[0]));
/* 65 */       throw new UserPasswordRetryLimitExceedException(this.maxRetryCount, this.lockTime);
/*    */     } 
/*    */     
/* 68 */     if (!matches(user, password)) {
/*    */       
/* 70 */       retryCount = Integer.valueOf(retryCount.intValue() + 1);
/* 71 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, "Error", 
/* 72 */             MessageUtils.message("user.password.retry.limit.count", new Object[] { retryCount }), new Object[0]));
/* 73 */       this.redisCache.setCacheObject(getCacheKey(username), retryCount, Integer.valueOf(this.lockTime), TimeUnit.MINUTES);
/* 74 */       throw new UserPasswordNotMatchException();
/*    */     } 
/*    */ 
/*    */     
/* 78 */     clearLoginRecordCache(username);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(SysUser user, String rawPassword) {
/* 84 */     return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearLoginRecordCache(String loginName) {
/* 89 */     if (this.redisCache.hasKey(getCacheKey(loginName)).booleanValue())
/*    */     {
/* 91 */       this.redisCache.deleteObject(getCacheKey(loginName));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/SysPasswordService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */