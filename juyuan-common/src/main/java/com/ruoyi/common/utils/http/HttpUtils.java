/*     */ package com.ruoyi.common.utils.http;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.ConnectException;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.cert.X509Certificate;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpUtils
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String sendGet(String url) {
/*  42 */     return sendGet(url, "");
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
/*     */   public static String sendGet(String url, String param) {
/*  54 */     return sendGet(url, param, "UTF-8");
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
/*     */   
/*     */   public static String sendGet(String url, String param, String contentType) {
/*  67 */     StringBuilder result = new StringBuilder();
/*  68 */     BufferedReader in = null;
/*     */     
/*     */     try {
/*  71 */       String urlNameString = StringUtils.isNotBlank(param) ? (url + "?" + param) : url;
/*  72 */       log.info("sendGet - {}", urlNameString);
/*  73 */       URL realUrl = new URL(urlNameString);
/*  74 */       URLConnection connection = realUrl.openConnection();
/*  75 */       connection.setRequestProperty("accept", "*/*");
/*  76 */       connection.setRequestProperty("connection", "Keep-Alive");
/*  77 */       connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
/*  78 */       connection.connect();
/*  79 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
/*     */       String line;
/*  81 */       while ((line = in.readLine()) != null)
/*     */       {
/*  83 */         result.append(line);
/*     */       }
/*  85 */       log.info("recv - {}", result);
/*     */     }
/*  87 */     catch (ConnectException e) {
/*     */       
/*  89 */       log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
/*     */     }
/*  91 */     catch (SocketTimeoutException e) {
/*     */       
/*  93 */       log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
/*     */     }
/*  95 */     catch (IOException e) {
/*     */       
/*  97 */       log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
/*     */     }
/*  99 */     catch (Exception e) {
/*     */       
/* 101 */       log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 107 */         if (in != null)
/*     */         {
/* 109 */           in.close();
/*     */         }
/*     */       }
/* 112 */       catch (Exception ex) {
/*     */         
/* 114 */         log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
/*     */       } 
/*     */     } 
/* 117 */     return result.toString();
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
/*     */   public static String sendPost(String url, String param) {
/* 129 */     PrintWriter out = null;
/* 130 */     BufferedReader in = null;
/* 131 */     StringBuilder result = new StringBuilder();
/*     */     
/*     */     try {
/* 134 */       log.info("sendPost - {}", url);
/* 135 */       URL realUrl = new URL(url);
/* 136 */       URLConnection conn = realUrl.openConnection();
/* 137 */       conn.setRequestProperty("accept", "*/*");
/* 138 */       conn.setRequestProperty("connection", "Keep-Alive");
/* 139 */       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
/* 140 */       conn.setRequestProperty("Accept-Charset", "utf-8");
/* 141 */       conn.setRequestProperty("contentType", "utf-8");
/* 142 */       conn.setDoOutput(true);
/* 143 */       conn.setDoInput(true);
/* 144 */       out = new PrintWriter(conn.getOutputStream());
/* 145 */       out.print(param);
/* 146 */       out.flush();
/* 147 */       in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
/*     */       String line;
/* 149 */       while ((line = in.readLine()) != null)
/*     */       {
/* 151 */         result.append(line);
/*     */       }
/* 153 */       log.info("recv - {}", result);
/*     */     }
/* 155 */     catch (ConnectException e) {
/*     */       
/* 157 */       log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
/*     */     }
/* 159 */     catch (SocketTimeoutException e) {
/*     */       
/* 161 */       log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
/*     */     }
/* 163 */     catch (IOException e) {
/*     */       
/* 165 */       log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
/*     */     }
/* 167 */     catch (Exception e) {
/*     */       
/* 169 */       log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 175 */         if (out != null)
/*     */         {
/* 177 */           out.close();
/*     */         }
/* 179 */         if (in != null)
/*     */         {
/* 181 */           in.close();
/*     */         }
/*     */       }
/* 184 */       catch (IOException ex) {
/*     */         
/* 186 */         log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
/*     */       } 
/*     */     } 
/* 189 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String sendSSLPost(String url, String param) {
/* 194 */     StringBuilder result = new StringBuilder();
/* 195 */     String urlNameString = url + "?" + param;
/*     */     
/*     */     try {
/* 198 */       log.info("sendSSLPost - {}", urlNameString);
/* 199 */       SSLContext sc = SSLContext.getInstance("SSL");
/* 200 */       sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());
/* 201 */       URL console = new URL(urlNameString);
/* 202 */       HttpsURLConnection conn = (HttpsURLConnection)console.openConnection();
/* 203 */       conn.setRequestProperty("accept", "*/*");
/* 204 */       conn.setRequestProperty("connection", "Keep-Alive");
/* 205 */       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
/* 206 */       conn.setRequestProperty("Accept-Charset", "utf-8");
/* 207 */       conn.setRequestProperty("contentType", "utf-8");
/* 208 */       conn.setDoOutput(true);
/* 209 */       conn.setDoInput(true);
/*     */       
/* 211 */       conn.setSSLSocketFactory(sc.getSocketFactory());
/* 212 */       conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
/* 213 */       conn.connect();
/* 214 */       InputStream is = conn.getInputStream();
/* 215 */       BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 216 */       String ret = "";
/* 217 */       while ((ret = br.readLine()) != null) {
/*     */         
/* 219 */         if (ret != null && !"".equals(ret.trim()))
/*     */         {
/* 221 */           result.append(new String(ret.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
/*     */         }
/*     */       } 
/* 224 */       log.info("recv - {}", result);
/* 225 */       conn.disconnect();
/* 226 */       br.close();
/*     */     }
/* 228 */     catch (ConnectException e) {
/*     */       
/* 230 */       log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
/*     */     }
/* 232 */     catch (SocketTimeoutException e) {
/*     */       
/* 234 */       log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
/*     */     }
/* 236 */     catch (IOException e) {
/*     */       
/* 238 */       log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
/*     */     }
/* 240 */     catch (Exception e) {
/*     */       
/* 242 */       log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
/*     */     } 
/* 244 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class TrustAnyTrustManager
/*     */     implements X509TrustManager
/*     */   {
/*     */     private TrustAnyTrustManager() {}
/*     */ 
/*     */     
/*     */     public void checkClientTrusted(X509Certificate[] chain, String authType) {}
/*     */ 
/*     */     
/*     */     public void checkServerTrusted(X509Certificate[] chain, String authType) {}
/*     */ 
/*     */     
/*     */     public X509Certificate[] getAcceptedIssuers() {
/* 262 */       return new X509Certificate[0];
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TrustAnyHostnameVerifier
/*     */     implements HostnameVerifier {
/*     */     private TrustAnyHostnameVerifier() {}
/*     */     
/*     */     public boolean verify(String hostname, SSLSession session) {
/* 271 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/http/HttpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */