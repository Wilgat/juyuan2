/*    */ package com.ruoyi.framework.aspectj;
/*    */ 
/*    */ import com.ruoyi.common.annotation.DataSource;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
/*    */ import java.util.Objects;
/*    */ import org.aspectj.lang.ProceedingJoinPoint;
/*    */ import org.aspectj.lang.annotation.Around;
/*    */ import org.aspectj.lang.annotation.Aspect;
/*    */ import org.aspectj.lang.annotation.Pointcut;
/*    */ import org.aspectj.lang.reflect.MethodSignature;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.core.annotation.AnnotationUtils;
/*    */ import org.springframework.core.annotation.Order;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Aspect
/*    */ @Order(1)
/*    */ @Component
/*    */ public class DataSourceAspect
/*    */ {
/* 28 */   protected Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Pointcut("@annotation(com.ruoyi.common.annotation.DataSource)|| @within(com.ruoyi.common.annotation.DataSource)")
/*    */   public void dsPointCut() {}
/*    */ 
/*    */ 
/*    */   
/*    */   @Around("dsPointCut()")
/*    */   public Object around(ProceedingJoinPoint point) throws Throwable {
/* 40 */     DataSource dataSource = getDataSource(point);
/*    */     
/* 42 */     if (StringUtils.isNotNull(dataSource))
/*    */     {
/* 44 */       DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 49 */       return point.proceed();
/*    */     
/*    */     }
/*    */     finally {
/*    */       
/* 54 */       DynamicDataSourceContextHolder.clearDataSourceType();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DataSource getDataSource(ProceedingJoinPoint point) {
/* 63 */     MethodSignature signature = (MethodSignature)point.getSignature();
/* 64 */     DataSource dataSource = (DataSource)AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
/* 65 */     if (Objects.nonNull(dataSource))
/*    */     {
/* 67 */       return dataSource;
/*    */     }
/*    */     
/* 70 */     return (DataSource)AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/aspectj/DataSourceAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */