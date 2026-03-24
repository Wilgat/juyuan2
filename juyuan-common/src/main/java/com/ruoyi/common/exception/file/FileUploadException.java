/*    */ package com.ruoyi.common.exception.file;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileUploadException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private final Throwable cause;
/*    */   
/*    */   public FileUploadException() {
/* 20 */     this(null, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public FileUploadException(String msg) {
/* 25 */     this(msg, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public FileUploadException(String msg, Throwable cause) {
/* 30 */     super(msg);
/* 31 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void printStackTrace(PrintStream stream) {
/* 37 */     super.printStackTrace(stream);
/* 38 */     if (this.cause != null) {
/*    */       
/* 40 */       stream.println("Caused by:");
/* 41 */       this.cause.printStackTrace(stream);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void printStackTrace(PrintWriter writer) {
/* 48 */     super.printStackTrace(writer);
/* 49 */     if (this.cause != null) {
/*    */       
/* 51 */       writer.println("Caused by:");
/* 52 */       this.cause.printStackTrace(writer);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Throwable getCause() {
/* 59 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/exception/file/FileUploadException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */