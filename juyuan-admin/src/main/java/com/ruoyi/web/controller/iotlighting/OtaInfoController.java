/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.iotlighting.domain.OtaInfo;
/*    */ import com.ruoyi.iotlighting.service.IOtaInfoService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.DeleteMapping;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.PutMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/ota"})
/*    */ public class OtaInfoController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IOtaInfoService otaInfoService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:query')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(OtaInfo otaInfo) {
/* 43 */     startPage();
/* 44 */     List<OtaInfo> list = this.otaInfoService.selectOtaInfoList(otaInfo);
/* 45 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:export')")
/*    */   @Log(title = "固件版本", businessType = BusinessType.EXPORT)
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, OtaInfo otaInfo) {
/* 55 */     List<OtaInfo> list = this.otaInfoService.selectOtaInfoList(otaInfo);
/* 56 */     ExcelUtil<OtaInfo> util = new ExcelUtil(OtaInfo.class);
/* 57 */     util.exportExcel(response, list, "固件版本数据");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:query')")
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 66 */     return success(this.otaInfoService.selectOtaInfoById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:query')")
/*    */   @Log(title = "固件版本", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody OtaInfo otaInfo) {
/* 76 */     return toAjax(this.otaInfoService.insertOtaInfo(otaInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:query')")
/*    */   @Log(title = "固件版本", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody OtaInfo otaInfo) {
/* 86 */     return toAjax(this.otaInfoService.updateOtaInfo(otaInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:ota:query')")
/*    */   @Log(title = "固件版本", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{ids}"})
/*    */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 96 */     return toAjax(this.otaInfoService.deleteOtaInfoByIds(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/OtaInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */