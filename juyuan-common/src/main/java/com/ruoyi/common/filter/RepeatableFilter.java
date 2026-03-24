/*    */ package com.ruoyi.common.filter;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
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
/*    */ public class RepeatableFilter
/*    */   implements Filter
/*    */ {
/*    */   public void init(FilterConfig filterConfig) throws ServletException {}
/*    */   
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
                throws IOException, ServletException 
            {
                RepeatedlyRequestWrapper repeatedlyRequestWrapper = null;  // ← initialize here

                if (request instanceof HttpServletRequest && 
                    StringUtils.startsWithIgnoreCase(request.getContentType(), "application/json")) 
                {
                    repeatedlyRequestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest)request, response);
                }

                if (repeatedlyRequestWrapper == null) {  // ← simplified null check
                    chain.doFilter(request, response);
                } else {
                    chain.doFilter(repeatedlyRequestWrapper, response);  // ← no cast needed
                }
            }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/filter/RepeatableFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */