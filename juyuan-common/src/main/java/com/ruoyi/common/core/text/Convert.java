/*     */ package com.ruoyi.common.core.text;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.ArrayUtils;
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
/*     */ public class Convert
/*     */ {
/*     */   public static String toStr(Object value, String defaultValue) {
/*  30 */     if (null == value)
/*     */     {
/*  32 */       return defaultValue;
/*     */     }
/*  34 */     if (value instanceof String)
/*     */     {
/*  36 */       return (String)value;
/*     */     }
/*  38 */     return value.toString();
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
/*     */   public static String toStr(Object value) {
/*  51 */     return toStr(value, null);
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
/*     */   
/*     */   public static Character toChar(Object value, Character defaultValue) {
/*  65 */     if (null == value)
/*     */     {
/*  67 */       return defaultValue;
/*     */     }
/*  69 */     if (value instanceof Character)
/*     */     {
/*  71 */       return (Character)value;
/*     */     }
/*     */     
/*  74 */     String valueStr = toStr(value, null);
/*  75 */     return Character.valueOf(StringUtils.isEmpty(valueStr) ? defaultValue.charValue() : valueStr.charAt(0));
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
/*     */   public static Character toChar(Object value) {
/*  88 */     return toChar(value, null);
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
/*     */   
/*     */   public static Byte toByte(Object value, Byte defaultValue) {
/* 102 */     if (value == null)
/*     */     {
/* 104 */       return defaultValue;
/*     */     }
/* 106 */     if (value instanceof Byte)
/*     */     {
/* 108 */       return (Byte)value;
/*     */     }
/* 110 */     if (value instanceof Number)
/*     */     {
/* 112 */       return Byte.valueOf(((Number)value).byteValue());
/*     */     }
/* 114 */     String valueStr = toStr(value, null);
/* 115 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 117 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 121 */       return Byte.valueOf(Byte.parseByte(valueStr));
/*     */     }
/* 123 */     catch (Exception e) {
/*     */       
/* 125 */       return defaultValue;
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
/*     */   
/*     */   public static Byte toByte(Object value) {
/* 139 */     return toByte(value, null);
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
/*     */   
/*     */   public static Short toShort(Object value, Short defaultValue) {
/* 153 */     if (value == null)
/*     */     {
/* 155 */       return defaultValue;
/*     */     }
/* 157 */     if (value instanceof Short)
/*     */     {
/* 159 */       return (Short)value;
/*     */     }
/* 161 */     if (value instanceof Number)
/*     */     {
/* 163 */       return Short.valueOf(((Number)value).shortValue());
/*     */     }
/* 165 */     String valueStr = toStr(value, null);
/* 166 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 168 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 172 */       return Short.valueOf(Short.parseShort(valueStr.trim()));
/*     */     }
/* 174 */     catch (Exception e) {
/*     */       
/* 176 */       return defaultValue;
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
/*     */   
/*     */   public static Short toShort(Object value) {
/* 190 */     return toShort(value, null);
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
/*     */   
/*     */   public static Number toNumber(Object value, Number defaultValue) {
/* 204 */     if (value == null)
/*     */     {
/* 206 */       return defaultValue;
/*     */     }
/* 208 */     if (value instanceof Number)
/*     */     {
/* 210 */       return (Number)value;
/*     */     }
/* 212 */     String valueStr = toStr(value, null);
/* 213 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 215 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 219 */       return NumberFormat.getInstance().parse(valueStr);
/*     */     }
/* 221 */     catch (Exception e) {
/*     */       
/* 223 */       return defaultValue;
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
/*     */   
/*     */   public static Number toNumber(Object value) {
/* 237 */     return toNumber(value, null);
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
/*     */   
/*     */   public static Integer toInt(Object value, Integer defaultValue) {
/* 251 */     if (value == null)
/*     */     {
/* 253 */       return defaultValue;
/*     */     }
/* 255 */     if (value instanceof Integer)
/*     */     {
/* 257 */       return (Integer)value;
/*     */     }
/* 259 */     if (value instanceof Number)
/*     */     {
/* 261 */       return Integer.valueOf(((Number)value).intValue());
/*     */     }
/* 263 */     String valueStr = toStr(value, null);
/* 264 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 266 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 270 */       return Integer.valueOf(Integer.parseInt(valueStr.trim()));
/*     */     }
/* 272 */     catch (Exception e) {
/*     */       
/* 274 */       return defaultValue;
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
/*     */   
/*     */   public static Integer toInt(Object value) {
/* 288 */     return toInt(value, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer[] toIntArray(String str) {
/* 299 */     return toIntArray(",", str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Long[] toLongArray(String str) {
/* 310 */     return toLongArray(",", str);
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
/*     */   public static Integer[] toIntArray(String split, String str) {
/* 322 */     if (StringUtils.isEmpty(str))
/*     */     {
/* 324 */       return new Integer[0];
/*     */     }
/* 326 */     String[] arr = str.split(split);
/* 327 */     Integer[] ints = new Integer[arr.length];
/* 328 */     for (int i = 0; i < arr.length; i++) {
/*     */       
/* 330 */       Integer v = toInt(arr[i], Integer.valueOf(0));
/* 331 */       ints[i] = v;
/*     */     } 
/* 333 */     return ints;
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
/*     */   public static Long[] toLongArray(String split, String str) {
/* 345 */     if (StringUtils.isEmpty(str))
/*     */     {
/* 347 */       return new Long[0];
/*     */     }
/* 349 */     String[] arr = str.split(split);
/* 350 */     Long[] longs = new Long[arr.length];
/* 351 */     for (int i = 0; i < arr.length; i++) {
/*     */       
/* 353 */       Long v = toLong(arr[i], null);
/* 354 */       longs[i] = v;
/*     */     } 
/* 356 */     return longs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] toStrArray(String str) {
/* 367 */     return toStrArray(",", str);
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
/*     */   public static String[] toStrArray(String split, String str) {
/* 379 */     return str.split(split);
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
/*     */   
/*     */   public static Long toLong(Object value, Long defaultValue) {
/* 393 */     if (value == null)
/*     */     {
/* 395 */       return defaultValue;
/*     */     }
/* 397 */     if (value instanceof Long)
/*     */     {
/* 399 */       return (Long)value;
/*     */     }
/* 401 */     if (value instanceof Number)
/*     */     {
/* 403 */       return Long.valueOf(((Number)value).longValue());
/*     */     }
/* 405 */     String valueStr = toStr(value, null);
/* 406 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 408 */       return defaultValue;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 413 */       return Long.valueOf((new BigDecimal(valueStr.trim())).longValue());
/*     */     }
/* 415 */     catch (Exception e) {
/*     */       
/* 417 */       return defaultValue;
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
/*     */   
/*     */   public static Long toLong(Object value) {
/* 431 */     return toLong(value, null);
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
/*     */   
/*     */   public static Double toDouble(Object value, Double defaultValue) {
/* 445 */     if (value == null)
/*     */     {
/* 447 */       return defaultValue;
/*     */     }
/* 449 */     if (value instanceof Double)
/*     */     {
/* 451 */       return (Double)value;
/*     */     }
/* 453 */     if (value instanceof Number)
/*     */     {
/* 455 */       return Double.valueOf(((Number)value).doubleValue());
/*     */     }
/* 457 */     String valueStr = toStr(value, null);
/* 458 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 460 */       return defaultValue;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 465 */       return Double.valueOf((new BigDecimal(valueStr.trim())).doubleValue());
/*     */     }
/* 467 */     catch (Exception e) {
/*     */       
/* 469 */       return defaultValue;
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
/*     */   
/*     */   public static Double toDouble(Object value) {
/* 483 */     return toDouble(value, null);
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
/*     */   
/*     */   public static Float toFloat(Object value, Float defaultValue) {
/* 497 */     if (value == null)
/*     */     {
/* 499 */       return defaultValue;
/*     */     }
/* 501 */     if (value instanceof Float)
/*     */     {
/* 503 */       return (Float)value;
/*     */     }
/* 505 */     if (value instanceof Number)
/*     */     {
/* 507 */       return Float.valueOf(((Number)value).floatValue());
/*     */     }
/* 509 */     String valueStr = toStr(value, null);
/* 510 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 512 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 516 */       return Float.valueOf(Float.parseFloat(valueStr.trim()));
/*     */     }
/* 518 */     catch (Exception e) {
/*     */       
/* 520 */       return defaultValue;
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
/*     */   
/*     */   public static Float toFloat(Object value) {
/* 534 */     return toFloat(value, null);
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
/*     */   
/*     */   public static Boolean toBool(Object value, Boolean defaultValue) {
/* 548 */     if (value == null)
/*     */     {
/* 550 */       return defaultValue;
/*     */     }
/* 552 */     if (value instanceof Boolean)
/*     */     {
/* 554 */       return (Boolean)value;
/*     */     }
/* 556 */     String valueStr = toStr(value, null);
/* 557 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 559 */       return defaultValue;
/*     */     }
/* 561 */     valueStr = valueStr.trim().toLowerCase();
/* 562 */     switch (valueStr) {
/*     */       
/*     */       case "true":
/*     */       case "yes":
/*     */       case "ok":
/*     */       case "1":
/* 568 */         return Boolean.valueOf(true);
/*     */       case "false":
/*     */       case "no":
/*     */       case "0":
/* 572 */         return Boolean.valueOf(false);
/*     */     } 
/* 574 */     return defaultValue;
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
/*     */   
/*     */   public static Boolean toBool(Object value) {
/* 588 */     return toBool(value, null);
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
/*     */   
/*     */   public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
/* 602 */     if (value == null)
/*     */     {
/* 604 */       return defaultValue;
/*     */     }
/* 606 */     if (clazz.isAssignableFrom(value.getClass()))
/*     */     {
/*     */       
/* 609 */       return (E)value;
/*     */     }
/*     */     
/* 612 */     String valueStr = toStr(value, null);
/* 613 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 615 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 619 */       return Enum.valueOf(clazz, valueStr);
/*     */     }
/* 621 */     catch (Exception e) {
/*     */       
/* 623 */       return defaultValue;
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
/*     */   
/*     */   public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
/* 637 */     return toEnum(clazz, value, null);
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
/*     */   
/*     */   public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
/* 651 */     if (value == null)
/*     */     {
/* 653 */       return defaultValue;
/*     */     }
/* 655 */     if (value instanceof BigInteger)
/*     */     {
/* 657 */       return (BigInteger)value;
/*     */     }
/* 659 */     if (value instanceof Long)
/*     */     {
/* 661 */       return BigInteger.valueOf(((Long)value).longValue());
/*     */     }
/* 663 */     String valueStr = toStr(value, null);
/* 664 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 666 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 670 */       return new BigInteger(valueStr);
/*     */     }
/* 672 */     catch (Exception e) {
/*     */       
/* 674 */       return defaultValue;
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
/*     */   
/*     */   public static BigInteger toBigInteger(Object value) {
/* 688 */     return toBigInteger(value, null);
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
/*     */   
/*     */   public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
/* 702 */     if (value == null)
/*     */     {
/* 704 */       return defaultValue;
/*     */     }
/* 706 */     if (value instanceof BigDecimal)
/*     */     {
/* 708 */       return (BigDecimal)value;
/*     */     }
/* 710 */     if (value instanceof Long)
/*     */     {
/* 712 */       return new BigDecimal(((Long)value).longValue());
/*     */     }
/* 714 */     if (value instanceof Double)
/*     */     {
/* 716 */       return BigDecimal.valueOf(((Double)value).doubleValue());
/*     */     }
/* 718 */     if (value instanceof Integer)
/*     */     {
/* 720 */       return new BigDecimal(((Integer)value).intValue());
/*     */     }
/* 722 */     String valueStr = toStr(value, null);
/* 723 */     if (StringUtils.isEmpty(valueStr))
/*     */     {
/* 725 */       return defaultValue;
/*     */     }
/*     */     
/*     */     try {
/* 729 */       return new BigDecimal(valueStr);
/*     */     }
/* 731 */     catch (Exception e) {
/*     */       
/* 733 */       return defaultValue;
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
/*     */   
/*     */   public static BigDecimal toBigDecimal(Object value) {
/* 747 */     return toBigDecimal(value, null);
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
/*     */   public static String utf8Str(Object obj) {
/* 759 */     return str(obj, CharsetKit.CHARSET_UTF_8);
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
/*     */   public static String str(Object obj, String charsetName) {
/* 772 */     return str(obj, Charset.forName(charsetName));
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
/*     */   public static String str(Object obj, Charset charset) {
/* 785 */     if (null == obj)
/*     */     {
/* 787 */       return null;
/*     */     }
/*     */     
/* 790 */     if (obj instanceof String)
/*     */     {
/* 792 */       return (String)obj;
/*     */     }
/* 794 */     if (obj instanceof byte[])
/*     */     {
/* 796 */       return str((byte[])obj, charset);
/*     */     }
/* 798 */     if (obj instanceof Byte[]) {
/*     */       
/* 800 */       byte[] bytes = ArrayUtils.toPrimitive((Byte[])obj);
/* 801 */       return str(bytes, charset);
/*     */     } 
/* 803 */     if (obj instanceof ByteBuffer)
/*     */     {
/* 805 */       return str((ByteBuffer)obj, charset);
/*     */     }
/* 807 */     return obj.toString();
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
/*     */   public static String str(byte[] bytes, String charset) {
/* 819 */     return str(bytes, StringUtils.isEmpty(charset) ? Charset.defaultCharset() : Charset.forName(charset));
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
/*     */   public static String str(byte[] data, Charset charset) {
/* 831 */     if (data == null)
/*     */     {
/* 833 */       return null;
/*     */     }
/*     */     
/* 836 */     if (null == charset)
/*     */     {
/* 838 */       return new String(data);
/*     */     }
/* 840 */     return new String(data, charset);
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
/*     */   public static String str(ByteBuffer data, String charset) {
/* 852 */     if (data == null)
/*     */     {
/* 854 */       return null;
/*     */     }
/*     */     
/* 857 */     return str(data, Charset.forName(charset));
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
/*     */   public static String str(ByteBuffer data, Charset charset) {
/* 869 */     if (null == charset)
/*     */     {
/* 871 */       charset = Charset.defaultCharset();
/*     */     }
/* 873 */     return charset.decode(data).toString();
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
/*     */   public static String toSBC(String input) {
/* 885 */     return toSBC(input, null);
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
/*     */   public static String toSBC(String input, Set<Character> notConvertSet) {
/* 897 */     char[] c = input.toCharArray();
/* 898 */     for (int i = 0; i < c.length; i++) {
/*     */       
/* 900 */       if (null == notConvertSet || !notConvertSet.contains(Character.valueOf(c[i])))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 906 */         if (c[i] == ' ') {
/*     */           
/* 908 */           c[i] = '　';
/*     */         }
/* 910 */         else if (c[i] < '') {
/*     */           
/* 912 */           c[i] = (char)(c[i] + 65248);
/*     */         } 
/*     */       }
/*     */     } 
/* 916 */     return new String(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toDBC(String input) {
/* 927 */     return toDBC(input, null);
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
/*     */   public static String toDBC(String text, Set<Character> notConvertSet) {
/* 939 */     char[] c = text.toCharArray();
/* 940 */     for (int i = 0; i < c.length; i++) {
/*     */       
/* 942 */       if (null == notConvertSet || !notConvertSet.contains(Character.valueOf(c[i])))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 948 */         if (c[i] == '　') {
/*     */           
/* 950 */           c[i] = ' ';
/*     */         }
/* 952 */         else if (c[i] > '＀' && c[i] < '｟') {
/*     */           
/* 954 */           c[i] = (char)(c[i] - 65248);
/*     */         }  } 
/*     */     } 
/* 957 */     String returnString = new String(c);
/*     */     
/* 959 */     return returnString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String digitUppercase(double n) {
/* 970 */     String[] fraction = { "角", "分" };
/* 971 */     String[] digit = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
/* 972 */     String[][] unit = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
/*     */     
/* 974 */     String head = (n < 0.0D) ? "负" : "";
/* 975 */     n = Math.abs(n);
/*     */     
/* 977 */     String s = "";
/* 978 */     for (int i = 0; i < fraction.length; i++)
/*     */     {
/* 980 */       s = s + (digit[(int)(Math.floor(n * 10.0D * Math.pow(10.0D, i)) % 10.0D)] + fraction[i]).replaceAll("(零.)+", "");
/*     */     }
/* 982 */     if (s.length() < 1)
/*     */     {
/* 984 */       s = "整";
/*     */     }
/* 986 */     int integerPart = (int)Math.floor(n);
/*     */     
/* 988 */     for (int j = 0; j < (unit[0]).length && integerPart > 0; j++) {
/*     */       
/* 990 */       String p = "";
/* 991 */       for (int k = 0; k < (unit[1]).length && n > 0.0D; k++) {
/*     */         
/* 993 */         p = digit[integerPart % 10] + unit[1][k] + p;
/* 994 */         integerPart /= 10;
/*     */       } 
/* 996 */       s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][j] + s;
/*     */     } 
/* 998 */     return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/text/Convert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */