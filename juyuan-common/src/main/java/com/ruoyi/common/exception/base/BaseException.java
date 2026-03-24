/*    */ package com.ruoyi.common.exception.base;
/*    */ 
/*    */ import com.ruoyi.common.utils.MessageUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
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
/*    */ public class BaseException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String module;
/*    */   private String code;
/*    */   private Object[] args;
/*    */   private String defaultMessage;
/*    */   
/*    */   public BaseException(String module, String code, Object[] args, String defaultMessage) {
/* 37 */     this.module = module;
/* 38 */     this.code = code;
/* 39 */     this.args = args;
/* 40 */     this.defaultMessage = defaultMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseException(String module, String code, Object[] args) {
/* 45 */     this(module, code, args, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseException(String module, String defaultMessage) {
/* 50 */     this(module, null, null, defaultMessage);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseException(String code, Object[] args) {
/* 55 */     this(null, code, args, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaseException(String defaultMessage) {
/* 60 */     this(null, null, null, defaultMessage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 66 */     String message = null;
/* 67 */     if (!StringUtils.isEmpty(this.code))
/*    */     {
/* 69 */       message = MessageUtils.message(this.code, this.args);
/*    */     }
/* 71 */     if (message == null)
/*    */     {
/* 73 */       message = this.defaultMessage;
/*    */     }
/* 75 */     return message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getModule() {
/* 80 */     return this.module;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 85 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object[] getArgs() {
/* 90 */     return this.args;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDefaultMessage() {
/* 95 */     return this.defaultMessage;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/base/BaseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */