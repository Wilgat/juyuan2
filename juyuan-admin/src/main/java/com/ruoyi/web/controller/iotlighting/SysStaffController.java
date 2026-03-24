/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.entity.SysUser;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.MessageUtils;
/*    */ import com.ruoyi.iotlighting.domain.SysStaff;
/*    */ import com.ruoyi.iotlighting.service.ISysStaffService;
/*    */ import com.ruoyi.system.service.ISysUserService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PutMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/sysStaffInfo"})
/*    */ public class SysStaffController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ISysStaffService sysStaffService;
/*    */   @Autowired
/*    */   private ISysUserService sysUserService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(SysStaff sysStaff) {
/* 39 */     startPage();
/* 40 */     List<SysStaff> list = this.sysStaffService.selectSysStaffList(sysStaff);
/* 41 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:sysStaffInfo:query')")
/*    */   @GetMapping({"/{userId}"})
/*    */   public AjaxResult getInfo(@PathVariable("userId") Long userId) {
/* 51 */     return success(this.sysStaffService.selectSysStaffByUserId(userId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:sysStaffInfo:edit')")
/*    */   @Log(title = "员工", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody SysStaff sysStaff) {
/* 62 */     SysUser sysUser = new SysUser();
/* 63 */     sysUser.setUserId(sysStaff.getUserId());
/* 64 */     sysUser.setNickName(sysStaff.getNickName());
/* 65 */     sysUser.setUserName(sysStaff.getUserName());
/* 66 */     if (this.sysStaffService.updateSysStaff(sysStaff) > 0) {
/* 67 */       return toAjax(this.sysUserService.updateUserProfile(sysUser));
/*    */     }
/* 69 */     return error(MessageUtils.message("sysStaff.edit.error", new Object[0]));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/SysStaffController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */