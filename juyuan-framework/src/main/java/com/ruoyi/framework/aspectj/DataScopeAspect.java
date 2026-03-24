/*     */ package com.ruoyi.framework.aspectj;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.framework.security.context.PermissionContextHolder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.aspectj.lang.JoinPoint;
/*     */ import org.aspectj.lang.annotation.Aspect;
/*     */ import org.aspectj.lang.annotation.Before;
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
/*     */ @Aspect
/*     */ @Component
/*     */ public class DataScopeAspect
/*     */ {
/*     */   public static final String DATA_SCOPE_ALL = "1";
/*     */   public static final String DATA_SCOPE_CUSTOM = "2";
/*     */   public static final String DATA_SCOPE_DEPT = "3";
/*     */   public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";
/*     */   public static final String DATA_SCOPE_SELF = "5";
/*     */   public static final String DATA_SCOPE = "dataScope";
/*     */   
/*     */   @Before("@annotation(controllerDataScope)")
/*     */   public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable {
/*  61 */     clearDataScope(point);
/*  62 */     handleDataScope(point, controllerDataScope);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleDataScope(JoinPoint joinPoint, DataScope controllerDataScope) {
/*  68 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/*  69 */     if (StringUtils.isNotNull(loginUser)) {
/*     */       
/*  71 */       SysUser currentUser = loginUser.getUser();
/*     */       
/*  73 */       if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
/*     */         
/*  75 */         String permission = (String)StringUtils.defaultIfEmpty(controllerDataScope.permission(), PermissionContextHolder.getContext());
/*  76 */         dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(), controllerDataScope
/*  77 */             .userAlias(), permission);
/*     */       } 
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
/*     */   
/*     */   public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias, String permission) {
/*  93 */     StringBuilder sqlString = new StringBuilder();
/*  94 */     List<String> conditions = new ArrayList<>();
/*     */     
/*  96 */     for (SysRole role : user.getRoles()) {
/*     */       
/*  98 */       String dataScope = role.getDataScope();
/*  99 */       if (!"2".equals(dataScope) && conditions.contains(dataScope)) {
/*     */         continue;
/*     */       }
/*     */       
/* 103 */       if (StringUtils.isNotEmpty(permission) && StringUtils.isNotEmpty(role.getPermissions()) && 
/* 104 */         !StringUtils.containsAny(role.getPermissions(), Convert.toStrArray(permission))) {
/*     */         continue;
/*     */       }
/*     */       
/* 108 */       if ("1".equals(dataScope)) {
/*     */         
/* 110 */         sqlString = new StringBuilder();
/* 111 */         conditions.add(dataScope);
/*     */         break;
/*     */       } 
/* 114 */       if ("2".equals(dataScope)) {
/*     */         
/* 116 */         sqlString.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", new Object[] { deptAlias, role
/*     */                 
/* 118 */                 .getRoleId() }));
/*     */       }
/* 120 */       else if ("3".equals(dataScope)) {
/*     */         
/* 122 */         sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", new Object[] { deptAlias, user.getDeptId() }));
/*     */       }
/* 124 */       else if ("4".equals(dataScope)) {
/*     */         
/* 126 */         sqlString.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )", new Object[] { deptAlias, user
/*     */                 
/* 128 */                 .getDeptId(), user.getDeptId() }));
/*     */       }
/* 130 */       else if ("5".equals(dataScope)) {
/*     */         
/* 132 */         if (StringUtils.isNotBlank(userAlias)) {
/*     */           
/* 134 */           sqlString.append(StringUtils.format(" OR {}.user_id = {} ", new Object[] { userAlias, user.getUserId() }));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 139 */           sqlString.append(StringUtils.format(" OR {}.dept_id = 0 ", new Object[] { deptAlias }));
/*     */         } 
/*     */       } 
/* 142 */       conditions.add(dataScope);
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (StringUtils.isEmpty(conditions))
/*     */     {
/* 148 */       sqlString.append(StringUtils.format(" OR {}.dept_id = 0 ", new Object[] { deptAlias }));
/*     */     }
/*     */     
/* 151 */     if (StringUtils.isNotBlank(sqlString.toString())) {
/*     */       
/* 153 */       Object params = joinPoint.getArgs()[0];
/* 154 */       if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
/*     */         
/* 156 */         BaseEntity baseEntity = (BaseEntity)params;
/* 157 */         baseEntity.getParams().put("dataScope", " AND (" + sqlString.substring(4) + ")");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearDataScope(JoinPoint joinPoint) {
/* 167 */     Object params = joinPoint.getArgs()[0];
/* 168 */     if (StringUtils.isNotNull(params) && params instanceof BaseEntity) {
/*     */       
/* 170 */       BaseEntity baseEntity = (BaseEntity)params;
/* 171 */       baseEntity.getParams().put("dataScope", "");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/aspectj/DataScopeAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */