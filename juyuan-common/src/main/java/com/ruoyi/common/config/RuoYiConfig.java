/*     */ package com.ruoyi.common.config;
/*     */ 
/*     */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ @ConfigurationProperties(prefix = "ruoyi")
/*     */ public class RuoYiConfig
/*     */ {
/*     */   private String name;
/*     */   private String version;
/*     */   private String copyrightYear;
/*     */   private boolean demoEnabled;
/*     */   private static String profile;
/*     */   private static boolean addressEnabled;
/*     */   private static String captchaType;
/*     */   
/*     */   public String getName() {
/*  50 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  54 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/*  58 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/*  62 */     this.version = version;
/*     */   }
/*     */   
/*     */   public String getCopyrightYear() {
/*  66 */     return this.copyrightYear;
/*     */   }
/*     */   
/*     */   public void setCopyrightYear(String copyrightYear) {
/*  70 */     this.copyrightYear = copyrightYear;
/*     */   }
/*     */   
/*     */   public boolean isDemoEnabled() {
/*  74 */     return this.demoEnabled;
/*     */   }
/*     */   
/*     */   public void setDemoEnabled(boolean demoEnabled) {
/*  78 */     this.demoEnabled = demoEnabled;
/*     */   }
/*     */   
/*     */   public static String getProfile() {
/*  82 */     return profile;
/*     */   }
/*     */   
/*     */   public void setProfile(String profile) {
/*  86 */     RuoYiConfig.profile = profile;
/*     */   }
/*     */   
/*     */   public static boolean isAddressEnabled() {
/*  90 */     return addressEnabled;
/*     */   }
/*     */   
/*     */   public void setAddressEnabled(boolean addressEnabled) {
/*  94 */     RuoYiConfig.addressEnabled = addressEnabled;
/*     */   }
/*     */   
/*     */   public static String getCaptchaType() {
/*  98 */     return captchaType;
/*     */   }
/*     */   
/*     */   public void setCaptchaType(String captchaType) {
/* 102 */     RuoYiConfig.captchaType = captchaType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getImportPath() {
/* 109 */     return getProfile() + "/import";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAvatarPath() {
/* 116 */     return getProfile() + "/avatar";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDownloadPath() {
/* 123 */     return getProfile() + "/download/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getUploadPath() {
/* 130 */     return getProfile() + "/upload";
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/config/RuoYiConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */