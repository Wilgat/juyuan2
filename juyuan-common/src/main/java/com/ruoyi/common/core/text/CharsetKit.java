/*    */ package com.ruoyi.common.core.text;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.StandardCharsets;
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
/*    */ public class CharsetKit
/*    */ {
/*    */   public static final String ISO_8859_1 = "ISO-8859-1";
/*    */   public static final String UTF_8 = "UTF-8";
/*    */   public static final String GBK = "GBK";
/* 22 */   public static final Charset CHARSET_ISO_8859_1 = Charset.forName("ISO-8859-1");
/*    */   
/* 24 */   public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
/*    */   
/* 26 */   public static final Charset CHARSET_GBK = Charset.forName("GBK");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Charset charset(String charset) {
/* 36 */     return StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset);
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
/*    */   public static String convert(String source, String srcCharset, String destCharset) {
/* 49 */     return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
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
/*    */   public static String convert(String source, Charset srcCharset, Charset destCharset) {
/* 62 */     if (null == srcCharset)
/*    */     {
/* 64 */       srcCharset = StandardCharsets.ISO_8859_1;
/*    */     }
/*    */     
/* 67 */     if (null == destCharset)
/*    */     {
/* 69 */       destCharset = StandardCharsets.UTF_8;
/*    */     }
/*    */     
/* 72 */     if (StringUtils.isEmpty(source) || srcCharset.equals(destCharset))
/*    */     {
/* 74 */       return source;
/*    */     }
/* 76 */     return new String(source.getBytes(srcCharset), destCharset);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String systemCharset() {
/* 84 */     return Charset.defaultCharset().name();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/text/CharsetKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */