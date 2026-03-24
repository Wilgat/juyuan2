/*    */ package com.ruoyi;
/*    */ 
/*    */ import org.springframework.boot.builder.SpringApplicationBuilder;
/*    */ import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuoYiServletInitializer
/*    */   extends SpringBootServletInitializer
/*    */ {
/*    */   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
/* 16 */     return application.sources(new Class[] { RuoYiApplication.class });
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/RuoYiServletInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */