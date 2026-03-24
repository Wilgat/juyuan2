/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysDept;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.framework.web.service.SysPermissionService;
/*     */ import com.ruoyi.framework.web.service.TokenService;
/*     */ import com.ruoyi.system.domain.SysUserRole;
/*     */ import com.ruoyi.system.service.ISysDeptService;
/*     */ import com.ruoyi.system.service.ISysRoleService;
/*     */ import com.ruoyi.system.service.ISysUserService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
/*     */ import org.springframework.validation.annotation.Validated;
/*     */ import org.springframework.web.bind.annotation.DeleteMapping;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.PutMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/role"})
/*     */ public class SysRoleController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysRoleService roleService;
/*     */   @Autowired
/*     */   private TokenService tokenService;
/*     */   @Autowired
/*     */   private SysPermissionService permissionService;
/*     */   @Autowired
/*     */   private ISysUserService userService;
/*     */   @Autowired
/*     */   private ISysDeptService deptService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysRole role) {
/*  54 */     startPage();
/*  55 */     List<SysRole> list = this.roleService.selectRoleList(role);
/*  56 */     return getDataTable(list);
/*     */   }
/*     */   
/*     */   @Log(title = "角色管理", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:role:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysRole role) {
/*  63 */     List<SysRole> list = this.roleService.selectRoleList(role);
/*  64 */     ExcelUtil<SysRole> util = new ExcelUtil(SysRole.class);
/*  65 */     util.exportExcel(response, list, "角色数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:query')")
/*     */   @GetMapping({"/{roleId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long roleId) {
/*  74 */     this.roleService.checkRoleDataScope(roleId);
/*  75 */     return success(this.roleService.selectRoleById(roleId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:add')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysRole role) {
/*  85 */     if (!this.roleService.checkRoleNameUnique(role))
/*  86 */       return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在"); 
/*  87 */     if (!this.roleService.checkRoleKeyUnique(role)) {
/*  88 */       return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
/*     */     }
/*  90 */     role.setCreateBy(getUsername());
/*  91 */     return toAjax(this.roleService.insertRole(role));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysRole role) {
/* 102 */     this.roleService.checkRoleAllowed(role);
/* 103 */     this.roleService.checkRoleDataScope(role.getRoleId());
/* 104 */     if (!this.roleService.checkRoleNameUnique(role))
/* 105 */       return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在"); 
/* 106 */     if (!this.roleService.checkRoleKeyUnique(role)) {
/* 107 */       return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
/*     */     }
/* 109 */     role.setUpdateBy(getUsername());
/*     */     
/* 111 */     if (this.roleService.updateRole(role) > 0) {
/*     */       
/* 113 */       LoginUser loginUser = getLoginUser();
/* 114 */       if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
/* 115 */         loginUser.setPermissions(this.permissionService.getMenuPermission(loginUser.getUser()));
/* 116 */         loginUser.setUser(this.userService.selectUserByUserName(loginUser.getUser().getUserName()));
/* 117 */         this.tokenService.setLoginUser(loginUser);
/*     */       } 
/* 119 */       return success();
/*     */     } 
/* 121 */     return error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/dataScope"})
/*     */   public AjaxResult dataScope(@RequestBody SysRole role) {
/* 131 */     this.roleService.checkRoleAllowed(role);
/* 132 */     this.roleService.checkRoleDataScope(role.getRoleId());
/* 133 */     return toAjax(this.roleService.authDataScope(role));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/changeStatus"})
/*     */   public AjaxResult changeStatus(@RequestBody SysRole role) {
/* 143 */     this.roleService.checkRoleAllowed(role);
/* 144 */     this.roleService.checkRoleDataScope(role.getRoleId());
/* 145 */     role.setUpdateBy(getUsername());
/* 146 */     return toAjax(this.roleService.updateRoleStatus(role));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:remove')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{roleIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] roleIds) {
/* 156 */     return toAjax(this.roleService.deleteRoleByIds(roleIds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/optionselect"})
/*     */   public AjaxResult optionselect() {
/* 164 */     return success(this.roleService.selectRoleAll());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:list')")
/*     */   @GetMapping({"/authUser/allocatedList"})
/*     */   public TableDataInfo allocatedList(SysUser user) {
/* 173 */     startPage();
/* 174 */     List<SysUser> list = this.userService.selectAllocatedList(user);
/* 175 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:list')")
/*     */   @GetMapping({"/authUser/unallocatedList"})
/*     */   public TableDataInfo unallocatedList(SysUser user) {
/* 184 */     startPage();
/* 185 */     List<SysUser> list = this.userService.selectUnallocatedList(user);
/* 186 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.GRANT)
/*     */   @PutMapping({"/authUser/cancel"})
/*     */   public AjaxResult cancelAuthUser(@RequestBody SysUserRole userRole) {
/* 196 */     return toAjax(this.roleService.deleteAuthUser(userRole));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.GRANT)
/*     */   @PutMapping({"/authUser/cancelAll"})
/*     */   public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
/* 206 */     return toAjax(this.roleService.deleteAuthUsers(roleId, userIds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:edit')")
/*     */   @Log(title = "角色管理", businessType = BusinessType.GRANT)
/*     */   @PutMapping({"/authUser/selectAll"})
/*     */   public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {
/* 216 */     this.roleService.checkRoleDataScope(roleId);
/* 217 */     return toAjax(this.roleService.insertAuthUsers(roleId, userIds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:role:query')")
/*     */   @GetMapping({"/deptTree/{roleId}"})
/*     */   public AjaxResult deptTree(@PathVariable("roleId") Long roleId) {
/* 226 */     AjaxResult ajax = AjaxResult.success();
/* 227 */     ajax.put("checkedKeys", this.deptService.selectDeptListByRoleId(roleId));
/* 228 */     ajax.put("depts", this.deptService.selectDeptTreeList(new SysDept()));
/* 229 */     return ajax;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysRoleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */