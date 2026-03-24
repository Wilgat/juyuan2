/*    */ package com.ruoyi.common.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UtilException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 8247610319171014183L;
/*    */   
/*    */   public UtilException(Throwable e) {
/* 14 */     super(e.getMessage(), e);
/*    */   }
/*    */ 
/*    */   
/*    */   public UtilException(String message) {
/* 19 */     super(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public UtilException(String message, Throwable throwable) {
/* 24 */     super(message, throwable);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/UtilException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */