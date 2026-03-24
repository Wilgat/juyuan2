/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.file.FileUploadUtils;
/*     */ import com.ruoyi.common.utils.file.MimeTypeUtils;
/*     */ import com.ruoyi.framework.web.service.TokenService;
/*     */ import com.ruoyi.system.service.ISysUserService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.PutMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/user/profile"})
/*     */ public class SysProfileController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysUserService userService;
/*     */   @Autowired
/*     */   private TokenService tokenService;
/*     */   
/*     */   @GetMapping
/*     */   public AjaxResult profile() {
/*  39 */     LoginUser loginUser = getLoginUser();
/*  40 */     SysUser user = loginUser.getUser();
/*  41 */     AjaxResult ajax = AjaxResult.success(user);
/*  42 */     ajax.put("roleGroup", this.userService.selectUserRoleGroup(loginUser.getUsername()));
/*  43 */     ajax.put("postGroup", this.userService.selectUserPostGroup(loginUser.getUsername()));
/*  44 */     return ajax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "个人信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult updateProfile(@RequestBody SysUser user) {
/*  53 */     LoginUser loginUser = getLoginUser();
/*  54 */     SysUser currentUser = loginUser.getUser();
/*  55 */     currentUser.setNickName(user.getNickName());
/*  56 */     currentUser.setEmail(user.getEmail());
/*  57 */     currentUser.setPhonenumber(user.getPhonenumber());
/*  58 */     currentUser.setSex(user.getSex());
/*  59 */     if (StringUtils.isNotEmpty(user.getPhonenumber()) && !this.userService.checkPhoneUnique(currentUser)) {
/*  60 */       return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
/*     */     }
/*  62 */     if (StringUtils.isNotEmpty(user.getEmail()) && !this.userService.checkEmailUnique(currentUser)) {
/*  63 */       return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
/*     */     }
/*  65 */     if (this.userService.updateUserProfile(currentUser) > 0) {
/*     */       
/*  67 */       this.tokenService.setLoginUser(loginUser);
/*  68 */       return success();
/*     */     } 
/*  70 */     return error("修改个人信息异常，请联系管理员");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "个人信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/updatePwd"})
/*     */   public AjaxResult updatePwd(String oldPassword, String newPassword) {
/*  79 */     LoginUser loginUser = getLoginUser();
/*  80 */     String userName = loginUser.getUsername();
/*  81 */     String password = loginUser.getPassword();
/*  82 */     if (!SecurityUtils.matchesPassword(oldPassword, password)) {
/*  83 */       return error("修改密码失败，旧密码错误");
/*     */     }
/*  85 */     if (SecurityUtils.matchesPassword(newPassword, password)) {
/*  86 */       return error("新密码不能与旧密码相同");
/*     */     }
/*  88 */     if (this.userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
/*     */       
/*  90 */       loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
/*  91 */       this.tokenService.delLoginUser(loginUser.getToken());
/*  92 */       return success();
/*     */     } 
/*  94 */     return error("修改密码异常，请联系管理员");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "用户头像", businessType = BusinessType.UPDATE)
/*     */   @PostMapping({"/avatar"})
/*     */   public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
/* 103 */     if (!file.isEmpty()) {
/* 104 */       LoginUser loginUser = getLoginUser();
/* 105 */       String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
/* 106 */       if (this.userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
/* 107 */         AjaxResult ajax = AjaxResult.success();
/* 108 */         ajax.put("imgUrl", avatar);
/*     */         
/* 110 */         loginUser.getUser().setAvatar(avatar);
/* 111 */         this.tokenService.setLoginUser(loginUser);
/* 112 */         return ajax;
/*     */       } 
/*     */     } 
/* 115 */     return error("上传图片异常，请联系管理员");
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysProfileController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */