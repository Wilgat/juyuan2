/*    */ package com.ruoyi.quartz.util;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.util.Date;
/*    */ import org.quartz.CronExpression;
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
/*    */ public class CronUtils
/*    */ {
/*    */   public static boolean isValid(String cronExpression) {
/* 23 */     return CronExpression.isValidExpression(cronExpression);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getInvalidMessage(String cronExpression) {
/*    */     try {
/* 36 */       new CronExpression(cronExpression);
/* 37 */       return null;
/*    */     }
/* 39 */     catch (ParseException pe) {
/*    */       
/* 41 */       return pe.getMessage();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Date getNextExecution(String cronExpression) {
/*    */     try {
/* 55 */       CronExpression cron = new CronExpression(cronExpression);
/* 56 */       return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
/*    */     }
/* 58 */     catch (ParseException e) {
/*    */       
/* 60 */       throw new IllegalArgumentException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/util/CronUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */