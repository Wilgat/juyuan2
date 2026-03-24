/*     */ package com.ruoyi.framework.web.service;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.framework.security.context.PermissionContextHolder;
/*     */ import java.util.Set;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.CollectionUtils;
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
/*     */ @Service("ss")
/*     */ public class PermissionService
/*     */ {
/*     */   private static final String ALL_PERMISSION = "*:*:*";
/*     */   private static final String SUPER_ADMIN = "admin";
/*     */   private static final String ROLE_DELIMETER = ",";
/*     */   private static final String PERMISSION_DELIMETER = ",";
/*     */   
/*     */   public boolean hasPermi(String permission) {
/*  38 */     if (StringUtils.isEmpty(permission))
/*     */     {
/*  40 */       return false;
/*     */     }
/*  42 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/*  43 */     if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
/*     */     {
/*  45 */       return false;
/*     */     }
/*  47 */     PermissionContextHolder.setContext(permission);
/*  48 */     return hasPermissions(loginUser.getPermissions(), permission);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lacksPermi(String permission) {
/*  59 */     return (hasPermi(permission) != true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnyPermi(String permissions) {
/*  70 */     if (StringUtils.isEmpty(permissions))
/*     */     {
/*  72 */       return false;
/*     */     }
/*  74 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/*  75 */     if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
/*     */     {
/*  77 */       return false;
/*     */     }
/*  79 */     PermissionContextHolder.setContext(permissions);
/*  80 */     Set<String> authorities = loginUser.getPermissions();
/*  81 */     for (String permission : permissions.split(",")) {
/*     */       
/*  83 */       if (permission != null && hasPermissions(authorities, permission))
/*     */       {
/*  85 */         return true;
/*     */       }
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRole(String role) {
/*  99 */     if (StringUtils.isEmpty(role))
/*     */     {
/* 101 */       return false;
/*     */     }
/* 103 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/* 104 */     if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
/*     */     {
/* 106 */       return false;
/*     */     }
/* 108 */     for (SysRole sysRole : loginUser.getUser().getRoles()) {
/*     */       
/* 110 */       String roleKey = sysRole.getRoleKey();
/* 111 */       if ("admin".equals(roleKey) || roleKey.equals(StringUtils.trim(role)))
/*     */       {
/* 113 */         return true;
/*     */       }
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lacksRole(String role) {
/* 127 */     return (hasRole(role) != true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnyRoles(String roles) {
/* 138 */     if (StringUtils.isEmpty(roles))
/*     */     {
/* 140 */       return false;
/*     */     }
/* 142 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/* 143 */     if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
/*     */     {
/* 145 */       return false;
/*     */     }
/* 147 */     for (String role : roles.split(",")) {
/*     */       
/* 149 */       if (hasRole(role))
/*     */       {
/* 151 */         return true;
/*     */       }
/*     */     } 
/* 154 */     return false;
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
/*     */   private boolean hasPermissions(Set<String> permissions, String permission) {
/* 166 */     return (permissions.contains("*:*:*") || permissions.contains(StringUtils.trim(permission)));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/PermissionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */