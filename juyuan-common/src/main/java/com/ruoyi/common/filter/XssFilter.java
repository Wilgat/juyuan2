/*    */ package com.ruoyi.common.filter;
/*    */ 
/*    */ import com.ruoyi.common.enums.HttpMethod;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XssFilter
/*    */   implements Filter
/*    */ {
/* 27 */   public List<String> excludes = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(FilterConfig filterConfig) throws ServletException {
/* 32 */     String tempExcludes = filterConfig.getInitParameter("excludes");
/* 33 */     if (StringUtils.isNotEmpty(tempExcludes)) {
/*    */       
/* 35 */       String[] url = tempExcludes.split(",");
/* 36 */       for (int i = 0; url != null && i < url.length; i++)
/*    */       {
/* 38 */         this.excludes.add(url[i]);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/* 47 */     HttpServletRequest req = (HttpServletRequest)request;
/* 48 */     HttpServletResponse resp = (HttpServletResponse)response;
/* 49 */     if (handleExcludeURL(req, resp)) {
/*    */       
/* 51 */       chain.doFilter(request, response);
/*    */       return;
/*    */     } 
/* 54 */     XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest)request);
/* 55 */     chain.doFilter((ServletRequest)xssRequest, response);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
/* 60 */     String url = request.getServletPath();
/* 61 */     String method = request.getMethod();
/*    */     
/* 63 */     if (method == null || HttpMethod.GET.matches(method) || HttpMethod.DELETE.matches(method))
/*    */     {
/* 65 */       return true;
/*    */     }
/* 67 */     return StringUtils.matches(url, this.excludes);
/*    */   }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/filter/XssFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */