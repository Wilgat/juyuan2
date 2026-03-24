/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.LocaleResolver;
/*    */ import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
/*    */ import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/*    */ 
/*    */ @Configuration
/*    */ public class I18nConfig implements WebMvcConfigurer {
/*    */   @Bean
/*    */   public LocaleResolver localeResolver() {
/* 17 */     SessionLocaleResolver slr = new SessionLocaleResolver();
/*    */     
/* 19 */     slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
/* 20 */     return (LocaleResolver)slr;
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public LocaleChangeInterceptor localeChangeInterceptor() {
/* 25 */     LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
/*    */     
/* 27 */     lci.setParamName("lang");
/* 28 */     return lci;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInterceptors(InterceptorRegistry registry) {
/* 33 */     registry.addInterceptor((HandlerInterceptor)localeChangeInterceptor());
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/I18nConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */