/*    */ package com.ruoyi.framework.security.handle;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.utils.ServletUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.security.core.AuthenticationException;
/*    */ import org.springframework.security.web.AuthenticationEntryPoint;
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
/*    */ @Component
/*    */ public class AuthenticationEntryPointImpl
/*    */   implements AuthenticationEntryPoint, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8970718410437077606L;
/*    */   
/*    */   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
/* 30 */     int code = 401;
/* 31 */     String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", new Object[] { request.getRequestURI() });
/* 32 */     ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/security/handle/AuthenticationEntryPointImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */