/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import com.ruoyi.common.config.RuoYiConfig;
/*    */ import com.ruoyi.framework.interceptor.RepeatSubmitInterceptor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.http.CacheControl;
/*    */ import org.springframework.web.cors.CorsConfiguration;
/*    */ import org.springframework.web.cors.CorsConfigurationSource;
/*    */ import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
/*    */ import org.springframework.web.filter.CorsFilter;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class ResourcesConfig
/*    */   implements WebMvcConfigurer
/*    */ {
/*    */   @Autowired
/*    */   private RepeatSubmitInterceptor repeatSubmitInterceptor;
/*    */   
/*    */   public void addResourceHandlers(ResourceHandlerRegistry registry) {
/* 33 */     registry.addResourceHandler(new String[] { "/profile/**"
/* 34 */         }).addResourceLocations(new String[] { "file:" + RuoYiConfig.getProfile() + "/" });
/*    */ 
/*    */     
/* 37 */     registry.addResourceHandler(new String[] { "/swagger-ui/**"
/* 38 */         }).addResourceLocations(new String[] { "classpath:/META-INF/resources/webjars/springfox-swagger-ui/"
/* 39 */         }).setCacheControl(CacheControl.maxAge(5L, TimeUnit.HOURS).cachePublic());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInterceptors(InterceptorRegistry registry) {
/* 48 */     registry.addInterceptor((HandlerInterceptor)this.repeatSubmitInterceptor).addPathPatterns(new String[] { "/**" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Bean
/*    */   public CorsFilter corsFilter() {
/* 57 */     CorsConfiguration config = new CorsConfiguration();
/* 58 */     config.setAllowCredentials(Boolean.valueOf(true));
/*    */     
/* 60 */     config.addAllowedOriginPattern("*");
/*    */     
/* 62 */     config.addAllowedHeader("*");
/*    */     
/* 64 */     config.addAllowedMethod("*");
/*    */     
/* 66 */     config.setMaxAge(Long.valueOf(1800L));
/*    */     
/* 68 */     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
/* 69 */     source.registerCorsConfiguration("/**", config);
/*    */     
/* 71 */     return new CorsFilter((CorsConfigurationSource)source);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/ResourcesConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */