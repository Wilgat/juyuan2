/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ import com.ruoyi.common.core.text.StrFormatter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ //import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.util.AntPathMatcher;
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
/*     */ public class StringUtils
/*     */   extends org.apache.commons.lang3.StringUtils
/*     */ {
/*     */   private static final String NULLSTR = "";
/*     */   private static final char SEPARATOR = '_';
/*     */   
/*     */   public static <T> T nvl(T value, T defaultValue) {
/*  34 */     return (value != null) ? value : defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Collection<?> coll) {
/*  45 */     return (isNull(coll) || coll.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotEmpty(Collection<?> coll) {
/*  56 */     return !isEmpty(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Object[] objects) {
/*  67 */     return (isNull(objects) || objects.length == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotEmpty(Object[] objects) {
/*  78 */     return !isEmpty(objects);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Map<?, ?> map) {
/*  89 */     return (isNull(map) || map.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotEmpty(Map<?, ?> map) {
/* 100 */     return !isEmpty(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String str) {
/* 111 */     return (isNull(str) || "".equals(str.trim()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotEmpty(String str) {
/* 122 */     return !isEmpty(str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNull(Object object) {
/* 133 */     return (object == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotNull(Object object) {
/* 144 */     return !isNull(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isArray(Object object) {
/* 155 */     return (isNotNull(object) && object.getClass().isArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String trim(String str) {
/* 163 */     return (str == null) ? "" : str.trim();
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
/*     */   public static String substring(String str, int start) {
/* 175 */     if (str == null)
/*     */     {
/* 177 */       return "";
/*     */     }
/*     */     
/* 180 */     if (start < 0)
/*     */     {
/* 182 */       start = str.length() + start;
/*     */     }
/*     */     
/* 185 */     if (start < 0)
/*     */     {
/* 187 */       start = 0;
/*     */     }
/* 189 */     if (start > str.length())
/*     */     {
/* 191 */       return "";
/*     */     }
/*     */     
/* 194 */     return str.substring(start);
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
/*     */   public static String substring(String str, int start, int end) {
/* 207 */     if (str == null)
/*     */     {
/* 209 */       return "";
/*     */     }
/*     */     
/* 212 */     if (end < 0)
/*     */     {
/* 214 */       end = str.length() + end;
/*     */     }
/* 216 */     if (start < 0)
/*     */     {
/* 218 */       start = str.length() + start;
/*     */     }
/*     */     
/* 221 */     if (end > str.length())
/*     */     {
/* 223 */       end = str.length();
/*     */     }
/*     */     
/* 226 */     if (start > end)
/*     */     {
/* 228 */       return "";
/*     */     }
/*     */     
/* 231 */     if (start < 0)
/*     */     {
/* 233 */       start = 0;
/*     */     }
/* 235 */     if (end < 0)
/*     */     {
/* 237 */       end = 0;
/*     */     }
/*     */     
/* 240 */     return str.substring(start, end);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String format(String template, Object... params) {
/* 258 */     if (isEmpty(params) || isEmpty(template))
/*     */     {
/* 260 */       return template;
/*     */     }
/* 262 */     return StrFormatter.format(template, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean ishttp(String link) {
/* 273 */     return startsWithAny(link, new CharSequence[] { "http://", "https://" });
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
/*     */   public static final Set<String> str2Set(String str, String sep) {
/* 285 */     return new HashSet<>(str2List(str, sep, true, false));
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
/*     */   public static final List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
/* 299 */     List<String> list = new ArrayList<>();
/* 300 */     if (isEmpty(str))
/*     */     {
/* 302 */       return list;
/*     */     }
/*     */ 
/*     */     
/* 306 */     if (filterBlank && isBlank(str))
/*     */     {
/* 308 */       return list;
/*     */     }
/* 310 */     String[] split = str.split(sep);
/* 311 */     for (String string : split) {
/*     */       
/* 313 */       if (!filterBlank || !isBlank(string)) {
/*     */ 
/*     */ 
/*     */         
/* 317 */         if (trim)
/*     */         {
/* 319 */           string = string.trim();
/*     */         }
/* 321 */         list.add(string);
/*     */       } 
/*     */     } 
/* 324 */     return list;
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
/*     */   public static boolean containsAny(Collection<String> collection, String... array) {
/* 336 */     if (isEmpty(collection) || isEmpty((Object[])array))
/*     */     {
/* 338 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 342 */     for (String str : array) {
/*     */       
/* 344 */       if (collection.contains(str))
/*     */       {
/* 346 */         return true;
/*     */       }
/*     */     } 
/* 349 */     return false;
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
/*     */   public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
/* 362 */     if (isEmpty(cs) || isEmpty((Object[])searchCharSequences))
/*     */     {
/* 364 */       return false;
/*     */     }
/* 366 */     for (CharSequence testStr : searchCharSequences) {
/*     */       
/* 368 */       if (containsIgnoreCase(cs, testStr))
/*     */       {
/* 370 */         return true;
/*     */       }
/*     */     } 
/* 373 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toUnderScoreCase(String str) {
/* 381 */     if (str == null)
/*     */     {
/* 383 */       return null;
/*     */     }
/* 385 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 387 */     boolean preCharIsUpperCase = true;
/*     */     
/* 389 */     boolean curreCharIsUpperCase = true;
/*     */     
/* 391 */     boolean nexteCharIsUpperCase = true;
/* 392 */     for (int i = 0; i < str.length(); i++) {
/*     */       
/* 394 */       char c = str.charAt(i);
/* 395 */       if (i > 0) {
/*     */         
/* 397 */         preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
/*     */       }
/*     */       else {
/*     */         
/* 401 */         preCharIsUpperCase = false;
/*     */       } 
/*     */       
/* 404 */       curreCharIsUpperCase = Character.isUpperCase(c);
/*     */       
/* 406 */       if (i < str.length() - 1)
/*     */       {
/* 408 */         nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
/*     */       }
/*     */       
/* 411 */       if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
/*     */         
/* 413 */         sb.append('_');
/*     */       }
/* 415 */       else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
/*     */         
/* 417 */         sb.append('_');
/*     */       } 
/* 419 */       sb.append(Character.toLowerCase(c));
/*     */     } 
/*     */     
/* 422 */     return sb.toString();
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
/*     */   public static boolean inStringIgnoreCase(String str, String... strs) {
/* 434 */     if (str != null && strs != null)
/*     */     {
/* 436 */       for (String s : strs) {
/*     */         
/* 438 */         if (str.equalsIgnoreCase(trim(s)))
/*     */         {
/* 440 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 444 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String convertToCamelCase(String name) {
/* 455 */     StringBuilder result = new StringBuilder();
/*     */     
/* 457 */     if (name == null || name.isEmpty())
/*     */     {
/*     */       
/* 460 */       return "";
/*     */     }
/* 462 */     if (!name.contains("_"))
/*     */     {
/*     */       
/* 465 */       return name.substring(0, 1).toUpperCase() + name.substring(1);
/*     */     }
/*     */     
/* 468 */     String[] camels = name.split("_");
/* 469 */     for (String camel : camels) {
/*     */ 
/*     */       
/* 472 */       if (!camel.isEmpty()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 477 */         result.append(camel.substring(0, 1).toUpperCase());
/* 478 */         result.append(camel.substring(1).toLowerCase());
/*     */       } 
/* 480 */     }  return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toCamelCase(String s) {
/* 489 */     if (s == null)
/*     */     {
/* 491 */       return null;
/*     */     }
/* 493 */     if (s.indexOf('_') == -1)
/*     */     {
/* 495 */       return s;
/*     */     }
/* 497 */     s = s.toLowerCase();
/* 498 */     StringBuilder sb = new StringBuilder(s.length());
/* 499 */     boolean upperCase = false;
/* 500 */     for (int i = 0; i < s.length(); i++) {
/*     */       
/* 502 */       char c = s.charAt(i);
/*     */       
/* 504 */       if (c == '_') {
/*     */         
/* 506 */         upperCase = true;
/*     */       }
/* 508 */       else if (upperCase) {
/*     */         
/* 510 */         sb.append(Character.toUpperCase(c));
/* 511 */         upperCase = false;
/*     */       }
/*     */       else {
/*     */         
/* 515 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 518 */     return sb.toString();
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
/*     */   public static boolean matches(String str, List<String> strs) {
/* 530 */     if (isEmpty(str) || isEmpty(strs))
/*     */     {
/* 532 */       return false;
/*     */     }
/* 534 */     for (String pattern : strs) {
/*     */       
/* 536 */       if (isMatch(pattern, str))
/*     */       {
/* 538 */         return true;
/*     */       }
/*     */     } 
/* 541 */     return false;
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
/*     */   
/*     */   public static boolean isMatch(String pattern, String url) {
/* 556 */     AntPathMatcher matcher = new AntPathMatcher();
/* 557 */     return matcher.match(pattern, url);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T cast(Object obj) {
/* 563 */     return (T)obj;
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
/*     */   public static final String padl(Number num, int size) {
/* 575 */     return padl(num.toString(), size, '0');
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
/*     */   public static final String padl(String s, int size, char c) {
/* 588 */     StringBuilder sb = new StringBuilder(size);
/* 589 */     if (s != null) {
/*     */       
/* 591 */       int len = s.length();
/* 592 */       if (s.length() <= size)
/*     */       {
/* 594 */         for (int i = size - len; i > 0; i--)
/*     */         {
/* 596 */           sb.append(c);
/*     */         }
/* 598 */         sb.append(s);
/*     */       }
/*     */       else
/*     */       {
/* 602 */         return s.substring(len - size, len);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 607 */       for (int i = size; i > 0; i--)
/*     */       {
/* 609 */         sb.append(c);
/*     */       }
/*     */     } 
/* 612 */     return sb.toString();
/*     */   }
/*     */ }

