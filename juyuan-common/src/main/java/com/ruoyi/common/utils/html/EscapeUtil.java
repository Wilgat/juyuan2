/*     */ package com.ruoyi.common.utils.html;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EscapeUtil
/*     */ {
/*     */   public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";
/*  14 */   private static final char[][] TEXT = new char[64][];
/*     */ 
/*     */   
/*     */   static {
/*  18 */     for (int i = 0; i < 64; i++) {
/*     */       
/*  20 */       (new char[1])[0] = (char)i; TEXT[i] = new char[1];
/*     */     } 
/*     */ 
/*     */     
/*  24 */     TEXT[39] = "&#039;".toCharArray();
/*  25 */     TEXT[34] = "&#34;".toCharArray();
/*  26 */     TEXT[38] = "&#38;".toCharArray();
/*  27 */     TEXT[60] = "&#60;".toCharArray();
/*  28 */     TEXT[62] = "&#62;".toCharArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String escape(String text) {
/*  39 */     return encode(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String unescape(String content) {
/*  50 */     return decode(content);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String clean(String content) {
/*  61 */     return (new HTMLFilter()).filter(content);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String encode(String text) {
/*  72 */     if (StringUtils.isEmpty(text))
/*     */     {
/*  74 */       return "";
/*     */     }
/*     */     
/*  77 */     StringBuilder tmp = new StringBuilder(text.length() * 6);
/*     */     
/*  79 */     for (int i = 0; i < text.length(); i++) {
/*     */       
/*  81 */       char c = text.charAt(i);
/*  82 */       if (c < 'Ā') {
/*     */         
/*  84 */         tmp.append("%");
/*  85 */         if (c < '\020')
/*     */         {
/*  87 */           tmp.append("0");
/*     */         }
/*  89 */         tmp.append(Integer.toString(c, 16));
/*     */       }
/*     */       else {
/*     */         
/*  93 */         tmp.append("%u");
/*  94 */         if (c <= '࿿')
/*     */         {
/*     */           
/*  97 */           tmp.append("0");
/*     */         }
/*  99 */         tmp.append(Integer.toString(c, 16));
/*     */       } 
/*     */     } 
/* 102 */     return tmp.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decode(String content) {
/* 113 */     if (StringUtils.isEmpty(content))
/*     */     {
/* 115 */       return content;
/*     */     }
/*     */     
/* 118 */     StringBuilder tmp = new StringBuilder(content.length());
/* 119 */     int lastPos = 0, pos = 0;
/*     */     
/* 121 */     while (lastPos < content.length()) {
/*     */       
/* 123 */       pos = content.indexOf("%", lastPos);
/* 124 */       if (pos == lastPos) {
/*     */         
/* 126 */         if (content.charAt(pos + 1) == 'u') {
/*     */           
/* 128 */           char c = (char)Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
/* 129 */           tmp.append(c);
/* 130 */           lastPos = pos + 6;
/*     */           
/*     */           continue;
/*     */         } 
/* 134 */         char ch = (char)Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
/* 135 */         tmp.append(ch);
/* 136 */         lastPos = pos + 3;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 141 */       if (pos == -1) {
/*     */         
/* 143 */         tmp.append(content.substring(lastPos));
/* 144 */         lastPos = content.length();
/*     */         
/*     */         continue;
/*     */       } 
/* 148 */       tmp.append(content.substring(lastPos, pos));
/* 149 */       lastPos = pos;
/*     */     } 
/*     */ 
/*     */     
/* 153 */     return tmp.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 158 */     String html = "<script>alert(1);</script>";
/* 159 */     String escape = escape(html);
/*     */ 
/*     */ 
/*     */     
/* 163 */     System.out.println("clean: " + clean(html));
/* 164 */     System.out.println("escape: " + escape);
/* 165 */     System.out.println("unescape: " + unescape(escape));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/html/EscapeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */