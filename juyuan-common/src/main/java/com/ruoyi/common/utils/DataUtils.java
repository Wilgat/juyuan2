/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataUtils
/*     */ {
/*     */   public static String bytesToHexString(byte[] bArr) {
/*   9 */     if (bArr == null) {
/*  10 */       return null;
/*     */     }
/*  12 */     StringBuffer sb = new StringBuffer(bArr.length);
/*     */ 
/*     */     
/*  15 */     for (int i = 0; i < bArr.length; i++) {
/*  16 */       String sTmp = Integer.toHexString(0xFF & bArr[i]);
/*  17 */       if (sTmp.length() < 2)
/*  18 */         sb.append(0); 
/*  19 */       sb.append(sTmp);
/*     */     } 
/*     */     
/*  22 */     return sb.toString().toUpperCase();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String intTohex(int n) {
/*  32 */     StringBuffer s = new StringBuffer();
/*     */     
/*  34 */     char[] b = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*  35 */     while (n != 0) {
/*  36 */       s = s.append(b[n % 16]);
/*  37 */       n /= 16;
/*     */     } 
/*  39 */     String a = s.reverse().toString();
/*  40 */     if ("".equals(a)) {
/*  41 */       a = "00";
/*     */     }
/*  43 */     if (a.length() == 1) {
/*  44 */       a = "0" + a;
/*     */     }
/*  46 */     return a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String string2HexString(String strPart) {
/*  57 */     StringBuffer hexString = new StringBuffer();
/*  58 */     for (int i = 0; i < strPart.length(); i++) {
/*  59 */       int ch = strPart.charAt(i);
/*  60 */       String strHex = Integer.toHexString(ch);
/*  61 */       hexString.append(strHex);
/*     */     } 
/*  63 */     return hexString.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] hexString2Bytes(String src) {
/*  73 */     int l = src.length() / 2;
/*  74 */     byte[] ret = new byte[l];
/*  75 */     for (int i = 0; i < l; i++) {
/*  76 */       ret[i] = 
/*  77 */         Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
/*     */     }
/*  79 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte hexToByte(String inHex) {
/*  90 */     return (byte)Integer.parseInt(inHex, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] hexToByteArray(String inHex) {
/*     */     byte[] result;
/* 101 */     int hexlen = inHex.length();
/*     */     
/* 103 */     if (hexlen % 2 == 1) {
/*     */       
/* 105 */       hexlen++;
/* 106 */       result = new byte[hexlen / 2];
/* 107 */       inHex = "0" + inHex;
/*     */     } else {
/*     */       
/* 110 */       result = new byte[hexlen / 2];
/*     */     } 
/* 112 */     int j = 0;
/* 113 */     for (int i = 0; i < hexlen; i += 2) {
/* 114 */       result[j] = hexToByte(inHex.substring(i, i + 2));
/* 115 */       j++;
/*     */     } 
/* 117 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/DataUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */