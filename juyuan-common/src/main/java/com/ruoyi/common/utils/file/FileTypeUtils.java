/*    */ package com.ruoyi.common.utils.file;
/*    */ 
/*    */ import java.io.File;
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
/*    */ public class FileTypeUtils
/*    */ {
/*    */   public static String getFileType(File file) {
/* 23 */     if (null == file)
/*    */     {
/* 25 */       return "";
/*    */     }
/* 27 */     return getFileType(file.getName());
/*    */   }
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
/*    */   public static String getFileType(String fileName) {
/* 40 */     int separatorIndex = fileName.lastIndexOf(".");
/* 41 */     if (separatorIndex < 0)
/*    */     {
/* 43 */       return "";
/*    */     }
/* 45 */     return fileName.substring(separatorIndex + 1).toLowerCase();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getFileExtendName(byte[] photoByte) {
/* 56 */     String strFileExtendName = "JPG";
/* 57 */     if (photoByte[0] == 71 && photoByte[1] == 73 && photoByte[2] == 70 && photoByte[3] == 56 && (photoByte[4] == 55 || photoByte[4] == 57) && photoByte[5] == 97) {
/*    */ 
/*    */       
/* 60 */       strFileExtendName = "GIF";
/*    */     }
/* 62 */     else if (photoByte[6] == 74 && photoByte[7] == 70 && photoByte[8] == 73 && photoByte[9] == 70) {
/*    */       
/* 64 */       strFileExtendName = "JPG";
/*    */     }
/* 66 */     else if (photoByte[0] == 66 && photoByte[1] == 77) {
/*    */       
/* 68 */       strFileExtendName = "BMP";
/*    */     }
/* 70 */     else if (photoByte[1] == 80 && photoByte[2] == 78 && photoByte[3] == 71) {
/*    */       
/* 72 */       strFileExtendName = "PNG";
/*    */     } 
/* 74 */     return strFileExtendName;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/file/FileTypeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */