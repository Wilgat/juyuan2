/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.TerDataLog;
/*     */ import com.ruoyi.iotlighting.service.ITerDataLogService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
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
/*     */ @RequestMapping({"/terDataLong/log"})
/*     */ public class TerDataLogController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ITerDataLogService terDataLogService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(TerDataLog terDataLog) {
/*  36 */     startPage();
/*  37 */     List<TerDataLog> list = this.terDataLogService.selectTerDataLogList(terDataLog);
/*  38 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:list')")
/*     */   @GetMapping({"/listAlert"})
/*     */   public TableDataInfo listAlert(TerDataLog terDataLog) {
/*  47 */     startPage();
/*  48 */     List<TerDataLog> list = this.terDataLogService.selectTerAlertLogList(terDataLog);
/*  49 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:export')")
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, TerDataLog terDataLog) {
/*  59 */     List<TerDataLog> list = this.terDataLogService.selectTerDataLogList(terDataLog);
/*  60 */     ExcelUtil<TerDataLog> util = new ExcelUtil(TerDataLog.class);
/*  61 */     util.exportExcel(response, list, "设备历史数据记录数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:query')")
/*     */   @GetMapping({"/{id}"})
/*     */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/*  70 */     return success(this.terDataLogService.selectTerDataLogById(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:add')")
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody TerDataLog terDataLog) {
/*  80 */     return toAjax(this.terDataLogService.insertTerDataLog(terDataLog));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:edit')")
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody TerDataLog terDataLog) {
/*  90 */     return toAjax(this.terDataLogService.updateTerDataLog(terDataLog));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terDataLong:log:remove')")
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{ids}"})
/*     */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 100 */     return toAjax(this.terDataLogService.deleteTerDataLogByIds(ids));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerDataLogController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */