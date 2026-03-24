/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysDept;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.system.service.ISysDeptService;
/*     */ import com.ruoyi.system.service.ISysPostService;
/*     */ import com.ruoyi.system.service.ISysRoleService;
/*     */ import com.ruoyi.system.service.ISysUserService;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang3.ArrayUtils;
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
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/user"})
/*     */ public class SysUserController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysUserService userService;
/*     */   @Autowired
/*     */   private ISysRoleService roleService;
/*     */   @Autowired
/*     */   private ISysDeptService deptService;
/*     */   @Autowired
/*     */   private ISysPostService postService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysUser user) {
/*  55 */     startPage();
/*  56 */     List<SysUser> list = this.userService.selectUserList(user);
/*  57 */     return getDataTable(list);
/*     */   }
/*     */   
/*     */   @Log(title = "用户管理", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:user:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysUser user) {
/*  64 */     List<SysUser> list = this.userService.selectUserList(user);
/*  65 */     ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
/*  66 */     util.exportExcel(response, list, "用户数据");
/*     */   }
/*     */   
/*     */   @Log(title = "用户管理", businessType = BusinessType.IMPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:user:import')")
/*     */   @PostMapping({"/importData"})
/*     */   public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
/*  73 */     ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
/*  74 */     List<SysUser> userList = util.importExcel(file.getInputStream());
/*  75 */     String operName = getUsername();
/*  76 */     String message = this.userService.importUser(userList, Boolean.valueOf(updateSupport), operName);
/*  77 */     return success(message);
/*     */   }
/*     */   
/*     */   @PostMapping({"/importTemplate"})
/*     */   public void importTemplate(HttpServletResponse response) {
/*  82 */     ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
/*  83 */     util.importTemplateExcel(response, "用户数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:query')")
/*     */   @GetMapping({"/", "/{userId}"})
/*     */   public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
/*  92 */     this.userService.checkUserDataScope(userId);
/*  93 */     AjaxResult ajax = AjaxResult.success();
/*  94 */     List<SysRole> roles = this.roleService.selectRoleAll();
/*  95 */     ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
/*  96 */     ajax.put("posts", this.postService.selectPostAll());
/*  97 */     if (StringUtils.isNotNull(userId)) {
/*  98 */       SysUser sysUser = this.userService.selectUserById(userId);
/*  99 */       ajax.put("data", sysUser);
/* 100 */       ajax.put("postIds", this.postService.selectPostListByUserId(userId));
/* 101 */       ajax.put("roleIds", sysUser.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
/*     */     } 
/* 103 */     return ajax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:add')")
/*     */   @Log(title = "用户管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysUser user) {
/* 113 */     if (!this.userService.checkUserNameUnique(user))
/* 114 */       return error("新增用户'" + user.getUserName() + "'失败，登录账号已存在"); 
/* 115 */     if (StringUtils.isNotEmpty(user.getPhonenumber()) && !this.userService.checkPhoneUnique(user))
/* 116 */       return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在"); 
/* 117 */     if (StringUtils.isNotEmpty(user.getEmail()) && !this.userService.checkEmailUnique(user)) {
/* 118 */       return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
/*     */     }
/* 120 */     user.setCreateBy(getUsername());
/* 121 */     user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
/* 122 */     return toAjax(this.userService.insertUser(user));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:edit')")
/*     */   @Log(title = "用户管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysUser user) {
/* 132 */     this.userService.checkUserAllowed(user);
/* 133 */     this.userService.checkUserDataScope(user.getUserId());
/* 134 */     if (!this.userService.checkUserNameUnique(user))
/* 135 */       return error("修改用户'" + user.getUserName() + "'失败，登录账号已存在"); 
/* 136 */     if (StringUtils.isNotEmpty(user.getPhonenumber()) && !this.userService.checkPhoneUnique(user))
/* 137 */       return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在"); 
/* 138 */     if (StringUtils.isNotEmpty(user.getEmail()) && !this.userService.checkEmailUnique(user)) {
/* 139 */       return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
/*     */     }
/* 141 */     user.setUpdateBy(getUsername());
/* 142 */     return toAjax(this.userService.updateUser(user));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:remove')")
/*     */   @Log(title = "用户管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{userIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] userIds) {
/* 152 */     if (ArrayUtils.contains((Object[])userIds, getUserId())) {
/* 153 */       return error("当前用户不能删除");
/*     */     }
/* 155 */     return toAjax(this.userService.deleteUserByIds(userIds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "用户管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/resetPwd"})
/*     */   public AjaxResult resetPwd(@RequestBody SysUser user) {
/* 164 */     this.userService.checkUserAllowed(user);
/* 165 */     this.userService.checkUserDataScope(user.getUserId());
/* 166 */     user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
/* 167 */     user.setUpdateBy(getUsername());
/* 168 */     return toAjax(this.userService.resetPwd(user));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:edit')")
/*     */   @Log(title = "用户管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/changeStatus"})
/*     */   public AjaxResult changeStatus(@RequestBody SysUser user) {
/* 178 */     this.userService.checkUserAllowed(user);
/* 179 */     this.userService.checkUserDataScope(user.getUserId());
/* 180 */     user.setUpdateBy(getUsername());
/* 181 */     return toAjax(this.userService.updateUserStatus(user));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:query')")
/*     */   @GetMapping({"/authRole/{userId}"})
/*     */   public AjaxResult authRole(@PathVariable("userId") Long userId) {
/* 190 */     AjaxResult ajax = AjaxResult.success();
/* 191 */     SysUser user = this.userService.selectUserById(userId);
/* 192 */     List<SysRole> roles = this.roleService.selectRolesByUserId(userId);
/* 193 */     ajax.put("user", user);
/* 194 */     ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
/* 195 */     return ajax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:user:edit')")
/*     */   @Log(title = "用户管理", businessType = BusinessType.GRANT)
/*     */   @PutMapping({"/authRole"})
/*     */   public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
/* 205 */     this.userService.checkUserDataScope(userId);
/* 206 */     this.userService.insertUserAuth(userId, roleIds);
/* 207 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/deptTree"})
/*     */   public AjaxResult deptTree(SysDept dept) {
/* 215 */     return success(this.deptService.selectDeptTreeList(dept));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysUserController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */