/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import com.ruoyi.common.filter.RepeatableFilter;
/*    */ import com.ruoyi.common.filter.XssFilter;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.DispatcherType;
/*    */ import javax.servlet.Filter;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
/*    */ import org.springframework.boot.web.servlet.FilterRegistrationBean;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class FilterConfig
/*    */ {
/*    */   @Value("${xss.excludes}")
/*    */   private String excludes;
/*    */   @Value("${xss.urlPatterns}")
/*    */   private String urlPatterns;
/*    */   
/*    */   @Bean
/*    */   @ConditionalOnProperty(value = {"xss.enabled"}, havingValue = "true")
/*    */   public FilterRegistrationBean xssFilterRegistration() {
/* 34 */     FilterRegistrationBean registration = new FilterRegistrationBean();
/* 35 */     registration.setDispatcherTypes(DispatcherType.REQUEST, new DispatcherType[0]);
/* 36 */     registration.setFilter((Filter)new XssFilter());
/* 37 */     registration.addUrlPatterns(StringUtils.split(this.urlPatterns, ","));
/* 38 */     registration.setName("xssFilter");
/* 39 */     registration.setOrder(-2147483648);
/* 40 */     Map<String, String> initParameters = new HashMap<>();
/* 41 */     initParameters.put("excludes", this.excludes);
/* 42 */     registration.setInitParameters(initParameters);
/* 43 */     return registration;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Bean
/*    */   public FilterRegistrationBean someFilterRegistration() {
/* 50 */     FilterRegistrationBean registration = new FilterRegistrationBean();
/* 51 */     registration.setFilter((Filter)new RepeatableFilter());
/* 52 */     registration.addUrlPatterns(new String[] { "/*" });
/* 53 */     registration.setName("repeatableFilter");
/* 54 */     registration.setOrder(2147483647);
/* 55 */     return registration;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/FilterConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */