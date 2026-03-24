/*    */ package com.ruoyi.common.exception.file;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileSizeLimitExceededException
/*    */   extends FileException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public FileSizeLimitExceededException(long defaultMaxSize) {
/* 14 */     super("upload.exceed.maxSize", new Object[] { Long.valueOf(defaultMaxSize) });
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/file/FileSizeLimitExceededException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */