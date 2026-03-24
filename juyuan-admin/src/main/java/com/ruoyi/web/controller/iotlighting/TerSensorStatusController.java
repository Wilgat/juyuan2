/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.TerSensorStatus;
/*     */ import com.ruoyi.iotlighting.service.ITerSensorStatusService;
/*     */ import java.util.Date;
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
/*     */ @RequestMapping({"/terSensorStatus/status"})
/*     */ public class TerSensorStatusController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ITerSensorStatusService terSensorStatusService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(TerSensorStatus terSensorStatus) {
/*  37 */     startPage();
/*  38 */     List<TerSensorStatus> list = this.terSensorStatusService.selectTerSensorStatusList(terSensorStatus);
/*  39 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/listAlarm"})
/*     */   public List<TerSensorStatus> listAlarm(TerSensorStatus terSensorStatus) {
/*  47 */     List<TerSensorStatus> list = this.terSensorStatusService.selectTerSensorStatusListAlarm(terSensorStatus);
/*  48 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, TerSensorStatus terSensorStatus) {
/*  57 */     List<TerSensorStatus> list = this.terSensorStatusService.selectTerSensorStatusList(terSensorStatus);
/*  58 */     ExcelUtil<TerSensorStatus> util = new ExcelUtil(TerSensorStatus.class);
/*  59 */     util.exportExcel(response, list, "设备传感器配置数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @GetMapping({"/{terSn}"})
/*     */   public AjaxResult getInfo(@PathVariable("terSn") String terSn) {
/*  68 */     return success(this.terSensorStatusService.selectTerSensorStatusByTerSn(terSn));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody TerSensorStatus terSensorStatus) {
/*  78 */     return toAjax(this.terSensorStatusService.insertTerSensorStatus(terSensorStatus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody TerSensorStatus terSensorStatus) {
/*  88 */     return toAjax(this.terSensorStatusService.updateTerSensorStatus(terSensorStatus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/updateConfig"})
/*     */   public AjaxResult updateConfig(@RequestBody TerSensorStatus terSensorStatus) {
/*  98 */     return toAjax(this.terSensorStatusService.updateTerSensorConfig(terSensorStatus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/dealAlarm"})
/*     */   public AjaxResult dealAlarm(@RequestBody TerSensorStatus terSensorStatus) {
/* 107 */     terSensorStatus.setUpdatedTime(new Date());
/* 108 */     return toAjax(this.terSensorStatusService.updateTerSensorStatus(terSensorStatus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('terSensorStatus:status:list')")
/*     */   @Log(title = "设备传感器状态", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{terSns}"})
/*     */   public AjaxResult remove(@PathVariable String[] terSns) {
/* 118 */     return toAjax(this.terSensorStatusService.deleteTerSensorStatusByTerSns(terSns));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerSensorStatusController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */