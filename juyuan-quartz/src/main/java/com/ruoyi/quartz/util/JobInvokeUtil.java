/*     */ package com.ruoyi.quartz.util;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.quartz.domain.SysJob;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class JobInvokeUtil
/*     */ {
/*     */   public static void invokeMethod(SysJob sysJob) throws Exception {
/*  25 */     String invokeTarget = sysJob.getInvokeTarget();
/*  26 */     String beanName = getBeanName(invokeTarget);
/*  27 */     String methodName = getMethodName(invokeTarget);
/*  28 */     List<Object[]> methodParams = getMethodParams(invokeTarget);
/*     */     
/*  30 */     if (!isValidClassName(beanName)) {
/*     */       
/*  32 */       Object bean = SpringUtils.getBean(beanName);
/*  33 */       invokeMethod(bean, methodName, methodParams);
/*     */     }
/*     */     else {
/*     */       
/*  37 */       Object bean = Class.forName(beanName).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*  38 */       invokeMethod(bean, methodName, methodParams);
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
/*     */   
/*     */   private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/*  53 */     if (StringUtils.isNotNull(methodParams) && methodParams.size() > 0) {
/*     */       
/*  55 */       Method method = bean.getClass().getMethod(methodName, getMethodParamsType(methodParams));
/*  56 */       method.invoke(bean, getMethodParamsValue(methodParams));
/*     */     }
/*     */     else {
/*     */       
/*  60 */       Method method = bean.getClass().getMethod(methodName, new Class[0]);
/*  61 */       method.invoke(bean, new Object[0]);
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
/*     */   public static boolean isValidClassName(String invokeTarget) {
/*  73 */     return (StringUtils.countMatches(invokeTarget, ".") > 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBeanName(String invokeTarget) {
/*  84 */     String beanName = StringUtils.substringBefore(invokeTarget, "(");
/*  85 */     return StringUtils.substringBeforeLast(beanName, ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMethodName(String invokeTarget) {
/*  96 */     String methodName = StringUtils.substringBefore(invokeTarget, "(");
/*  97 */     return StringUtils.substringAfterLast(methodName, ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Object[]> getMethodParams(String invokeTarget) {
/* 108 */     String methodStr = StringUtils.substringBetween(invokeTarget, "(", ")");
/* 109 */     if (StringUtils.isEmpty(methodStr))
/*     */     {
/* 111 */       return null;
/*     */     }
/* 113 */     String[] methodParams = methodStr.split(",(?=([^\"']*[\"'][^\"']*[\"'])*[^\"']*$)");
/* 114 */     List<Object[]> classs = new LinkedList();
/* 115 */     for (int i = 0; i < methodParams.length; i++) {
/*     */       
/* 117 */       String str = StringUtils.trimToEmpty(methodParams[i]);
/*     */       
/* 119 */       if (StringUtils.startsWithAny(str, new CharSequence[] { "'", "\"" })) {
/*     */         
/* 121 */         classs.add(new Object[] { StringUtils.substring(str, 1, str.length() - 1), String.class });
/*     */       
/*     */       }
/* 124 */       else if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)) {
/*     */         
/* 126 */         classs.add(new Object[] { Boolean.valueOf(str), Boolean.class });
/*     */       
/*     */       }
/* 129 */       else if (StringUtils.endsWith(str, "L")) {
/*     */         
/* 131 */         classs.add(new Object[] { Long.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Long.class });
/*     */       
/*     */       }
/* 134 */       else if (StringUtils.endsWith(str, "D")) {
/*     */         
/* 136 */         classs.add(new Object[] { Double.valueOf(StringUtils.substring(str, 0, str.length() - 1)), Double.class });
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 141 */         classs.add(new Object[] { Integer.valueOf(str), Integer.class });
/*     */       } 
/*     */     } 
/* 144 */     return classs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
/* 155 */     Class<?>[] classs = new Class[methodParams.size()];
/* 156 */     int index = 0;
/* 157 */     for (Object[] os : methodParams) {
/*     */       
/* 159 */       classs[index] = (Class)os[1];
/* 160 */       index++;
/*     */     } 
/* 162 */     return classs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object[] getMethodParamsValue(List<Object[]> methodParams) {
/* 173 */     Object[] classs = new Object[methodParams.size()];
/* 174 */     int index = 0;
/* 175 */     for (Object[] os : methodParams) {
/*     */       
/* 177 */       classs[index] = os[0];
/* 178 */       index++;
/*     */     } 
/* 180 */     return classs;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/util/JobInvokeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */