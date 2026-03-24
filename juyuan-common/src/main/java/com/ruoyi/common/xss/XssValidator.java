/*    */ package com.ruoyi.common.xss;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.validation.ConstraintValidator;
/*    */ import javax.validation.ConstraintValidatorContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XssValidator
/*    */   implements ConstraintValidator<Xss, String>
/*    */ {
/*    */   private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";
/*    */   
/*    */   public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
/* 21 */     if (StringUtils.isBlank(value))
/*    */     {
/* 23 */       return true;
/*    */     }
/* 25 */     return !containsHtml(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean containsHtml(String value) {
/* 30 */     Pattern pattern = Pattern.compile("<(\\S*?)[^>]*>.*?|<.*? />");
/* 31 */     Matcher matcher = pattern.matcher(value);
/* 32 */     return matcher.matches();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/xss/XssValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */