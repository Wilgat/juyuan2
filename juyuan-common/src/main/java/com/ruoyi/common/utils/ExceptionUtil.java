/*    */ package com.ruoyi.common.utils;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import org.apache.commons.lang3.exception.ExceptionUtils;
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
/*    */ public class ExceptionUtil
/*    */ {
/*    */   public static String getExceptionMessage(Throwable e) {
/* 19 */     StringWriter sw = new StringWriter();
/* 20 */     e.printStackTrace(new PrintWriter(sw, true));
/* 21 */     return sw.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getRootErrorMessage(Exception e) {
/* 26 */     Throwable root = ExceptionUtils.getRootCause(e);
/* 27 */     root = (root == null) ? e : root;
/* 28 */     if (root == null)
/*    */     {
/* 30 */       return "";
/*    */     }
/* 32 */     String msg = root.getMessage();
/* 33 */     if (msg == null)
/*    */     {
/* 35 */       return "null";
/*    */     }
/* 37 */     return StringUtils.defaultString(msg);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/ExceptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */