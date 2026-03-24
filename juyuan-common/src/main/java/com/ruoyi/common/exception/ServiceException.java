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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ServiceException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer code;
/*    */   private String message;
/*    */   private String detailMessage;
/*    */   
/*    */   public ServiceException() {}
/*    */   
/*    */   public ServiceException(String message) {
/* 38 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public ServiceException(String message, Integer code) {
/* 43 */     this.message = message;
/* 44 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDetailMessage() {
/* 49 */     return this.detailMessage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 55 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getCode() {
/* 60 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public ServiceException setMessage(String message) {
/* 65 */     this.message = message;
/* 66 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public ServiceException setDetailMessage(String detailMessage) {
/* 71 */     this.detailMessage = detailMessage;
/* 72 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/ServiceException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */