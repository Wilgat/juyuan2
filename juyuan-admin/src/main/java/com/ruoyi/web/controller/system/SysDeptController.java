/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysDept;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.service.ISysDeptService;
/*     */ import java.util.List;
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
/*     */ @RequestMapping({"/system/dept"})
/*     */ public class SysDeptController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysDeptService deptService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:list')")
/*     */   @GetMapping({"/list"})
/*     */   public AjaxResult list(SysDept dept) {
/*  44 */     List<SysDept> depts = this.deptService.selectDeptList(dept);
/*  45 */     return success(depts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:list')")
/*     */   @GetMapping({"/list/exclude/{deptId}"})
/*     */   public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
/*  55 */     List<SysDept> depts = this.deptService.selectDeptList(new SysDept());
/*  56 */     depts.removeIf(d -> (d.getDeptId().intValue() == deptId.longValue() || ArrayUtils.contains((Object[])StringUtils.split(d.getAncestors(), ","), deptId + "")));
/*  57 */     return success(depts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:query')")
/*     */   @GetMapping({"/{deptId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long deptId) {
/*  67 */     this.deptService.checkDeptDataScope(deptId);
/*  68 */     return success(this.deptService.selectDeptById(deptId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:add')")
/*     */   @Log(title = "部门管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysDept dept) {
/*  79 */     if (!this.deptService.checkDeptNameUnique(dept))
/*     */     {
/*  81 */       return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
/*     */     }
/*  83 */     dept.setCreateBy(getUsername());
/*  84 */     return toAjax(this.deptService.insertDept(dept));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:edit')")
/*     */   @Log(title = "部门管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysDept dept) {
/*  95 */     Long deptId = dept.getDeptId();
/*  96 */     this.deptService.checkDeptDataScope(deptId);
/*  97 */     if (!this.deptService.checkDeptNameUnique(dept))
/*     */     {
/*  99 */       return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
/*     */     }
/* 101 */     if (dept.getParentId().equals(deptId))
/*     */     {
/* 103 */       return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
/*     */     }
/* 105 */     if (StringUtils.equals("1", dept.getStatus()) && this.deptService.selectNormalChildrenDeptById(deptId) > 0)
/*     */     {
/* 107 */       return error("该部门包含未停用的子部门！");
/*     */     }
/* 109 */     dept.setUpdateBy(getUsername());
/* 110 */     return toAjax(this.deptService.updateDept(dept));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dept:remove')")
/*     */   @Log(title = "部门管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{deptId}"})
/*     */   public AjaxResult remove(@PathVariable Long deptId) {
/* 121 */     if (this.deptService.hasChildByDeptId(deptId))
/*     */     {
/* 123 */       return warn("存在下级部门,不允许删除");
/*     */     }
/* 125 */     if (this.deptService.checkDeptExistUser(deptId))
/*     */     {
/* 127 */       return warn("部门存在用户,不允许删除");
/*     */     }
/* 129 */     this.deptService.checkDeptDataScope(deptId);
/* 130 */     return toAjax(this.deptService.deleteDeptById(deptId));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysDeptController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */