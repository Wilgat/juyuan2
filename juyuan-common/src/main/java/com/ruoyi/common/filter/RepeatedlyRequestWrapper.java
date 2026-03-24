/*    */ package com.ruoyi.common.filter;
/*    */ 
/*    */ import com.ruoyi.common.utils.http.HttpHelper;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import javax.servlet.ReadListener;
/*    */ import javax.servlet.ServletInputStream;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletRequestWrapper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepeatedlyRequestWrapper
/*    */   extends HttpServletRequestWrapper
/*    */ {
/*    */   private final byte[] body;
/*    */   
/*    */   public RepeatedlyRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException {
/* 26 */     super(request);
/* 27 */     request.setCharacterEncoding("UTF-8");
/* 28 */     response.setCharacterEncoding("UTF-8");
/*    */     
/* 30 */     this.body = HttpHelper.getBodyString((ServletRequest)request).getBytes("UTF-8");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedReader getReader() throws IOException {
/* 36 */     return new BufferedReader(new InputStreamReader((InputStream)getInputStream()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ServletInputStream getInputStream() throws IOException {
/* 42 */     final ByteArrayInputStream bais = new ByteArrayInputStream(this.body);
/* 43 */     return new ServletInputStream()
/*    */       {
/*    */         
/*    */         public int read() throws IOException
/*    */         {
/* 48 */           return bais.read();
/*    */         }
/*    */ 
/*    */ 
/*    */         
/*    */         public int available() throws IOException {
/* 54 */           return RepeatedlyRequestWrapper.this.body.length;
/*    */         }
/*    */ 
/*    */ 
/*    */         
/*    */         public boolean isFinished() {
/* 60 */           return false;
/*    */         }
/*    */ 
/*    */ 
/*    */         
/*    */         public boolean isReady() {
/* 66 */           return false;
/*    */         }
/*    */         
/*    */         public void setReadListener(ReadListener readListener) {}
/*    */       };
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/filter/RepeatedlyRequestWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */