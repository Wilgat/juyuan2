/*     */ package com.ruoyi.common.constant;
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
/*     */ 
/*     */ public class Constants
/*     */ {
/*     */   public static final String UTF8 = "UTF-8";
/*     */   public static final String GBK = "GBK";
/*     */   public static final String WWW = "www.";
/*     */   public static final String HTTP = "http://";
/*     */   public static final String HTTPS = "https://";
/*     */   public static final String SUCCESS = "0";
/*     */   public static final String FAIL = "1";
/*     */   public static final String LOGIN_SUCCESS = "Success";
/*     */   public static final String LOGOUT = "Logout";
/*     */   public static final String REGISTER = "Register";
/*     */   public static final String LOGIN_FAIL = "Error";
/*  70 */   public static final Integer CAPTCHA_EXPIRATION = Integer.valueOf(2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOKEN = "token";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOKEN_PREFIX = "Bearer ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LOGIN_USER_KEY = "login_user_key";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String JWT_USERID = "userid";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String JWT_USERNAME = "sub";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String JWT_AVATAR = "avatar";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String JWT_CREATED = "created";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String JWT_AUTHORITIES = "authorities";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String RESOURCE_PREFIX = "/profile";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LOOKUP_RMI = "rmi:";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LOOKUP_LDAP = "ldap:";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LOOKUP_LDAPS = "ldaps:";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static final String[] JSON_WHITELIST_STR = new String[] { "org.springframework", "com.ruoyi" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public static final String[] JOB_WHITELIST_STR = new String[] { "com.ruoyi" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final String[] JOB_ERROR_STR = new String[] { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml", "org.springframework", "org.apache", "com.ruoyi.common.utils.file", "com.ruoyi.common.config" };
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/constant/Constants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */