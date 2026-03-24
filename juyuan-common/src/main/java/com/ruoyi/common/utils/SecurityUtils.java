/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
/*     */ public class SecurityUtils
/*     */ {
/*     */   public static Long getUserId() {
/*     */     try {
/*  24 */       return getLoginUser().getUserId();
/*     */     }
/*  26 */     catch (Exception e) {
/*     */       
/*  28 */       throw new ServiceException("获取用户ID异常", Integer.valueOf(401));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Long getDeptId() {
/*     */     try {
/*  39 */       return getLoginUser().getDeptId();
/*     */     }
/*  41 */     catch (Exception e) {
/*     */       
/*  43 */       throw new ServiceException("获取部门ID异常", Integer.valueOf(401));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getUsername() {
/*     */     try {
/*  54 */       return getLoginUser().getUsername();
/*     */     }
/*  56 */     catch (Exception e) {
/*     */       
/*  58 */       throw new ServiceException("获取用户账户异常", Integer.valueOf(401));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LoginUser getLoginUser() {
/*     */     try {
/*  69 */       return (LoginUser)getAuthentication().getPrincipal();
/*     */     }
/*  71 */     catch (Exception e) {
/*     */       
/*  73 */       throw new ServiceException("获取用户信息异常", Integer.valueOf(401));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Authentication getAuthentication() {
/*  82 */     return SecurityContextHolder.getContext().getAuthentication();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String encryptPassword(String password) {
/*  93 */     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
/*  94 */     return passwordEncoder.encode(password);
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
/*     */   public static boolean matchesPassword(String rawPassword, String encodedPassword) {
/* 106 */     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
/* 107 */     return passwordEncoder.matches(rawPassword, encodedPassword);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAdmin(Long userId) {
/* 118 */     return (userId != null && 1L == userId.longValue());
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/SecurityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */