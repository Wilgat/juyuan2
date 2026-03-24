/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.annotation.DataScope;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.system.domain.SysRoleDept;
/*     */ import com.ruoyi.system.domain.SysRoleMenu;
/*     */ import com.ruoyi.system.domain.SysUserRole;
/*     */ import com.ruoyi.system.mapper.SysRoleDeptMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMapper;
/*     */ import com.ruoyi.system.mapper.SysRoleMenuMapper;
/*     */ import com.ruoyi.system.mapper.SysUserRoleMapper;
/*     */ import com.ruoyi.system.service.ISysRoleService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ @Service
/*     */ public class SysRoleServiceImpl
/*     */   implements ISysRoleService
/*     */ {
/*     */   @Autowired
/*     */   private SysRoleMapper roleMapper;
/*     */   @Autowired
/*     */   private SysRoleMenuMapper roleMenuMapper;
/*     */   @Autowired
/*     */   private SysUserRoleMapper userRoleMapper;
/*     */   @Autowired
/*     */   private SysRoleDeptMapper roleDeptMapper;
/*     */   
/*     */   @DataScope(deptAlias = "d")
/*     */   public List<SysRole> selectRoleList(SysRole role) {
/*  58 */     return this.roleMapper.selectRoleList(role);
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
/*     */   public List<SysRole> selectRolesByUserId(Long userId) {
/*  70 */     List<SysRole> userRoles = this.roleMapper.selectRolePermissionByUserId(userId);
/*  71 */     List<SysRole> roles = selectRoleAll();
/*  72 */     for (SysRole role : roles) {
/*     */       
/*  74 */       for (SysRole userRole : userRoles) {
/*     */         
/*  76 */         if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
/*     */         {
/*  78 */           role.setFlag(true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     return roles;
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
/*     */   public Set<String> selectRolePermissionByUserId(Long userId) {
/*  95 */     List<SysRole> perms = this.roleMapper.selectRolePermissionByUserId(userId);
/*  96 */     Set<String> permsSet = new HashSet<>();
/*  97 */     for (SysRole perm : perms) {
/*     */       
/*  99 */       if (StringUtils.isNotNull(perm))
/*     */       {
/* 101 */         permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
/*     */       }
/*     */     } 
/* 104 */     return permsSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SysRole> selectRoleAll() {
/* 115 */     return ((SysRoleServiceImpl)SpringUtils.getAopProxy(this)).selectRoleList(new SysRole());
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
/*     */   public List<Long> selectRoleListByUserId(Long userId) {
/* 127 */     return this.roleMapper.selectRoleListByUserId(userId);
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
/*     */   public SysRole selectRoleById(Long roleId) {
/* 139 */     return this.roleMapper.selectRoleById(roleId);
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
/*     */   public boolean checkRoleNameUnique(SysRole role) {
/* 151 */     Long roleId = Long.valueOf(StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId().longValue());
/* 152 */     SysRole info = this.roleMapper.checkRoleNameUnique(role.getRoleName());
/* 153 */     if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
/*     */     {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
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
/*     */   public boolean checkRoleKeyUnique(SysRole role) {
/* 169 */     Long roleId = Long.valueOf(StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId().longValue());
/* 170 */     SysRole info = this.roleMapper.checkRoleKeyUnique(role.getRoleKey());
/* 171 */     if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
/*     */     {
/* 173 */       return false;
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkRoleAllowed(SysRole role) {
/* 186 */     if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
/*     */     {
/* 188 */       throw new ServiceException("不允许操作超级管理员角色");
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
/*     */   public void checkRoleDataScope(Long roleId) {
/* 200 */     if (!SysUser.isAdmin(SecurityUtils.getUserId())) {
/*     */       
/* 202 */       SysRole role = new SysRole();
/* 203 */       role.setRoleId(roleId);
/* 204 */       List<SysRole> roles = ((SysRoleServiceImpl)SpringUtils.getAopProxy(this)).selectRoleList(role);
/* 205 */       if (StringUtils.isEmpty(roles))
/*     */       {
/* 207 */         throw new ServiceException("没有权限访问角色数据！");
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
/*     */   public int countUserRoleByRoleId(Long roleId) {
/* 221 */     return this.userRoleMapper.countUserRoleByRoleId(roleId);
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
/*     */   @Transactional
/*     */   public int insertRole(SysRole role) {
/* 235 */     this.roleMapper.insertRole(role);
/* 236 */     return insertRoleMenu(role);
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
/*     */   @Transactional
/*     */   public int updateRole(SysRole role) {
/* 250 */     this.roleMapper.updateRole(role);
/*     */     
/* 252 */     this.roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
/* 253 */     return insertRoleMenu(role);
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
/*     */   public int updateRoleStatus(SysRole role) {
/* 265 */     return this.roleMapper.updateRole(role);
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
/*     */   @Transactional
/*     */   public int authDataScope(SysRole role) {
/* 279 */     this.roleMapper.updateRole(role);
/*     */     
/* 281 */     this.roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
/*     */     
/* 283 */     return insertRoleDept(role);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertRoleMenu(SysRole role) {
/* 293 */     int rows = 1;
/*     */     
/* 295 */     List<SysRoleMenu> list = new ArrayList<>();
/* 296 */     for (Long menuId : role.getMenuIds()) {
/*     */       
/* 298 */       SysRoleMenu rm = new SysRoleMenu();
/* 299 */       rm.setRoleId(role.getRoleId());
/* 300 */       rm.setMenuId(menuId);
/* 301 */       list.add(rm);
/*     */     } 
/* 303 */     if (list.size() > 0)
/*     */     {
/* 305 */       rows = this.roleMenuMapper.batchRoleMenu(list);
/*     */     }
/* 307 */     return rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertRoleDept(SysRole role) {
/* 317 */     int rows = 1;
/*     */     
/* 319 */     List<SysRoleDept> list = new ArrayList<>();
/* 320 */     for (Long deptId : role.getDeptIds()) {
/*     */       
/* 322 */       SysRoleDept rd = new SysRoleDept();
/* 323 */       rd.setRoleId(role.getRoleId());
/* 324 */       rd.setDeptId(deptId);
/* 325 */       list.add(rd);
/*     */     } 
/* 327 */     if (list.size() > 0)
/*     */     {
/* 329 */       rows = this.roleDeptMapper.batchRoleDept(list);
/*     */     }
/* 331 */     return rows;
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
/*     */   @Transactional
/*     */   public int deleteRoleById(Long roleId) {
/* 345 */     this.roleMenuMapper.deleteRoleMenuByRoleId(roleId);
/*     */     
/* 347 */     this.roleDeptMapper.deleteRoleDeptByRoleId(roleId);
/* 348 */     return this.roleMapper.deleteRoleById(roleId);
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
/*     */   @Transactional
/*     */   public int deleteRoleByIds(Long[] roleIds) {
/* 361 */     for (Long roleId : roleIds) {
/*     */       
/* 363 */       checkRoleAllowed(new SysRole(roleId));
/* 364 */       checkRoleDataScope(roleId);
/* 365 */       SysRole role = selectRoleById(roleId);
/* 366 */       if (countUserRoleByRoleId(roleId) > 0)
/*     */       {
/* 368 */         throw new ServiceException(String.format("%1$s已分配,不能删除", new Object[] { role.getRoleName() }));
/*     */       }
/*     */     } 
/*     */     
/* 372 */     this.roleMenuMapper.deleteRoleMenu(roleIds);
/*     */     
/* 374 */     this.roleDeptMapper.deleteRoleDept(roleIds);
/* 375 */     return this.roleMapper.deleteRoleByIds(roleIds);
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
/*     */   public int deleteAuthUser(SysUserRole userRole) {
/* 387 */     return this.userRoleMapper.deleteUserRoleInfo(userRole);
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
/*     */   public int deleteAuthUsers(Long roleId, Long[] userIds) {
/* 400 */     return this.userRoleMapper.deleteUserRoleInfos(roleId, userIds);
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
/*     */   public int insertAuthUsers(Long roleId, Long[] userIds) {
/* 414 */     List<SysUserRole> list = new ArrayList<>();
/* 415 */     for (Long userId : userIds) {
/*     */       
/* 417 */       SysUserRole ur = new SysUserRole();
/* 418 */       ur.setUserId(userId);
/* 419 */       ur.setRoleId(roleId);
/* 420 */       list.add(ur);
/*     */     } 
/* 422 */     return this.userRoleMapper.batchUserRole(list);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysRoleServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */