/*     */ package com.ruoyi.framework.aspectj;
/*     */ 
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.alibaba.fastjson2.filter.Filter;
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.enums.BusinessStatus;
/*     */ import com.ruoyi.common.enums.HttpMethod;
/*     */ import com.ruoyi.common.filter.PropertyPreExcludeFilter;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.ServletUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.ip.IpUtils;
/*     */ import com.ruoyi.framework.manager.AsyncManager;
/*     */ import com.ruoyi.framework.manager.factory.AsyncFactory;
/*     */ import com.ruoyi.system.domain.SysOperLog;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletRequest;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.aspectj.lang.JoinPoint;
/*     */ import org.aspectj.lang.annotation.AfterReturning;
/*     */ import org.aspectj.lang.annotation.AfterThrowing;
/*     */ import org.aspectj.lang.annotation.Aspect;
/*     */ import org.aspectj.lang.annotation.Before;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.NamedThreadLocal;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Aspect
/*     */ @Component
/*     */ public class LogAspect
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
/*     */ 
/*     */   
/*  45 */   public static final String[] EXCLUDE_PROPERTIES = new String[] { "password", "oldPassword", "newPassword", "confirmPassword" };
/*     */ 
/*     */   
/*  48 */   private static final ThreadLocal<Long> TIME_THREADLOCAL = (ThreadLocal<Long>)new NamedThreadLocal("Cost Time");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Before("@annotation(controllerLog)")
/*     */   public void boBefore(JoinPoint joinPoint, Log controllerLog) {
/*  56 */     TIME_THREADLOCAL.set(Long.valueOf(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
/*     */   public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
/*  67 */     handleLog(joinPoint, controllerLog, null, jsonResult);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
/*     */   public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
/*  79 */     handleLog(joinPoint, controllerLog, e, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object jsonResult) {
/*     */     try {
/*  87 */       LoginUser loginUser = SecurityUtils.getLoginUser();
/*     */ 
/*     */       
/*  90 */       SysOperLog operLog = new SysOperLog();
/*  91 */       operLog.setStatus(Integer.valueOf(BusinessStatus.SUCCESS.ordinal()));
/*     */       
/*  93 */       String ip = IpUtils.getIpAddr();
/*  94 */       operLog.setOperIp(ip);
/*  95 */       operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
/*  96 */       if (loginUser != null)
/*     */       {
/*  98 */         operLog.setOperName(loginUser.getUsername());
/*     */       }
/*     */       
/* 101 */       if (e != null) {
/*     */         
/* 103 */         operLog.setStatus(Integer.valueOf(BusinessStatus.FAIL.ordinal()));
/* 104 */         operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
/*     */       } 
/*     */       
/* 107 */       String className = joinPoint.getTarget().getClass().getName();
/* 108 */       String methodName = joinPoint.getSignature().getName();
/* 109 */       operLog.setMethod(className + "." + methodName + "()");
/*     */       
/* 111 */       operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
/*     */       
/* 113 */       getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
/*     */       
/* 115 */       operLog.setCostTime(Long.valueOf(System.currentTimeMillis() - ((Long)TIME_THREADLOCAL.get()).longValue()));
/*     */       
/* 117 */       AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
/*     */     }
/* 119 */     catch (Exception exp) {
/*     */ 
/*     */       
/* 122 */       log.error("异常信息:{}", exp.getMessage());
/* 123 */       exp.printStackTrace();
/*     */     }
/*     */     finally {
/*     */       
/* 127 */       TIME_THREADLOCAL.remove();
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
/*     */ 
/*     */   
/*     */   public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult) throws Exception {
/* 141 */     operLog.setBusinessType(Integer.valueOf(log.businessType().ordinal()));
/*     */     
/* 143 */     operLog.setTitle(log.title());
/*     */     
/* 145 */     operLog.setOperatorType(Integer.valueOf(log.operatorType().ordinal()));
/*     */     
/* 147 */     if (log.isSaveRequestData())
/*     */     {
/*     */       
/* 150 */       setRequestValue(joinPoint, operLog, log.excludeParamNames());
/*     */     }
/*     */     
/* 153 */     if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult))
/*     */     {
/* 155 */       operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
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
/*     */   private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) throws Exception {
/* 167 */     Map<?, ?> paramsMap = ServletUtils.getParamMap((ServletRequest)ServletUtils.getRequest());
/* 168 */     String requestMethod = operLog.getRequestMethod();
/* 169 */     if (StringUtils.isEmpty(paramsMap) && (HttpMethod.PUT
/* 170 */       .name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))) {
/*     */       
/* 172 */       String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
/* 173 */       operLog.setOperParam(StringUtils.substring(params, 0, 2000));
/*     */     }
/*     */     else {
/*     */       
/* 177 */       operLog.setOperParam(StringUtils.substring(JSON.toJSONString(paramsMap, (Filter)excludePropertyPreFilter(excludeParamNames), new com.alibaba.fastjson2.JSONWriter.Feature[0]), 0, 2000));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
/* 186 */     String params = "";
/* 187 */     if (paramsArray != null && paramsArray.length > 0)
/*     */     {
/* 189 */       for (Object o : paramsArray) {
/*     */         
/* 191 */         if (StringUtils.isNotNull(o) && !isFilterObject(o)) {
/*     */           
/*     */           try {
/*     */             
/* 195 */             String jsonObj = JSON.toJSONString(o, (Filter)excludePropertyPreFilter(excludeParamNames), new com.alibaba.fastjson2.JSONWriter.Feature[0]);
/* 196 */             params = params + jsonObj.toString() + " ";
/*     */           }
/* 198 */           catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 204 */     return params.trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
/* 212 */     return (new PropertyPreExcludeFilter()).addExcludes((String[])ArrayUtils.addAll((Object[])EXCLUDE_PROPERTIES, (Object[])excludeParamNames));
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
/*     */   public boolean isFilterObject(Object o) {
/* 224 */     Class<?> clazz = o.getClass();
/* 225 */     if (clazz.isArray())
/*     */     {
/* 227 */       return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
/*     */     }
/* 229 */     if (Collection.class.isAssignableFrom(clazz)) {
/*     */       
/* 231 */       Collection collection = (Collection)o;
/* 232 */       Iterator iterator = collection.iterator(); if (iterator.hasNext()) { Object value = iterator.next();
/*     */         
/* 234 */         return value instanceof MultipartFile; }
/*     */ 
/*     */     
/* 237 */     } else if (Map.class.isAssignableFrom(clazz)) {
/*     */       
/* 239 */       Map map = (Map)o;
/* 240 */       Iterator iterator = map.entrySet().iterator(); if (iterator.hasNext()) { Object value = iterator.next();
/*     */         
/* 242 */         Map.Entry entry = (Map.Entry)value;
/* 243 */         return entry.getValue() instanceof MultipartFile; }
/*     */     
/*     */     } 
/* 246 */     return (o instanceof MultipartFile || o instanceof javax.servlet.http.HttpServletRequest || o instanceof javax.servlet.http.HttpServletResponse || o instanceof org.springframework.validation.BindingResult);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/aspectj/LogAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */