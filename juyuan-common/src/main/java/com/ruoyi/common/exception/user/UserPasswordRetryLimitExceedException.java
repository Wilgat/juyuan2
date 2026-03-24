/*    */ package com.ruoyi.common.exception.user;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserPasswordRetryLimitExceedException
/*    */   extends UserException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public UserPasswordRetryLimitExceedException(int retryLimitCount, int lockTime) {
/* 14 */     super("user.password.retry.limit.exceed", new Object[] { Integer.valueOf(retryLimitCount), Integer.valueOf(lockTime) });
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/user/UserPasswordRetryLimitExceedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */