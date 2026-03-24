/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysDictType;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.system.service.ISysDictTypeService;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/dict/type"})
/*     */ public class SysDictTypeController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysDictTypeService dictTypeService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysDictType dictType) {
/*  41 */     startPage();
/*  42 */     List<SysDictType> list = this.dictTypeService.selectDictTypeList(dictType);
/*  43 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */   
/*     */   @Log(title = "字典类型", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysDictType dictType) {
/*  51 */     List<SysDictType> list = this.dictTypeService.selectDictTypeList(dictType);
/*  52 */     ExcelUtil<SysDictType> util = new ExcelUtil(SysDictType.class);
/*  53 */     util.exportExcel(response, list, "字典类型");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:query')")
/*     */   @GetMapping({"/{dictId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long dictId) {
/*  63 */     return success(this.dictTypeService.selectDictTypeById(dictId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:add')")
/*     */   @Log(title = "字典类型", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysDictType dict) {
/*  74 */     if (!this.dictTypeService.checkDictTypeUnique(dict))
/*     */     {
/*  76 */       return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
/*     */     }
/*  78 */     dict.setCreateBy(getUsername());
/*  79 */     return toAjax(this.dictTypeService.insertDictType(dict));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:edit')")
/*     */   @Log(title = "字典类型", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
/*  90 */     if (!this.dictTypeService.checkDictTypeUnique(dict))
/*     */     {
/*  92 */       return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
/*     */     }
/*  94 */     dict.setUpdateBy(getUsername());
/*  95 */     return toAjax(this.dictTypeService.updateDictType(dict));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
/*     */   @Log(title = "字典类型", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{dictIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] dictIds) {
/* 106 */     this.dictTypeService.deleteDictTypeByIds(dictIds);
/* 107 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
/*     */   @Log(title = "字典类型", businessType = BusinessType.CLEAN)
/*     */   @DeleteMapping({"/refreshCache"})
/*     */   public AjaxResult refreshCache() {
/* 118 */     this.dictTypeService.resetDictCache();
/* 119 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/optionselect"})
/*     */   public AjaxResult optionselect() {
/* 128 */     List<SysDictType> dictTypes = this.dictTypeService.selectDictTypeAll();
/* 129 */     return success(dictTypes);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysDictTypeController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */