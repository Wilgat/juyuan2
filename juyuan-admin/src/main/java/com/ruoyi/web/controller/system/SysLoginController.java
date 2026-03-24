/*    */ package com.ruoyi.web.controller.system;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSONObject;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.entity.SysMenu;
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.common.core.domain.model.LoginBody;
/*    */ import com.ruoyi.common.utils.SecurityUtils;
/*    */ import com.ruoyi.framework.web.service.SysLoginService;
/*    */ import com.ruoyi.framework.web.service.SysPermissionService;
/*    */ import com.ruoyi.system.service.ISysMenuService;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ public class SysLoginController
/*    */ {
/* 29 */   private static final Logger log = LoggerFactory.getLogger(SysLoginController.class);
/*    */ 
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private SysLoginService loginService;
/*    */ 
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private ISysMenuService menuService;
/*    */ 
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private SysPermissionService permissionService;
/*    */ 
/*    */ 
/*    */   
/*    */   @PostMapping({"/login"})
/*    */   public AjaxResult login(@RequestBody LoginBody loginBody) {
/* 50 */     log.info("登入请求,request param:{}", JSONObject.toJSONString(loginBody, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/* 51 */     AjaxResult ajax = AjaxResult.success();
/*    */     
/* 53 */     String token = this.loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody
/* 54 */         .getUuid(), loginBody.getCid());
/* 55 */     ajax.put("token", token);
/* 56 */     return ajax;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"getInfo"})
/*    */   public AjaxResult getInfo() {
/* 67 */     SysUser user = SecurityUtils.getLoginUser().getUser();
/*    */     
/* 69 */     Set<String> roles = this.permissionService.getRolePermission(user);
/*    */     
/* 71 */     Set<String> permissions = this.permissionService.getMenuPermission(user);
/* 72 */     AjaxResult ajax = AjaxResult.success();
/* 73 */     ajax.put("user", user);
/* 74 */     ajax.put("roles", roles);
/* 75 */     ajax.put("permissions", permissions);
/* 76 */     return ajax;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"getRouters"})
/*    */   public AjaxResult getRouters() {
/* 87 */     Long userId = SecurityUtils.getUserId();
/* 88 */     List<SysMenu> menus = this.menuService.selectMenuTreeByUserId(userId);
/* 89 */     return AjaxResult.success(this.menuService.buildMenus(menus));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysLoginController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */