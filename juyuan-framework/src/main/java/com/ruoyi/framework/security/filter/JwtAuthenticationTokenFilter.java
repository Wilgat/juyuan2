/*    */ package com.ruoyi.framework.security.filter;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.utils.SecurityUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.framework.web.service.TokenService;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*    */ import org.springframework.security.core.Authentication;
/*    */ import org.springframework.security.core.context.SecurityContextHolder;
/*    */ import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.filter.OncePerRequestFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class JwtAuthenticationTokenFilter
/*    */   extends OncePerRequestFilter
/*    */ {
/*    */   @Autowired
/*    */   private TokenService tokenService;
/*    */   
/*    */   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
/* 34 */     LoginUser loginUser = this.tokenService.getLoginUser(request);
/* 35 */     if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
/*    */       
/* 37 */       this.tokenService.verifyToken(loginUser);
/* 38 */       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
/* 39 */       authenticationToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
/* 40 */       SecurityContextHolder.getContext().setAuthentication((Authentication)authenticationToken);
/*    */     } 
/* 42 */     chain.doFilter((ServletRequest)request, (ServletResponse)response);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/security/filter/JwtAuthenticationTokenFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */