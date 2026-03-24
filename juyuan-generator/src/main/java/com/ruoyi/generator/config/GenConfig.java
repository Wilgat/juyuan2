/*    */ package com.ruoyi.generator.config;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*    */ import org.springframework.context.annotation.PropertySource;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @ConfigurationProperties(prefix = "gen")
/*    */ @PropertySource({"classpath:generator.yml"})
/*    */ public class GenConfig
/*    */ {
/*    */   public static String author;
/*    */   public static String packageName;
/*    */   public static boolean autoRemovePre;
/*    */   public static String tablePrefix;
/*    */   
/*    */   public static String getAuthor() {
/* 32 */     return author;
/*    */   }
/*    */ 
/*    */   
/*    */   @Value("${author}")
/*    */   public void setAuthor(String author) {
/* 38 */     GenConfig.author = author;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPackageName() {
/* 43 */     return packageName;
/*    */   }
/*    */ 
/*    */   
/*    */   @Value("${packageName}")
/*    */   public void setPackageName(String packageName) {
/* 49 */     GenConfig.packageName = packageName;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean getAutoRemovePre() {
/* 54 */     return autoRemovePre;
/*    */   }
/*    */ 
/*    */   
/*    */   @Value("${autoRemovePre}")
/*    */   public void setAutoRemovePre(boolean autoRemovePre) {
/* 60 */     GenConfig.autoRemovePre = autoRemovePre;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getTablePrefix() {
/* 65 */     return tablePrefix;
/*    */   }
/*    */ 
/*    */   
/*    */   @Value("${tablePrefix}")
/*    */   public void setTablePrefix(String tablePrefix) {
/* 71 */     GenConfig.tablePrefix = tablePrefix;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/config/GenConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */