/*    */ package com.ruoyi.common.exception.user;
/*    */ 
/*    */ import com.ruoyi.common.exception.base.BaseException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserException
/*    */   extends BaseException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public UserException(String code, Object[] args) {
/* 16 */     super("user", code, args, null);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/user/UserException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */