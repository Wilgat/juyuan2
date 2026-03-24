/*    */ package com.ruoyi.common.exception;
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
/*    */ 
/*    */ 
/*    */ public class GlobalException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String message;
/*    */   private String detailMessage;
/*    */   
/*    */   public GlobalException() {}
/*    */   
/*    */   public GlobalException(String message) {
/* 33 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDetailMessage() {
/* 38 */     return this.detailMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public GlobalException setDetailMessage(String detailMessage) {
/* 43 */     this.detailMessage = detailMessage;
/* 44 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 50 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public GlobalException setMessage(String message) {
/* 55 */     this.message = message;
/* 56 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/GlobalException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */