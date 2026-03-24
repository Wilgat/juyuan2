/*    */ package com.ruoyi.common.constant;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScheduleConstants
/*    */ {
/*    */   public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";
/*    */   public static final String TASK_PROPERTIES = "TASK_PROPERTIES";
/*    */   public static final String MISFIRE_DEFAULT = "0";
/*    */   public static final String MISFIRE_IGNORE_MISFIRES = "1";
/*    */   public static final String MISFIRE_FIRE_AND_PROCEED = "2";
/*    */   public static final String MISFIRE_DO_NOTHING = "3";
/*    */   
/*    */   public enum Status
/*    */   {
/* 32 */     NORMAL("0"),
/*    */ 
/*    */ 
/*    */     
/* 36 */     PAUSE("1");
/*    */     
/*    */     private String value;
/*    */ 
/*    */     
/*    */     Status(String value) {
/* 42 */       this.value = value;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getValue() {
/* 47 */       return this.value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/constant/ScheduleConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */