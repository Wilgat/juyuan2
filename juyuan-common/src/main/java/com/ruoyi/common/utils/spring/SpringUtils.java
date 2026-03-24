/*     */ package com.ruoyi.common.utils.spring;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.util.Map;
/*     */ import org.springframework.aop.framework.AopContext;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.NoSuchBeanDefinitionException;
/*     */ import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
/*     */ import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
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
/*     */ @Component
/*     */ public final class SpringUtils
/*     */   implements BeanFactoryPostProcessor, ApplicationContextAware
/*     */ {
/*     */   private static ConfigurableListableBeanFactory beanFactory;
/*     */   private static ApplicationContext applicationContext;
/*     */   
/*     */   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
/*  31 */     SpringUtils.beanFactory = beanFactory;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, Object> getBeansByAnnotation(Class clsName) throws BeansException {
/*  36 */     return beanFactory.getBeansWithAnnotation(clsName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
/*  41 */     SpringUtils.applicationContext = applicationContext;
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
/*     */   public static <T> T getBean(String name) throws BeansException {
/*  55 */     return (T)beanFactory.getBean(name);
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
/*     */   public static <T> T getBean(Class<T> clz) throws BeansException {
/*  68 */     T result = (T)beanFactory.getBean(clz);
/*  69 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsBean(String name) {
/*  80 */     return beanFactory.containsBean(name);
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
/*     */   public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
/*  93 */     return beanFactory.isSingleton(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
/* 104 */     return beanFactory.getType(name);
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
/*     */   public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
/* 117 */     return beanFactory.getAliases(name);
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
/*     */   public static <T> T getAopProxy(T invoker) {
/* 129 */     return (T)AopContext.currentProxy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] getActiveProfiles() {
/* 139 */     return applicationContext.getEnvironment().getActiveProfiles();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getActiveProfile() {
/* 149 */     String[] activeProfiles = getActiveProfiles();
/* 150 */     return StringUtils.isNotEmpty((Object[])activeProfiles) ? activeProfiles[0] : null;
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
/*     */   public static String getRequiredProperty(String key) {
/* 162 */     return applicationContext.getEnvironment().getRequiredProperty(key);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/spring/SpringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */