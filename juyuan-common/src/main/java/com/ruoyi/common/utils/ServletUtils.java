/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.web.context.request.RequestAttributes;
/*     */ import org.springframework.web.context.request.RequestContextHolder;
/*     */ import org.springframework.web.context.request.ServletRequestAttributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServletUtils
/*     */ {
/*     */   public static String getParameter(String name) {
/*  32 */     return getRequest().getParameter(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getParameter(String name, String defaultValue) {
/*  40 */     return Convert.toStr(getRequest().getParameter(name), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer getParameterToInt(String name) {
/*  48 */     return Convert.toInt(getRequest().getParameter(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer getParameterToInt(String name, Integer defaultValue) {
/*  56 */     return Convert.toInt(getRequest().getParameter(name), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Boolean getParameterToBool(String name) {
/*  64 */     return Convert.toBool(getRequest().getParameter(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Boolean getParameterToBool(String name, Boolean defaultValue) {
/*  72 */     return Convert.toBool(getRequest().getParameter(name), defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, String[]> getParams(ServletRequest request) {
/*  83 */     Map<String, String[]> map = request.getParameterMap();
/*  84 */     return (Map)Collections.unmodifiableMap((Map)map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, String> getParamMap(ServletRequest request) {
/*  95 */     Map<String, String> params = new HashMap<>();
/*  96 */     for (Map.Entry<String, String[]> entry : getParams(request).entrySet())
/*     */     {
/*  98 */       params.put(entry.getKey(), StringUtils.join((Object[])entry.getValue(), ","));
/*     */     }
/* 100 */     return params;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpServletRequest getRequest() {
/* 108 */     return getRequestAttributes().getRequest();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpServletResponse getResponse() {
/* 116 */     return getRequestAttributes().getResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpSession getSession() {
/* 124 */     return getRequest().getSession();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ServletRequestAttributes getRequestAttributes() {
/* 129 */     RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
/* 130 */     return (ServletRequestAttributes)attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderString(HttpServletResponse response, String string) {
/*     */     try {
/* 143 */       response.setStatus(200);
/* 144 */       response.setContentType("application/json");
/* 145 */       response.setCharacterEncoding("utf-8");
/* 146 */       response.getWriter().print(string);
/*     */     }
/* 148 */     catch (IOException e) {
/*     */       
/* 150 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAjaxRequest(HttpServletRequest request) {
/* 161 */     String accept = request.getHeader("accept");
/* 162 */     if (accept != null && accept.contains("application/json"))
/*     */     {
/* 164 */       return true;
/*     */     }
/*     */     
/* 167 */     String xRequestedWith = request.getHeader("X-Requested-With");
/* 168 */     if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))
/*     */     {
/* 170 */       return true;
/*     */     }
/*     */     
/* 173 */     String uri = request.getRequestURI();
/* 174 */     if (StringUtils.inStringIgnoreCase(uri, new String[] { ".json", ".xml" }))
/*     */     {
/* 176 */       return true;
/*     */     }
/*     */     
/* 179 */     String ajax = request.getParameter("__ajax");
/* 180 */     return StringUtils.inStringIgnoreCase(ajax, new String[] { "json", "xml" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String urlEncode(String str) {
/*     */     try {
/* 193 */       return URLEncoder.encode(str, "UTF-8");
/*     */     }
/* 195 */     catch (UnsupportedEncodingException e) {
/*     */       
/* 197 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String urlDecode(String str) {
/*     */     try {
/* 211 */       return URLDecoder.decode(str, "UTF-8");
/*     */     }
/* 213 */     catch (UnsupportedEncodingException e) {
/*     */       
/* 215 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/ServletUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */