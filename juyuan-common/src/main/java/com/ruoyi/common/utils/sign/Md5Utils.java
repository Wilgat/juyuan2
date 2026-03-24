/*    */ package com.ruoyi.common.utils.sign;
/*    */ 
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.security.MessageDigest;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Md5Utils
/*    */ {
/* 15 */   private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static byte[] md5(String s) {
/*    */     try {
/* 22 */       MessageDigest algorithm = MessageDigest.getInstance("MD5");
/* 23 */       algorithm.reset();
/* 24 */       algorithm.update(s.getBytes("UTF-8"));
/* 25 */       byte[] messageDigest = algorithm.digest();
/* 26 */       return messageDigest;
/*    */     }
/* 28 */     catch (Exception e) {
/*    */       
/* 30 */       log.error("MD5 Error...", e);
/*    */       
/* 32 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static final String toHex(byte[] hash) {
/* 37 */     if (hash == null)
/*    */     {
/* 39 */       return null;
/*    */     }
/* 41 */     StringBuffer buf = new StringBuffer(hash.length * 2);
/*    */ 
/*    */     
/* 44 */     for (int i = 0; i < hash.length; i++) {
/*    */       
/* 46 */       if ((hash[i] & 0xFF) < 16)
/*    */       {
/* 48 */         buf.append("0");
/*    */       }
/* 50 */       buf.append(Long.toString((hash[i] & 0xFF), 16));
/*    */     } 
/* 52 */     return buf.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String hash(String s) {
/*    */     try {
/* 59 */       return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
/*    */     }
/* 61 */     catch (Exception e) {
/*    */       
/* 63 */       log.error("not supported charset...{}", e);
/* 64 */       return s;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/sign/Md5Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */