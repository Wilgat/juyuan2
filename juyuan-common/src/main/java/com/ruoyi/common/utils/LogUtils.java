/*    */ package com.ruoyi.common.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogUtils
/*    */ {
/*    */   public static String getBlock(Object msg) {
/* 12 */     if (msg == null)
/*    */     {
/* 14 */       msg = "";
/*    */     }
/* 16 */     return "[" + msg.toString() + "]";
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/LogUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */