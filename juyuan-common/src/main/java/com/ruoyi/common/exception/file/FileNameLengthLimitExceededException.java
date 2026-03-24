/*    */ package com.ruoyi.common.exception.file;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileNameLengthLimitExceededException
/*    */   extends FileException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public FileNameLengthLimitExceededException(int defaultFileNameLength) {
/* 14 */     super("upload.filename.exceed.length", new Object[] { Integer.valueOf(defaultFileNameLength) });
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/file/FileNameLengthLimitExceededException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */