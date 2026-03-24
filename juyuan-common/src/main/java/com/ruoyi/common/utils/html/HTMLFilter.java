/*     */ package com.ruoyi.common.utils.html;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class HTMLFilter
/*     */ {
/*     */   private static final int REGEX_FLAGS_SI = 34;
/*  24 */   private static final Pattern P_COMMENTS = Pattern.compile("<!--(.*?)-->", 32);
/*  25 */   private static final Pattern P_COMMENT = Pattern.compile("^!--(.*)--$", 34);
/*  26 */   private static final Pattern P_TAGS = Pattern.compile("<(.*?)>", 32);
/*  27 */   private static final Pattern P_END_TAG = Pattern.compile("^/([a-z0-9]+)", 34);
/*  28 */   private static final Pattern P_START_TAG = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", 34);
/*  29 */   private static final Pattern P_QUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", 34);
/*  30 */   private static final Pattern P_UNQUOTED_ATTRIBUTES = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", 34);
/*  31 */   private static final Pattern P_PROTOCOL = Pattern.compile("^([^:]+):", 34);
/*  32 */   private static final Pattern P_ENTITY = Pattern.compile("&#(\\d+);?");
/*  33 */   private static final Pattern P_ENTITY_UNICODE = Pattern.compile("&#x([0-9a-f]+);?");
/*  34 */   private static final Pattern P_ENCODE = Pattern.compile("%([0-9a-f]{2});?");
/*  35 */   private static final Pattern P_VALID_ENTITIES = Pattern.compile("&([^&;]*)(?=(;|&|$))");
/*  36 */   private static final Pattern P_VALID_QUOTES = Pattern.compile("(>|^)([^<]+?)(<|$)", 32);
/*  37 */   private static final Pattern P_END_ARROW = Pattern.compile("^>");
/*  38 */   private static final Pattern P_BODY_TO_END = Pattern.compile("<([^>]*?)(?=<|$)");
/*  39 */   private static final Pattern P_XML_CONTENT = Pattern.compile("(^|>)([^<]*?)(?=>)");
/*  40 */   private static final Pattern P_STRAY_LEFT_ARROW = Pattern.compile("<([^>]*?)(?=<|$)");
/*  41 */   private static final Pattern P_STRAY_RIGHT_ARROW = Pattern.compile("(^|>)([^<]*?)(?=>)");
/*  42 */   private static final Pattern P_AMP = Pattern.compile("&");
/*  43 */   private static final Pattern P_QUOTE = Pattern.compile("\"");
/*  44 */   private static final Pattern P_LEFT_ARROW = Pattern.compile("<");
/*  45 */   private static final Pattern P_RIGHT_ARROW = Pattern.compile(">");
/*  46 */   private static final Pattern P_BOTH_ARROWS = Pattern.compile("<>");
/*     */ 
/*     */   
/*  49 */   private static final ConcurrentMap<String, Pattern> P_REMOVE_PAIR_BLANKS = new ConcurrentHashMap<>();
/*  50 */   private static final ConcurrentMap<String, Pattern> P_REMOVE_SELF_BLANKS = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<String, List<String>> vAllowed;
/*     */ 
/*     */ 
/*     */   
/*  59 */   private final Map<String, Integer> vTagCounts = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vSelfClosingTags;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vNeedClosingTags;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vDisallowed;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vProtocolAtts;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vAllowedProtocols;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vRemoveBlanks;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String[] vAllowedEntities;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean stripComment;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean encodeQuotes;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean alwaysMakeTags;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HTMLFilter() {
/* 105 */     this.vAllowed = new HashMap<>();
/*     */     
/* 107 */     ArrayList<String> a_atts = new ArrayList<>();
/* 108 */     a_atts.add("href");
/* 109 */     a_atts.add("target");
/* 110 */     this.vAllowed.put("a", a_atts);
/*     */     
/* 112 */     ArrayList<String> img_atts = new ArrayList<>();
/* 113 */     img_atts.add("src");
/* 114 */     img_atts.add("width");
/* 115 */     img_atts.add("height");
/* 116 */     img_atts.add("alt");
/* 117 */     this.vAllowed.put("img", img_atts);
/*     */     
/* 119 */     ArrayList<String> no_atts = new ArrayList<>();
/* 120 */     this.vAllowed.put("b", no_atts);
/* 121 */     this.vAllowed.put("strong", no_atts);
/* 122 */     this.vAllowed.put("i", no_atts);
/* 123 */     this.vAllowed.put("em", no_atts);
/*     */     
/* 125 */     this.vSelfClosingTags = new String[] { "img" };
/* 126 */     this.vNeedClosingTags = new String[] { "a", "b", "strong", "i", "em" };
/* 127 */     this.vDisallowed = new String[0];
/* 128 */     this.vAllowedProtocols = new String[] { "http", "mailto", "https" };
/* 129 */     this.vProtocolAtts = new String[] { "src", "href" };
/* 130 */     this.vRemoveBlanks = new String[] { "a", "b", "strong", "i", "em" };
/* 131 */     this.vAllowedEntities = new String[] { "amp", "gt", "lt", "quot" };
/* 132 */     this.stripComment = true;
/* 133 */     this.encodeQuotes = true;
/* 134 */     this.alwaysMakeTags = false;
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
/*     */   public HTMLFilter(Map<String, Object> conf) {
/* 146 */     assert conf.containsKey("vAllowed") : "configuration requires vAllowed";
/* 147 */     assert conf.containsKey("vSelfClosingTags") : "configuration requires vSelfClosingTags";
/* 148 */     assert conf.containsKey("vNeedClosingTags") : "configuration requires vNeedClosingTags";
/* 149 */     assert conf.containsKey("vDisallowed") : "configuration requires vDisallowed";
/* 150 */     assert conf.containsKey("vAllowedProtocols") : "configuration requires vAllowedProtocols";
/* 151 */     assert conf.containsKey("vProtocolAtts") : "configuration requires vProtocolAtts";
/* 152 */     assert conf.containsKey("vRemoveBlanks") : "configuration requires vRemoveBlanks";
/* 153 */     assert conf.containsKey("vAllowedEntities") : "configuration requires vAllowedEntities";
/*     */     
/* 155 */     this.vAllowed = Collections.unmodifiableMap((HashMap)conf.get("vAllowed"));
/* 156 */     this.vSelfClosingTags = (String[])conf.get("vSelfClosingTags");
/* 157 */     this.vNeedClosingTags = (String[])conf.get("vNeedClosingTags");
/* 158 */     this.vDisallowed = (String[])conf.get("vDisallowed");
/* 159 */     this.vAllowedProtocols = (String[])conf.get("vAllowedProtocols");
/* 160 */     this.vProtocolAtts = (String[])conf.get("vProtocolAtts");
/* 161 */     this.vRemoveBlanks = (String[])conf.get("vRemoveBlanks");
/* 162 */     this.vAllowedEntities = (String[])conf.get("vAllowedEntities");
/* 163 */     this.stripComment = conf.containsKey("stripComment") ? ((Boolean)conf.get("stripComment")).booleanValue() : true;
/* 164 */     this.encodeQuotes = conf.containsKey("encodeQuotes") ? ((Boolean)conf.get("encodeQuotes")).booleanValue() : true;
/* 165 */     this.alwaysMakeTags = conf.containsKey("alwaysMakeTags") ? ((Boolean)conf.get("alwaysMakeTags")).booleanValue() : true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void reset() {
/* 170 */     this.vTagCounts.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String chr(int decimal) {
/* 177 */     return String.valueOf((char)decimal);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String htmlSpecialChars(String s) {
/* 182 */     String result = s;
/* 183 */     result = regexReplace(P_AMP, "&amp;", result);
/* 184 */     result = regexReplace(P_QUOTE, "&quot;", result);
/* 185 */     result = regexReplace(P_LEFT_ARROW, "&lt;", result);
/* 186 */     result = regexReplace(P_RIGHT_ARROW, "&gt;", result);
/* 187 */     return result;
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
/*     */   public String filter(String input) {
/* 200 */     reset();
/* 201 */     String s = input;
/*     */     
/* 203 */     s = escapeComments(s);
/*     */     
/* 205 */     s = balanceHTML(s);
/*     */     
/* 207 */     s = checkTags(s);
/*     */     
/* 209 */     s = processRemoveBlanks(s);
/*     */ 
/*     */ 
/*     */     
/* 213 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAlwaysMakeTags() {
/* 218 */     return this.alwaysMakeTags;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStripComments() {
/* 223 */     return this.stripComment;
/*     */   }
/*     */ 
/*     */   
/*     */   private String escapeComments(String s) {
/* 228 */     Matcher m = P_COMMENTS.matcher(s);
/* 229 */     StringBuffer buf = new StringBuffer();
/* 230 */     if (m.find()) {
/*     */       
/* 232 */       String match = m.group(1);
/* 233 */       m.appendReplacement(buf, Matcher.quoteReplacement("<!--" + htmlSpecialChars(match) + "-->"));
/*     */     } 
/* 235 */     m.appendTail(buf);
/*     */     
/* 237 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String balanceHTML(String s) {
/* 242 */     if (this.alwaysMakeTags) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 247 */       s = regexReplace(P_END_ARROW, "", s);
/*     */       
/* 249 */       s = regexReplace(P_BODY_TO_END, "<$1>", s);
/* 250 */       s = regexReplace(P_XML_CONTENT, "$1<$2", s);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 258 */       s = regexReplace(P_STRAY_LEFT_ARROW, "&lt;$1", s);
/* 259 */       s = regexReplace(P_STRAY_RIGHT_ARROW, "$1$2&gt;<", s);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 266 */       s = regexReplace(P_BOTH_ARROWS, "", s);
/*     */     } 
/*     */     
/* 269 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private String checkTags(String s) {
/* 274 */     Matcher m = P_TAGS.matcher(s);
/*     */     
/* 276 */     StringBuffer buf = new StringBuffer();
/* 277 */     while (m.find()) {
/*     */       
/* 279 */       String replaceStr = m.group(1);
/* 280 */       replaceStr = processTag(replaceStr);
/* 281 */       m.appendReplacement(buf, Matcher.quoteReplacement(replaceStr));
/*     */     } 
/* 283 */     m.appendTail(buf);
/*     */ 
/*     */ 
/*     */     
/* 287 */     StringBuilder sBuilder = new StringBuilder(buf.toString());
/* 288 */     for (String key : this.vTagCounts.keySet()) {
/*     */       
/* 290 */       for (int ii = 0; ii < ((Integer)this.vTagCounts.get(key)).intValue(); ii++)
/*     */       {
/* 292 */         sBuilder.append("</").append(key).append(">");
/*     */       }
/*     */     } 
/* 295 */     s = sBuilder.toString();
/*     */     
/* 297 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private String processRemoveBlanks(String s) {
/* 302 */     String result = s;
/* 303 */     for (String tag : this.vRemoveBlanks) {
/*     */       
/* 305 */       if (!P_REMOVE_PAIR_BLANKS.containsKey(tag))
/*     */       {
/* 307 */         P_REMOVE_PAIR_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?></" + tag + ">"));
/*     */       }
/* 309 */       result = regexReplace(P_REMOVE_PAIR_BLANKS.get(tag), "", result);
/* 310 */       if (!P_REMOVE_SELF_BLANKS.containsKey(tag))
/*     */       {
/* 312 */         P_REMOVE_SELF_BLANKS.putIfAbsent(tag, Pattern.compile("<" + tag + "(\\s[^>]*)?/>"));
/*     */       }
/* 314 */       result = regexReplace(P_REMOVE_SELF_BLANKS.get(tag), "", result);
/*     */     } 
/*     */     
/* 317 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String regexReplace(Pattern regex_pattern, String replacement, String s) {
/* 322 */     Matcher m = regex_pattern.matcher(s);
/* 323 */     return m.replaceAll(replacement);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String processTag(String s) {
/* 329 */     Matcher m = P_END_TAG.matcher(s);
/* 330 */     if (m.find()) {
/*     */       
/* 332 */       String name = m.group(1).toLowerCase();
/* 333 */       if (allowed(name))
/*     */       {
/* 335 */         if (!inArray(name, this.vSelfClosingTags))
/*     */         {
/* 337 */           if (this.vTagCounts.containsKey(name)) {
/*     */             
/* 339 */             this.vTagCounts.put(name, Integer.valueOf(((Integer)this.vTagCounts.get(name)).intValue() - 1));
/* 340 */             return "</" + name + ">";
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 347 */     m = P_START_TAG.matcher(s);
/* 348 */     if (m.find()) {
/*     */       
/* 350 */       String name = m.group(1).toLowerCase();
/* 351 */       String body = m.group(2);
/* 352 */       String ending = m.group(3);
/*     */ 
/*     */       
/* 355 */       if (allowed(name)) {
/*     */         
/* 357 */         StringBuilder params = new StringBuilder();
/*     */         
/* 359 */         Matcher m2 = P_QUOTED_ATTRIBUTES.matcher(body);
/* 360 */         Matcher m3 = P_UNQUOTED_ATTRIBUTES.matcher(body);
/* 361 */         List<String> paramNames = new ArrayList<>();
/* 362 */         List<String> paramValues = new ArrayList<>();
/* 363 */         while (m2.find()) {
/*     */           
/* 365 */           paramNames.add(m2.group(1));
/* 366 */           paramValues.add(m2.group(3));
/*     */         } 
/* 368 */         while (m3.find()) {
/*     */           
/* 370 */           paramNames.add(m3.group(1));
/* 371 */           paramValues.add(m3.group(3));
/*     */         } 
/*     */ 
/*     */         
/* 375 */         for (int ii = 0; ii < paramNames.size(); ii++) {
/*     */           
/* 377 */           String paramName = ((String)paramNames.get(ii)).toLowerCase();
/* 378 */           String paramValue = paramValues.get(ii);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 384 */           if (allowedAttribute(name, paramName)) {
/*     */             
/* 386 */             if (inArray(paramName, this.vProtocolAtts))
/*     */             {
/* 388 */               paramValue = processParamProtocol(paramValue);
/*     */             }
/* 390 */             params.append(' ').append(paramName).append("=\\\"").append(paramValue).append("\\\"");
/*     */           } 
/*     */         } 
/*     */         
/* 394 */         if (inArray(name, this.vSelfClosingTags))
/*     */         {
/* 396 */           ending = " /";
/*     */         }
/*     */         
/* 399 */         if (inArray(name, this.vNeedClosingTags))
/*     */         {
/* 401 */           ending = "";
/*     */         }
/*     */         
/* 404 */         if (ending == null || ending.length() < 1) {
/*     */           
/* 406 */           if (this.vTagCounts.containsKey(name))
/*     */           {
/* 408 */             this.vTagCounts.put(name, Integer.valueOf(((Integer)this.vTagCounts.get(name)).intValue() + 1));
/*     */           }
/*     */           else
/*     */           {
/* 412 */             this.vTagCounts.put(name, Integer.valueOf(1));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 417 */           ending = " /";
/*     */         } 
/* 419 */         return "<" + name + params + ending + ">";
/*     */       } 
/*     */ 
/*     */       
/* 423 */       return "";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 428 */     m = P_COMMENT.matcher(s);
/* 429 */     if (!this.stripComment && m.find())
/*     */     {
/* 431 */       return "<" + m.group() + ">";
/*     */     }
/*     */     
/* 434 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private String processParamProtocol(String s) {
/* 439 */     s = decodeEntities(s);
/* 440 */     Matcher m = P_PROTOCOL.matcher(s);
/* 441 */     if (m.find()) {
/*     */       
/* 443 */       String protocol = m.group(1);
/* 444 */       if (!inArray(protocol, this.vAllowedProtocols)) {
/*     */ 
/*     */         
/* 447 */         s = "#" + s.substring(protocol.length() + 1);
/* 448 */         if (s.startsWith("#//"))
/*     */         {
/* 450 */           s = "#" + s.substring(3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 455 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private String decodeEntities(String s) {
/* 460 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 462 */     Matcher m = P_ENTITY.matcher(s);
/* 463 */     while (m.find()) {
/*     */       
/* 465 */       String match = m.group(1);
/* 466 */       int decimal = Integer.decode(match).intValue();
/* 467 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 469 */     m.appendTail(buf);
/* 470 */     s = buf.toString();
/*     */     
/* 472 */     buf = new StringBuffer();
/* 473 */     m = P_ENTITY_UNICODE.matcher(s);
/* 474 */     while (m.find()) {
/*     */       
/* 476 */       String match = m.group(1);
/* 477 */       int decimal = Integer.valueOf(match, 16).intValue();
/* 478 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 480 */     m.appendTail(buf);
/* 481 */     s = buf.toString();
/*     */     
/* 483 */     buf = new StringBuffer();
/* 484 */     m = P_ENCODE.matcher(s);
/* 485 */     while (m.find()) {
/*     */       
/* 487 */       String match = m.group(1);
/* 488 */       int decimal = Integer.valueOf(match, 16).intValue();
/* 489 */       m.appendReplacement(buf, Matcher.quoteReplacement(chr(decimal)));
/*     */     } 
/* 491 */     m.appendTail(buf);
/* 492 */     s = buf.toString();
/*     */     
/* 494 */     s = validateEntities(s);
/* 495 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   private String validateEntities(String s) {
/* 500 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*     */     
/* 503 */     Matcher m = P_VALID_ENTITIES.matcher(s);
/* 504 */     while (m.find()) {
/*     */       
/* 506 */       String one = m.group(1);
/* 507 */       String two = m.group(2);
/* 508 */       m.appendReplacement(buf, Matcher.quoteReplacement(checkEntity(one, two)));
/*     */     } 
/* 510 */     m.appendTail(buf);
/*     */     
/* 512 */     return encodeQuotes(buf.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   private String encodeQuotes(String s) {
/* 517 */     if (this.encodeQuotes) {
/*     */       
/* 519 */       StringBuffer buf = new StringBuffer();
/* 520 */       Matcher m = P_VALID_QUOTES.matcher(s);
/* 521 */       while (m.find()) {
/*     */         
/* 523 */         String one = m.group(1);
/* 524 */         String two = m.group(2);
/* 525 */         String three = m.group(3);
/*     */         
/* 527 */         m.appendReplacement(buf, Matcher.quoteReplacement(one + two + three));
/*     */       } 
/* 529 */       m.appendTail(buf);
/* 530 */       return buf.toString();
/*     */     } 
/*     */ 
/*     */     
/* 534 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String checkEntity(String preamble, String term) {
/* 541 */     return (";".equals(term) && isValidEntity(preamble)) ? ('&' + preamble) : ("&amp;" + preamble);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValidEntity(String entity) {
/* 546 */     return inArray(entity, this.vAllowedEntities);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean inArray(String s, String[] array) {
/* 551 */     for (String item : array) {
/*     */       
/* 553 */       if (item != null && item.equals(s))
/*     */       {
/* 555 */         return true;
/*     */       }
/*     */     } 
/* 558 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean allowed(String name) {
/* 563 */     return ((this.vAllowed.isEmpty() || this.vAllowed.containsKey(name)) && !inArray(name, this.vDisallowed));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean allowedAttribute(String name, String paramName) {
/* 568 */     return (allowed(name) && (this.vAllowed.isEmpty() || ((List)this.vAllowed.get(name)).contains(paramName)));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/html/HTMLFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */