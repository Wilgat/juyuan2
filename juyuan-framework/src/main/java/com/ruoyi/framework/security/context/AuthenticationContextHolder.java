/*    */ package com.ruoyi.framework.security.context;
/*    */ 
/*    */ import org.springframework.security.core.Authentication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthenticationContextHolder
/*    */ {
/* 12 */   private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();
/*    */ 
/*    */   
/*    */   public static Authentication getContext() {
/* 16 */     return contextHolder.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setContext(Authentication context) {
/* 21 */     contextHolder.set(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void clearContext() {
/* 26 */     contextHolder.remove();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/security/context/AuthenticationContextHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */