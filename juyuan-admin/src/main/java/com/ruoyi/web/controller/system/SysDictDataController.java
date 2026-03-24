/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.entity.SysDictData;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.system.service.ISysDictDataService;
/*     */ import com.ruoyi.system.service.ISysDictTypeService;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/system/dict/data"})
/*     */ public class SysDictDataController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysDictDataService dictDataService;
/*     */   @Autowired
/*     */   private ISysDictTypeService dictTypeService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysDictData dictData) {
/*  47 */     startPage();
/*  48 */     List<SysDictData> list = this.dictDataService.selectDictDataList(dictData);
/*  49 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */   
/*     */   @Log(title = "字典数据", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysDictData dictData) {
/*  57 */     List<SysDictData> list = this.dictDataService.selectDictDataList(dictData);
/*  58 */     ExcelUtil<SysDictData> util = new ExcelUtil(SysDictData.class);
/*  59 */     util.exportExcel(response, list, "字典数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:query')")
/*     */   @GetMapping({"/{dictCode}"})
/*     */   public AjaxResult getInfo(@PathVariable Long dictCode) {
/*  69 */     return success(this.dictDataService.selectDictDataById(dictCode));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/type/{dictType}"})
/*     */   public AjaxResult dictType(@PathVariable String dictType) {
/*  78 */     List<SysDictData> data = this.dictTypeService.selectDictDataByType(dictType);
/*  79 */     if (StringUtils.isNull(data))
/*     */     {
/*  81 */       data = new ArrayList<>();
/*     */     }
/*  83 */     return success(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:add')")
/*     */   @Log(title = "字典数据", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysDictData dict) {
/*  94 */     dict.setCreateBy(getUsername());
/*  95 */     return toAjax(this.dictDataService.insertDictData(dict));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:edit')")
/*     */   @Log(title = "字典数据", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
/* 106 */     dict.setUpdateBy(getUsername());
/* 107 */     return toAjax(this.dictDataService.updateDictData(dict));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
/*     */   @Log(title = "字典类型", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{dictCodes}"})
/*     */   public AjaxResult remove(@PathVariable Long[] dictCodes) {
/* 118 */     this.dictDataService.deleteDictDataByIds(dictCodes);
/* 119 */     return success();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysDictDataController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */