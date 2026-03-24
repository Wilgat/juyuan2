/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.BuildingZoneInfo;
/*     */ import com.ruoyi.iotlighting.service.IBuildingZoneInfoService;
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
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/lighting/zoneInfo"})
/*     */ public class BuildingZoneInfoController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private IBuildingZoneInfoService buildingZoneInfoService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(BuildingZoneInfo buildingZoneInfo) {
/*  37 */     startPage();
/*  38 */     List<BuildingZoneInfo> list = this.buildingZoneInfoService.selectBuildingZoneInfoList(buildingZoneInfo);
/*  39 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @Log(title = "建筑区域信息", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, BuildingZoneInfo buildingZoneInfo) {
/*  49 */     List<BuildingZoneInfo> list = this.buildingZoneInfoService.selectBuildingZoneInfoList(buildingZoneInfo);
/*  50 */     ExcelUtil<BuildingZoneInfo> util = new ExcelUtil(BuildingZoneInfo.class);
/*  51 */     util.exportExcel(response, list, "建筑区域信息数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/importTemplate"})
/*     */   public void exportTemplate(HttpServletResponse response) {
/*  59 */     ExcelUtil<BuildingZoneInfo> util = new ExcelUtil(BuildingZoneInfo.class);
/*  60 */     util.importTemplateExcel(response, "区域数据");
/*     */   }
/*     */   
/*     */   @Log(title = "区域导入", businessType = BusinessType.IMPORT)
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @PostMapping({"/importData"})
/*     */   public AjaxResult importData(MultipartFile file) throws Exception {
/*  67 */     ExcelUtil<BuildingZoneInfo> util = new ExcelUtil(BuildingZoneInfo.class);
/*  68 */     List<BuildingZoneInfo> buildingZoneInfoList = util.importExcel(file.getInputStream());
/*  69 */     String message = this.buildingZoneInfoService.importZoneInfo(buildingZoneInfoList);
/*  70 */     return success(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @GetMapping({"/{id}"})
/*     */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/*  80 */     return success(this.buildingZoneInfoService.selectBuildingZoneInfoById(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @Log(title = "建筑区域信息", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody BuildingZoneInfo buildingZoneInfo) {
/*  90 */     return toAjax(this.buildingZoneInfoService.insertBuildingZoneInfo(buildingZoneInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @Log(title = "建筑区域信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody BuildingZoneInfo buildingZoneInfo) {
/* 100 */     return toAjax(this.buildingZoneInfoService.updateBuildingZoneInfo(buildingZoneInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:buildingInfo:zone')")
/*     */   @Log(title = "建筑区域信息", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{ids}"})
/*     */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 110 */     return toAjax(this.buildingZoneInfoService.deleteBuildingZoneInfoByIds(ids));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingZoneInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */