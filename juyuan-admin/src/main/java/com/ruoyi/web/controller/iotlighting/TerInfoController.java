/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
/*     */ import com.ruoyi.iotlighting.domain.TerInfo;
/*     */ import com.ruoyi.iotlighting.service.ITerInfoService;
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
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/lighting/terInfo"})
/*     */ public class TerInfoController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ITerInfoService terInfoService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(TerInfo terInfo) {
/*  38 */     startPage();
/*  39 */     List<TerInfo> list = this.terInfoService.selectTerInfoList(terInfo);
/*  40 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:list')")
/*     */   @GetMapping({"/listAll"})
/*     */   public TableDataInfo listAll(TerInfo terInfo) {
/*  49 */     startPage();
/*  50 */     List<TerInfo> list = this.terInfoService.selectTerInfoListAll(terInfo);
/*  51 */     return getDataTable(list);
/*     */   }
/*     */   
/*     */   @GetMapping({"/listCount"})
/*     */   public BuildingTerStatusVo listCount() {
/*  56 */     BuildingTerStatusVo buildingTerStatus = this.terInfoService.selectTerCount();
/*  57 */     return buildingTerStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "设备信息", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, TerInfo terInfo) {
/*  66 */     List<TerInfo> list = this.terInfoService.selectTerInfoList(terInfo);
/*  67 */     ExcelUtil<TerInfo> util = new ExcelUtil(TerInfo.class);
/*  68 */     util.exportExcel(response, list, "设备信息数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/{id}"})
/*     */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/*  76 */     return success(this.terInfoService.selectTerInfoById(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:add')")
/*     */   @Log(title = "设备信息", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody TerInfo terInfo) {
/*  86 */     return toAjax(this.terInfoService.insertTerInfo(terInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:edit')")
/*     */   @Log(title = "设备信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody TerInfo terInfo) {
/*  96 */     return toAjax(this.terInfoService.updateTerInfo(terInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:edit')")
/*     */   @Log(title = "设备信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/ota/{ids}"})
/*     */   public AjaxResult ota(@PathVariable Long[] ids) {
/* 107 */     return toAjax(this.terInfoService.updateTerOtaByIds(ids));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:remove')")
/*     */   @Log(title = "设备信息", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{ids}"})
/*     */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 117 */     return toAjax(this.terInfoService.deleteTerInfoByIds(ids));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:terInfo:unbind')")
/*     */   @Log(title = "设备信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/unbind/{terSn}"})
/*     */   public AjaxResult unbind(@PathVariable String terSn) {
/* 127 */     return toAjax(this.terInfoService.unbind(terSn));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */