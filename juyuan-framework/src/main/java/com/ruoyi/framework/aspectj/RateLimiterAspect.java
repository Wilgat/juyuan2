/*    */ package com.ruoyi.framework.aspectj;
/*    */ 
/*    */ import com.ruoyi.common.annotation.RateLimiter;
/*    */ import com.ruoyi.common.enums.LimitType;
/*    */ import com.ruoyi.common.exception.ServiceException;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.common.utils.ip.IpUtils;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.aspectj.lang.JoinPoint;
/*    */ import org.aspectj.lang.annotation.Aspect;
/*    */ import org.aspectj.lang.annotation.Before;
/*    */ import org.aspectj.lang.reflect.MethodSignature;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.redis.core.RedisTemplate;
/*    */ import org.springframework.data.redis.core.script.RedisScript;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Aspect
/*    */ @Component
/*    */ public class RateLimiterAspect
/*    */ {
/* 31 */   private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);
/*    */   
/*    */   private RedisTemplate<Object, Object> redisTemplate;
/*    */   
/*    */   private RedisScript<Long> limitScript;
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   public void setRedisTemplate1(RedisTemplate<Object, Object> redisTemplate) {
/* 40 */     this.redisTemplate = redisTemplate;
/*    */   }
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   public void setLimitScript(RedisScript<Long> limitScript) {
/* 46 */     this.limitScript = limitScript;
/*    */   }
/*    */ 
/*    */   
/*    */   @Before("@annotation(rateLimiter)")
/*    */   public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
/* 52 */     int time = rateLimiter.time();
/* 53 */     int count = rateLimiter.count();
/*    */     
/* 55 */     String combineKey = getCombineKey(rateLimiter, point);
/* 56 */     List<Object> keys = Collections.singletonList(combineKey);
/*    */     
/*    */     try {
/* 59 */       Long number = (Long)this.redisTemplate.execute(this.limitScript, keys, new Object[] { Integer.valueOf(count), Integer.valueOf(time) });
/* 60 */       if (StringUtils.isNull(number) || number.intValue() > count)
/*    */       {
/* 62 */         throw new ServiceException("访问过于频繁，请稍候再试");
/*    */       }
/* 64 */       log.info("限制请求'{}',当前请求'{}',缓存key'{}'", new Object[] { Integer.valueOf(count), Integer.valueOf(number.intValue()), combineKey });
/*    */     }
/* 66 */     catch (ServiceException e) {
/*    */       
/* 68 */       throw e;
/*    */     }
/* 70 */     catch (Exception e) {
/*    */       
/* 72 */       throw new RuntimeException("服务器限流异常，请稍候再试");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
/* 78 */     StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
/* 79 */     if (rateLimiter.limitType() == LimitType.IP)
/*    */     {
/* 81 */       stringBuffer.append(IpUtils.getIpAddr()).append("-");
/*    */     }
/* 83 */     MethodSignature signature = (MethodSignature)point.getSignature();
/* 84 */     Method method = signature.getMethod();
/* 85 */     Class<?> targetClass = method.getDeclaringClass();
/* 86 */     stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
/* 87 */     return stringBuffer.toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/aspectj/RateLimiterAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */