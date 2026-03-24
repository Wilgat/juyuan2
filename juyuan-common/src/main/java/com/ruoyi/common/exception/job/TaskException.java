/*    */ package com.ruoyi.common.exception.job;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Code code;
/*    */   
/*    */   public TaskException(String msg, Code code) {
/* 16 */     this(msg, code, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskException(String msg, Code code, Exception nestedEx) {
/* 21 */     super(msg, nestedEx);
/* 22 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public Code getCode() {
/* 27 */     return this.code;
/*    */   }
/*    */   
/*    */   public enum Code
/*    */   {
/* 32 */     TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/job/TaskException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */