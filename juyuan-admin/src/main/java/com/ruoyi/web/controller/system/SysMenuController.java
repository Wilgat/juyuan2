/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysMenu;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.service.ISysMenuService;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/menu"})
/*     */ public class SysMenuController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysMenuService menuService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:menu:list')")
/*     */   @GetMapping({"/list"})
/*     */   public AjaxResult list(SysMenu menu) {
/*  43 */     List<SysMenu> menus = this.menuService.selectMenuList(menu, getUserId());
/*  44 */     return success(menus);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:menu:query')")
/*     */   @GetMapping({"/{menuId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long menuId) {
/*  54 */     return success(this.menuService.selectMenuById(menuId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/treeselect"})
/*     */   public AjaxResult treeselect(SysMenu menu) {
/*  63 */     List<SysMenu> menus = this.menuService.selectMenuList(menu, getUserId());
/*  64 */     return success(this.menuService.buildMenuTreeSelect(menus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/roleMenuTreeselect/{roleId}"})
/*     */   public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
/*  73 */     List<SysMenu> menus = this.menuService.selectMenuList(getUserId());
/*  74 */     AjaxResult ajax = AjaxResult.success();
/*  75 */     ajax.put("checkedKeys", this.menuService.selectMenuListByRoleId(roleId));
/*  76 */     ajax.put("menus", this.menuService.buildMenuTreeSelect(menus));
/*  77 */     return ajax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:menu:add')")
/*     */   @Log(title = "菜单管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysMenu menu) {
/*  88 */     if (!this.menuService.checkMenuNameUnique(menu))
/*     */     {
/*  90 */       return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
/*     */     }
/*  92 */     if ("0".equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
/*     */     {
/*  94 */       return error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
/*     */     }
/*  96 */     menu.setCreateBy(getUsername());
/*  97 */     return toAjax(this.menuService.insertMenu(menu));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:menu:edit')")
/*     */   @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysMenu menu) {
/* 108 */     if (!this.menuService.checkMenuNameUnique(menu))
/*     */     {
/* 110 */       return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
/*     */     }
/* 112 */     if ("0".equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
/*     */     {
/* 114 */       return error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
/*     */     }
/* 116 */     if (menu.getMenuId().equals(menu.getParentId()))
/*     */     {
/* 118 */       return error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
/*     */     }
/* 120 */     menu.setUpdateBy(getUsername());
/* 121 */     return toAjax(this.menuService.updateMenu(menu));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:menu:remove')")
/*     */   @Log(title = "菜单管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{menuId}"})
/*     */   public AjaxResult remove(@PathVariable("menuId") Long menuId) {
/* 132 */     if (this.menuService.hasChildByMenuId(menuId))
/*     */     {
/* 134 */       return warn("存在子菜单,不允许删除");
/*     */     }
/* 136 */     if (this.menuService.checkMenuExistRole(menuId))
/*     */     {
/* 138 */       return warn("菜单已分配,不允许删除");
/*     */     }
/* 140 */     return toAjax(this.menuService.deleteMenuById(menuId));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysMenuController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */