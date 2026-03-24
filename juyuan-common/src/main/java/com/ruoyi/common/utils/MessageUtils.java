/*    */ package com.ruoyi.common.utils;
/*    */ 
/*    */ import com.ruoyi.common.utils.spring.SpringUtils;
/*    */ import org.springframework.context.MessageSource;
/*    */ import org.springframework.context.i18n.LocaleContextHolder;
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
/*    */ public class MessageUtils
/*    */ {
/*    */   public static String message(String code, Object... args) {
/* 23 */     MessageSource messageSource = (MessageSource)SpringUtils.getBean(MessageSource.class);
/* 24 */     return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/MessageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */