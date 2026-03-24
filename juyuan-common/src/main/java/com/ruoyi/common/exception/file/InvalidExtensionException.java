/*    */ package com.ruoyi.common.exception.file;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvalidExtensionException
/*    */   extends FileUploadException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String[] allowedExtension;
/*    */   private String extension;
/*    */   private String filename;
/*    */   
/*    */   public InvalidExtensionException(String[] allowedExtension, String extension, String filename) {
/* 20 */     super("文件[" + filename + "]后缀[" + extension + "]不正确，请上传" + Arrays.toString((Object[])allowedExtension) + "格式");
/* 21 */     this.allowedExtension = allowedExtension;
/* 22 */     this.extension = extension;
/* 23 */     this.filename = filename;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAllowedExtension() {
/* 28 */     return this.allowedExtension;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getExtension() {
/* 33 */     return this.extension;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFilename() {
/* 38 */     return this.filename;
/*    */   }
/*    */   
/*    */   public static class InvalidImageExtensionException
/*    */     extends InvalidExtensionException
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public InvalidImageExtensionException(String[] allowedExtension, String extension, String filename) {
/* 47 */       super(allowedExtension, extension, filename);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class InvalidFlashExtensionException
/*    */     extends InvalidExtensionException
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public InvalidFlashExtensionException(String[] allowedExtension, String extension, String filename) {
/* 57 */       super(allowedExtension, extension, filename);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class InvalidMediaExtensionException
/*    */     extends InvalidExtensionException
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public InvalidMediaExtensionException(String[] allowedExtension, String extension, String filename) {
/* 67 */       super(allowedExtension, extension, filename);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class InvalidVideoExtensionException
/*    */     extends InvalidExtensionException
/*    */   {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     public InvalidVideoExtensionException(String[] allowedExtension, String extension, String filename) {
/* 77 */       super(allowedExtension, extension, filename);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/file/InvalidExtensionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */