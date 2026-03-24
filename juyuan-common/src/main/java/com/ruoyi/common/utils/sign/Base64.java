/*     */ package com.ruoyi.common.utils.sign;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Base64
/*     */ {
/*     */   private static final int BASELENGTH = 128;
/*     */   private static final int LOOKUPLENGTH = 64;
/*     */   private static final int TWENTYFOURBITGROUP = 24;
/*     */   private static final int EIGHTBIT = 8;
/*     */   private static final int SIXTEENBIT = 16;
/*     */   private static final int FOURBYTE = 4;
/*     */   private static final int SIGN = -128;
/*     */   private static final char PAD = '=';
/*  18 */   private static final byte[] base64Alphabet = new byte[128];
/*  19 */   private static final char[] lookUpBase64Alphabet = new char[64];
/*     */   
/*     */   static {
/*     */     int i;
/*  23 */     for (i = 0; i < 128; i++)
/*     */     {
/*  25 */       base64Alphabet[i] = -1;
/*     */     }
/*  27 */     for (i = 90; i >= 65; i--)
/*     */     {
/*  29 */       base64Alphabet[i] = (byte)(i - 65);
/*     */     }
/*  31 */     for (i = 122; i >= 97; i--)
/*     */     {
/*  33 */       base64Alphabet[i] = (byte)(i - 97 + 26);
/*     */     }
/*     */     
/*  36 */     for (i = 57; i >= 48; i--)
/*     */     {
/*  38 */       base64Alphabet[i] = (byte)(i - 48 + 52);
/*     */     }
/*     */     
/*  41 */     base64Alphabet[43] = 62;
/*  42 */     base64Alphabet[47] = 63;
/*     */     
/*  44 */     for (i = 0; i <= 25; i++)
/*     */     {
/*  46 */       lookUpBase64Alphabet[i] = (char)(65 + i);
/*     */     }
/*     */     int j;
/*  49 */     for (i = 26, j = 0; i <= 51; i++, j++)
/*     */     {
/*  51 */       lookUpBase64Alphabet[i] = (char)(97 + j);
/*     */     }
/*     */     
/*  54 */     for (i = 52, j = 0; i <= 61; i++, j++)
/*     */     {
/*  56 */       lookUpBase64Alphabet[i] = (char)(48 + j);
/*     */     }
/*  58 */     lookUpBase64Alphabet[62] = '+';
/*  59 */     lookUpBase64Alphabet[63] = '/';
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isWhiteSpace(char octect) {
/*  64 */     return (octect == ' ' || octect == '\r' || octect == '\n' || octect == '\t');
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isPad(char octect) {
/*  69 */     return (octect == '=');
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isData(char octect) {
/*  74 */     return (octect < '' && base64Alphabet[octect] != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String encode(byte[] binaryData) {
/*  85 */     if (binaryData == null)
/*     */     {
/*  87 */       return null;
/*     */     }
/*     */     
/*  90 */     int lengthDataBits = binaryData.length * 8;
/*  91 */     if (lengthDataBits == 0)
/*     */     {
/*  93 */       return "";
/*     */     }
/*     */     
/*  96 */     int fewerThan24bits = lengthDataBits % 24;
/*  97 */     int numberTriplets = lengthDataBits / 24;
/*  98 */     int numberQuartet = (fewerThan24bits != 0) ? (numberTriplets + 1) : numberTriplets;
/*  99 */     char[] encodedData = null;
/*     */     
/* 101 */     encodedData = new char[numberQuartet * 4];
/*     */     
/* 103 */     byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;
/*     */     
/* 105 */     int encodedIndex = 0;
/* 106 */     int dataIndex = 0;
/*     */     
/* 108 */     for (int i = 0; i < numberTriplets; i++) {
/*     */       
/* 110 */       b1 = binaryData[dataIndex++];
/* 111 */       b2 = binaryData[dataIndex++];
/* 112 */       b3 = binaryData[dataIndex++];
/*     */       
/* 114 */       l = (byte)(b2 & 0xF);
/* 115 */       k = (byte)(b1 & 0x3);
/*     */       
/* 117 */       byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 118 */       byte val2 = ((b2 & Byte.MIN_VALUE) == 0) ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/* 119 */       byte val3 = ((b3 & Byte.MIN_VALUE) == 0) ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 0xFC);
/*     */       
/* 121 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/* 122 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
/* 123 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2 | val3];
/* 124 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3F];
/*     */     } 
/*     */ 
/*     */     
/* 128 */     if (fewerThan24bits == 8) {
/*     */       
/* 130 */       b1 = binaryData[dataIndex];
/* 131 */       k = (byte)(b1 & 0x3);
/* 132 */       byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 133 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/* 134 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];
/* 135 */       encodedData[encodedIndex++] = '=';
/* 136 */       encodedData[encodedIndex++] = '=';
/*     */     }
/* 138 */     else if (fewerThan24bits == 16) {
/*     */       
/* 140 */       b1 = binaryData[dataIndex];
/* 141 */       b2 = binaryData[dataIndex + 1];
/* 142 */       l = (byte)(b2 & 0xF);
/* 143 */       k = (byte)(b1 & 0x3);
/*     */       
/* 145 */       byte val1 = ((b1 & Byte.MIN_VALUE) == 0) ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 146 */       byte val2 = ((b2 & Byte.MIN_VALUE) == 0) ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*     */       
/* 148 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
/* 149 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
/* 150 */       encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];
/* 151 */       encodedData[encodedIndex++] = '=';
/*     */     } 
/* 153 */     return new String(encodedData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] decode(String encoded) {
/* 164 */     if (encoded == null)
/*     */     {
/* 166 */       return null;
/*     */     }
/*     */     
/* 169 */     char[] base64Data = encoded.toCharArray();
/*     */     
/* 171 */     int len = removeWhiteSpace(base64Data);
/*     */     
/* 173 */     if (len % 4 != 0)
/*     */     {
/* 175 */       return null;
/*     */     }
/*     */     
/* 178 */     int numberQuadruple = len / 4;
/*     */     
/* 180 */     if (numberQuadruple == 0)
/*     */     {
/* 182 */       return new byte[0];
/*     */     }
/*     */     
/* 185 */     byte[] decodedData = null;
/* 186 */     byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;
/* 187 */     char d1 = Character.MIN_VALUE, d2 = Character.MIN_VALUE, d3 = Character.MIN_VALUE, d4 = Character.MIN_VALUE;
/*     */     
/* 189 */     int i = 0;
/* 190 */     int encodedIndex = 0;
/* 191 */     int dataIndex = 0;
/* 192 */     decodedData = new byte[numberQuadruple * 3];
/*     */     
/* 194 */     for (; i < numberQuadruple - 1; i++) {
/*     */ 
/*     */       
/* 197 */       if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++]) || 
/* 198 */         !isData(d3 = base64Data[dataIndex++]) || !isData(d4 = base64Data[dataIndex++]))
/*     */       {
/* 200 */         return null;
/*     */       }
/*     */       
/* 203 */       b1 = base64Alphabet[d1];
/* 204 */       b2 = base64Alphabet[d2];
/* 205 */       b3 = base64Alphabet[d3];
/* 206 */       b4 = base64Alphabet[d4];
/*     */       
/* 208 */       decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/* 209 */       decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/* 210 */       decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
/*     */     } 
/*     */     
/* 213 */     if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++]))
/*     */     {
/* 215 */       return null;
/*     */     }
/*     */     
/* 218 */     b1 = base64Alphabet[d1];
/* 219 */     b2 = base64Alphabet[d2];
/*     */     
/* 221 */     d3 = base64Data[dataIndex++];
/* 222 */     d4 = base64Data[dataIndex++];
/* 223 */     if (!isData(d3) || !isData(d4)) {
/*     */       
/* 225 */       if (isPad(d3) && isPad(d4)) {
/*     */         
/* 227 */         if ((b2 & 0xF) != 0)
/*     */         {
/* 229 */           return null;
/*     */         }
/* 231 */         byte[] tmp = new byte[i * 3 + 1];
/* 232 */         System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/* 233 */         tmp[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
/* 234 */         return tmp;
/*     */       } 
/* 236 */       if (!isPad(d3) && isPad(d4)) {
/*     */         
/* 238 */         b3 = base64Alphabet[d3];
/* 239 */         if ((b3 & 0x3) != 0)
/*     */         {
/* 241 */           return null;
/*     */         }
/* 243 */         byte[] tmp = new byte[i * 3 + 2];
/* 244 */         System.arraycopy(decodedData, 0, tmp, 0, i * 3);
/* 245 */         tmp[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/* 246 */         tmp[encodedIndex] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/* 247 */         return tmp;
/*     */       } 
/*     */ 
/*     */       
/* 251 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 256 */     b3 = base64Alphabet[d3];
/* 257 */     b4 = base64Alphabet[d4];
/* 258 */     decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
/* 259 */     decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
/* 260 */     decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
/*     */ 
/*     */     
/* 263 */     return decodedData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int removeWhiteSpace(char[] data) {
/* 274 */     if (data == null)
/*     */     {
/* 276 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 280 */     int newSize = 0;
/* 281 */     int len = data.length;
/* 282 */     for (int i = 0; i < len; i++) {
/*     */       
/* 284 */       if (!isWhiteSpace(data[i]))
/*     */       {
/* 286 */         data[newSize++] = data[i];
/*     */       }
/*     */     } 
/* 289 */     return newSize;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/sign/Base64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */