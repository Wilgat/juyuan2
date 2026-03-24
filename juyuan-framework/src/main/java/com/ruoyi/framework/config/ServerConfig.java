/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import com.ruoyi.common.utils.ServletUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ @Component
/*    */ public class ServerConfig
/*    */ {
/*    */   public String getUrl() {
/* 22 */     HttpServletRequest request = ServletUtils.getRequest();
/* 23 */     return getDomain(request);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getDomain(HttpServletRequest request) {
/* 28 */     StringBuffer url = request.getRequestURL();
/* 29 */     String contextPath = request.getServletContext().getContextPath();
/* 30 */     return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/ServerConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */