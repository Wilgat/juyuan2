/*     */ package com.ruoyi.common.filter;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.html.EscapeUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.servlet.ReadListener;
/*     */ import javax.servlet.ServletInputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletRequestWrapper;
/*     */ import org.apache.commons.io.IOUtils;
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
/*     */ public class XssHttpServletRequestWrapper
/*     */   extends HttpServletRequestWrapper
/*     */ {
/*     */   public XssHttpServletRequestWrapper(HttpServletRequest request) {
/*  27 */     super(request);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getParameterValues(String name) {
/*  33 */     String[] values = super.getParameterValues(name);
/*  34 */     if (values != null) {
/*     */       
/*  36 */       int length = values.length;
/*  37 */       String[] escapesValues = new String[length];
/*  38 */       for (int i = 0; i < length; i++)
/*     */       {
/*     */         
/*  41 */         escapesValues[i] = EscapeUtil.clean(values[i]).trim();
/*     */       }
/*  43 */       return escapesValues;
/*     */     } 
/*  45 */     return super.getParameterValues(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServletInputStream getInputStream() throws IOException {
/*  52 */     if (!isJsonRequest())
/*     */     {
/*  54 */       return super.getInputStream();
/*     */     }
/*     */ 
/*     */     
/*  58 */     String json = IOUtils.toString((InputStream)super.getInputStream(), "utf-8");
/*  59 */     if (StringUtils.isEmpty(json))
/*     */     {
/*  61 */       return super.getInputStream();
/*     */     }
/*     */ 
/*     */     
/*  65 */     json = EscapeUtil.clean(json).trim();
/*  66 */     final byte[] jsonBytes = json.getBytes("utf-8");
/*  67 */     final ByteArrayInputStream bis = new ByteArrayInputStream(jsonBytes);
/*  68 */     return new ServletInputStream()
/*     */       {
/*     */         
/*     */         public boolean isFinished()
/*     */         {
/*  73 */           return true;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public boolean isReady() {
/*  79 */           return true;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int available() throws IOException {
/*  85 */           return jsonBytes.length;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void setReadListener(ReadListener readListener) {}
/*     */ 
/*     */ 
/*     */         
/*     */         public int read() throws IOException {
/*  96 */           return bis.read();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isJsonRequest() {
/* 108 */     String header = getHeader("Content-Type");
/* 109 */     return StringUtils.startsWithIgnoreCase(header, "application/json");
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/filter/XssHttpServletRequestWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */