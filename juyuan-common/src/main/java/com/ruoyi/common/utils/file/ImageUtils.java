/*    */ package com.ruoyi.common.utils.file;
/*    */ 
/*    */ import com.ruoyi.common.config.RuoYiConfig;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.Arrays;
/*    */ import org.apache.poi.util.IOUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageUtils
/*    */ {
/* 23 */   private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);
/*    */ 
/*    */   
/*    */   public static byte[] getImage(String imagePath) {
/* 27 */     InputStream is = getFile(imagePath);
/*    */     
/*    */     try {
/* 30 */       return IOUtils.toByteArray(is);
/*    */     }
/* 32 */     catch (Exception e) {
/*    */       
/* 34 */       log.error("图片加载异常 {}", e);
/* 35 */       return null;
/*    */     }
/*    */     finally {
/*    */       
/* 39 */       IOUtils.closeQuietly(is);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static InputStream getFile(String imagePath) {
/*    */     try {
/* 47 */       byte[] result = readFile(imagePath);
/* 48 */       result = Arrays.copyOf(result, result.length);
/* 49 */       return new ByteArrayInputStream(result);
/*    */     }
/* 51 */     catch (Exception e) {
/*    */       
/* 53 */       log.error("获取图片异常 {}", e);
/*    */       
/* 55 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static byte[] readFile(String url) {
/* 66 */     InputStream in = null;
/*    */     
/*    */     try {
/* 69 */       if (url.startsWith("http")) {
/*    */ 
/*    */         
/* 72 */         URL urlObj = new URL(url);
/* 73 */         URLConnection urlConnection = urlObj.openConnection();
/* 74 */         urlConnection.setConnectTimeout(30000);
/* 75 */         urlConnection.setReadTimeout(60000);
/* 76 */         urlConnection.setDoInput(true);
/* 77 */         in = urlConnection.getInputStream();
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 82 */         String localPath = RuoYiConfig.getProfile();
/* 83 */         String downloadPath = localPath + StringUtils.substringAfter(url, "/profile");
/* 84 */         in = new FileInputStream(downloadPath);
/*    */       } 
/* 86 */       return IOUtils.toByteArray(in);
/*    */     }
/* 88 */     catch (Exception e) {
/*    */       
/* 90 */       log.error("获取文件路径异常 {}", e);
/* 91 */       return null;
/*    */     }
/*    */     finally {
/*    */       
/* 95 */       IOUtils.closeQuietly(in);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/file/ImageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */