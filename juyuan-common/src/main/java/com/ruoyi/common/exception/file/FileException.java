/*    */ package com.ruoyi.common.exception.file;
/*    */ 
/*    */ import com.ruoyi.common.exception.base.BaseException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileException
/*    */   extends BaseException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public FileException(String code, Object[] args) {
/* 16 */     super("file", code, args, null);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/file/FileException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */