/*    */ package com.ruoyi.generator.util;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.velocity.app.Velocity;
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
/*    */ public class VelocityInitializer
/*    */ {
/*    */   public static void initVelocity() {
/* 19 */     Properties p = new Properties();
/*    */ 
/*    */     
/*    */     try {
/* 23 */       p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
/*    */       
/* 25 */       p.setProperty("resource.default_encoding", "UTF-8");
/*    */       
/* 27 */       Velocity.init(p);
/*    */     }
/* 29 */     catch (Exception e) {
/*    */       
/* 31 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/util/VelocityInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */