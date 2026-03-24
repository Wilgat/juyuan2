/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import java.util.TimeZone;
/*    */ import org.mybatis.spring.annotation.MapperScan;
/*    */ import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.context.annotation.EnableAspectJAutoProxy;
/*    */ import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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
/*    */ @Configuration
/*    */ @EnableAspectJAutoProxy(exposeProxy = true)
/*    */ @MapperScan({"com.ruoyi.**.mapper"})
/*    */ public class ApplicationConfig
/*    */ {
/*    */   @Bean
/*    */   public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
/* 28 */     return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/ApplicationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */