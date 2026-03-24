/*    */ package com.ruoyi.common.core.text;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StrFormatter
/*    */ {
/*    */   public static final String EMPTY_JSON = "{}";
/*    */   public static final char C_BACKSLASH = '\\';
/*    */   public static final char C_DELIM_START = '{';
/*    */   public static final char C_DELIM_END = '}';
/*    */   
/*    */   public static String format(String strPattern, Object... argArray) {
/* 32 */     if (StringUtils.isEmpty(strPattern) || StringUtils.isEmpty(argArray))
/*    */     {
/* 34 */       return strPattern;
/*    */     }
/* 36 */     int strPatternLength = strPattern.length();
/*    */ 
/*    */     
/* 39 */     StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
/*    */     
/* 41 */     int handledPosition = 0;
/*    */     
/* 43 */     for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
/*    */       
/* 45 */       int delimIndex = strPattern.indexOf("{}", handledPosition);
/* 46 */       if (delimIndex == -1) {
/*    */         
/* 48 */         if (handledPosition == 0)
/*    */         {
/* 50 */           return strPattern;
/*    */         }
/*    */ 
/*    */         
/* 54 */         sbuf.append(strPattern, handledPosition, strPatternLength);
/* 55 */         return sbuf.toString();
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 60 */       if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == '\\') {
/*    */         
/* 62 */         if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == '\\')
/*    */         {
/*    */           
/* 65 */           sbuf.append(strPattern, handledPosition, delimIndex - 1);
/* 66 */           sbuf.append(Convert.utf8Str(argArray[argIndex]));
/* 67 */           handledPosition = delimIndex + 2;
/*    */         
/*    */         }
/*    */         else
/*    */         {
/* 72 */           argIndex--;
/* 73 */           sbuf.append(strPattern, handledPosition, delimIndex - 1);
/* 74 */           sbuf.append('{');
/* 75 */           handledPosition = delimIndex + 1;
/*    */         }
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 81 */         sbuf.append(strPattern, handledPosition, delimIndex);
/* 82 */         sbuf.append(Convert.utf8Str(argArray[argIndex]));
/* 83 */         handledPosition = delimIndex + 2;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 88 */     sbuf.append(strPattern, handledPosition, strPattern.length());
/*    */     
/* 90 */     return sbuf.toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/text/StrFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */