/*     */ package com.ruoyi.common.utils.ip;
/*     */ 
/*     */ import com.ruoyi.common.utils.ServletUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import javax.servlet.http.HttpServletRequest;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IpUtils
/*     */ {
/*     */   public static final String REGX_0_255 = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
/*     */   public static final String REGX_IP = "(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))";
/*     */   public static final String REGX_IP_WILDCARD = "(((\\*\\.){3}\\*)|((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)(\\.\\*){3})|((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))(\\.\\*){2}|(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}\\*))";
/*     */   public static final String REGX_IP_SEG = "((((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))\\-(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)))";
/*     */   
/*     */   public static String getIpAddr() {
/*  30 */     return getIpAddr(ServletUtils.getRequest());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIpAddr(HttpServletRequest request) {
/*  41 */     if (request == null)
/*     */     {
/*  43 */       return "unknown";
/*     */     }
/*  45 */     String ip = request.getHeader("x-forwarded-for");
/*  46 */     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
/*     */     {
/*  48 */       ip = request.getHeader("Proxy-Client-IP");
/*     */     }
/*  50 */     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
/*     */     {
/*  52 */       ip = request.getHeader("X-Forwarded-For");
/*     */     }
/*  54 */     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
/*     */     {
/*  56 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*     */     }
/*  58 */     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
/*     */     {
/*  60 */       ip = request.getHeader("X-Real-IP");
/*     */     }
/*     */     
/*  63 */     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
/*     */     {
/*  65 */       ip = request.getRemoteAddr();
/*     */     }
/*     */     
/*  68 */     return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean internalIp(String ip) {
/*  79 */     byte[] addr = textToNumericFormatV4(ip);
/*  80 */     return (internalIp(addr) || "127.0.0.1".equals(ip));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean internalIp(byte[] addr) {
/*  91 */     if (StringUtils.isNull(addr) || addr.length < 2)
/*     */     {
/*  93 */       return true;
/*     */     }
/*  95 */     byte b0 = addr[0];
/*  96 */     byte b1 = addr[1];
/*     */     
/*  98 */     byte SECTION_1 = 10;
/*     */     
/* 100 */     byte SECTION_2 = -84;
/* 101 */     byte SECTION_3 = 16;
/* 102 */     byte SECTION_4 = 31;
/*     */     
/* 104 */     byte SECTION_5 = -64;
/* 105 */     byte SECTION_6 = -88;
/* 106 */     switch (b0) {
/*     */       
/*     */       case 10:
/* 109 */         return true;
/*     */       case -84:
/* 111 */         if (b1 >= 16 && b1 <= 31)
/*     */         {
/* 113 */           return true;
/*     */         }
/*     */       case -64:
/* 116 */         switch (b1) {
/*     */           
/*     */           case -88:
/* 119 */             return true;
/*     */         }  break;
/*     */     } 
/* 122 */     return false;
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
/*     */   public static byte[] textToNumericFormatV4(String text) {
/* 134 */     if (text.length() == 0)
/*     */     {
/* 136 */       return null;
/*     */     }
/*     */     
/* 139 */     byte[] bytes = new byte[4];
/* 140 */     String[] elements = text.split("\\.", -1);
/*     */     
/*     */     try {
/*     */       long l;
/*     */       int i;
/* 145 */       switch (elements.length) {
/*     */         
/*     */         case 1:
/* 148 */           l = Long.parseLong(elements[0]);
/* 149 */           if (l < 0L || l > 4294967295L)
/*     */           {
/* 151 */             return null;
/*     */           }
/* 153 */           bytes[0] = (byte)(int)(l >> 24L & 0xFFL);
/* 154 */           bytes[1] = (byte)(int)((l & 0xFFFFFFL) >> 16L & 0xFFL);
/* 155 */           bytes[2] = (byte)(int)((l & 0xFFFFL) >> 8L & 0xFFL);
/* 156 */           bytes[3] = (byte)(int)(l & 0xFFL);
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
/* 211 */           return bytes;case 2: l = Integer.parseInt(elements[0]); if (l < 0L || l > 255L) return null;  bytes[0] = (byte)(int)(l & 0xFFL); l = Integer.parseInt(elements[1]); if (l < 0L || l > 16777215L) return null;  bytes[1] = (byte)(int)(l >> 16L & 0xFFL); bytes[2] = (byte)(int)((l & 0xFFFFL) >> 8L & 0xFFL); bytes[3] = (byte)(int)(l & 0xFFL); return bytes;case 3: for (i = 0; i < 2; i++) { l = Integer.parseInt(elements[i]); if (l < 0L || l > 255L) return null;  bytes[i] = (byte)(int)(l & 0xFFL); }  l = Integer.parseInt(elements[2]); if (l < 0L || l > 65535L) return null;  bytes[2] = (byte)(int)(l >> 8L & 0xFFL); bytes[3] = (byte)(int)(l & 0xFFL); return bytes;case 4: for (i = 0; i < 4; i++) { l = Integer.parseInt(elements[i]); if (l < 0L || l > 255L) return null;  bytes[i] = (byte)(int)(l & 0xFFL); }  return bytes;
/*     */       } 
/*     */       return null;
/*     */     } catch (NumberFormatException e) {
/*     */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getHostIp() {
/*     */     try {
/* 223 */       return InetAddress.getLocalHost().getHostAddress();
/*     */     }
/* 225 */     catch (UnknownHostException unknownHostException) {
/*     */ 
/*     */       
/* 228 */       return "127.0.0.1";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getHostName() {
/*     */     try {
/* 240 */       return InetAddress.getLocalHost().getHostName();
/*     */     }
/* 242 */     catch (UnknownHostException unknownHostException) {
/*     */ 
/*     */       
/* 245 */       return "未知";
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
/*     */   public static String getMultistageReverseProxyIp(String ip) {
/* 257 */     if (ip != null && ip.indexOf(",") > 0) {
/*     */       
/* 259 */       String[] ips = ip.trim().split(",");
/* 260 */       for (String subIp : ips) {
/*     */         
/* 262 */         if (false == isUnknown(subIp)) {
/*     */           
/* 264 */           ip = subIp;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 269 */     return StringUtils.substring(ip, 0, 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isUnknown(String checkString) {
/* 280 */     return (StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isIP(String ip) {
/* 288 */     return (StringUtils.isNotBlank(ip) && ip.matches("(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isIpWildCard(String ip) {
/* 296 */     return (StringUtils.isNotBlank(ip) && ip.matches("(((\\*\\.){3}\\*)|((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)(\\.\\*){3})|((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))(\\.\\*){2}|(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}\\*))"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean ipIsInWildCardNoCheck(String ipWildCard, String ip) {
/* 304 */     String[] s1 = ipWildCard.split("\\.");
/* 305 */     String[] s2 = ip.split("\\.");
/* 306 */     boolean isMatchedSeg = true;
/* 307 */     for (int i = 0; i < s1.length && !s1[i].equals("*"); i++) {
/*     */       
/* 309 */       if (!s1[i].equals(s2[i])) {
/*     */         
/* 311 */         isMatchedSeg = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 315 */     return isMatchedSeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isIPSegment(String ipSeg) {
/* 323 */     return (StringUtils.isNotBlank(ipSeg) && ipSeg.matches("((((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d))\\-(((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)))"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean ipIsInNetNoCheck(String iparea, String ip) {
/* 331 */     int idx = iparea.indexOf('-');
/* 332 */     String[] sips = iparea.substring(0, idx).split("\\.");
/* 333 */     String[] sipe = iparea.substring(idx + 1).split("\\.");
/* 334 */     String[] sipt = ip.split("\\.");
/* 335 */     long ips = 0L, ipe = 0L, ipt = 0L;
/* 336 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 338 */       ips = ips << 8L | Integer.parseInt(sips[i]);
/* 339 */       ipe = ipe << 8L | Integer.parseInt(sipe[i]);
/* 340 */       ipt = ipt << 8L | Integer.parseInt(sipt[i]);
/*     */     } 
/* 342 */     if (ips > ipe) {
/*     */       
/* 344 */       long t = ips;
/* 345 */       ips = ipe;
/* 346 */       ipe = t;
/*     */     } 
/* 348 */     return (ips <= ipt && ipt <= ipe);
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
/*     */   public static boolean isMatchedIp(String filter, String ip) {
/* 360 */     if (StringUtils.isEmpty(filter) || StringUtils.isEmpty(ip))
/*     */     {
/* 362 */       return false;
/*     */     }
/* 364 */     String[] ips = filter.split(";");
/* 365 */     for (String iStr : ips) {
/*     */       
/* 367 */       if (isIP(iStr) && iStr.equals(ip))
/*     */       {
/* 369 */         return true;
/*     */       }
/* 371 */       if (isIpWildCard(iStr) && ipIsInWildCardNoCheck(iStr, ip))
/*     */       {
/* 373 */         return true;
/*     */       }
/* 375 */       if (isIPSegment(iStr) && ipIsInNetNoCheck(iStr, ip))
/*     */       {
/* 377 */         return true;
/*     */       }
/*     */     } 
/* 380 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/ip/IpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */