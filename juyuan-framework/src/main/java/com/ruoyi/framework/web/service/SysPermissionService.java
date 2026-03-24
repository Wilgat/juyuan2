/*    */ package com.ruoyi.framework.web.service;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.entity.SysRole;
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.system.service.ISysMenuService;
/*    */ import com.ruoyi.system.service.ISysRoleService;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.util.CollectionUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class SysPermissionService
/*    */ {
/*    */   @Autowired
/*    */   private ISysRoleService roleService;
/*    */   @Autowired
/*    */   private ISysMenuService menuService;
/*    */   
/*    */   public Set<String> getRolePermission(SysUser user) {
/* 36 */     Set<String> roles = new HashSet<>();
/*    */     
/* 38 */     if (user.isAdmin()) {
/*    */       
/* 40 */       roles.add("admin");
/*    */     }
/*    */     else {
/*    */       
/* 44 */       roles.addAll(this.roleService.selectRolePermissionByUserId(user.getUserId()));
/*    */     } 
/* 46 */     return roles;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<String> getMenuPermission(SysUser user) {
/* 57 */     Set<String> perms = new HashSet<>();
/*    */     
/* 59 */     if (user.isAdmin()) {
/*    */       
/* 61 */       perms.add("*:*:*");
/*    */     }
/*    */     else {
/*    */       
/* 65 */       List<SysRole> roles = user.getRoles();
/* 66 */       if (!CollectionUtils.isEmpty(roles)) {
/*    */ 
/*    */         
/* 69 */         for (SysRole role : roles)
/*    */         {
/* 71 */           Set<String> rolePerms = this.menuService.selectMenuPermsByRoleId(role.getRoleId());
/* 72 */           role.setPermissions(rolePerms);
/* 73 */           perms.addAll(rolePerms);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 78 */         perms.addAll(this.menuService.selectMenuPermsByUserId(user.getUserId()));
/*    */       } 
/*    */     } 
/* 81 */     return perms;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/SysPermissionService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */