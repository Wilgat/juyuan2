/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.iotlighting.domain.TerAlertCount;
/*    */ import com.ruoyi.iotlighting.domain.TerAlertSystem;
/*    */ import com.ruoyi.iotlighting.service.ITerAlertSystemService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
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
/*    */ @RestController
/*    */ @RequestMapping({"/terAlertSystem"})
/*    */ public class TerAlertSystemController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ITerAlertSystemService terAlertSystemService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(TerAlertSystem terAlertSystem) {
/* 36 */     startPage();
/* 37 */     List<TerAlertSystem> list = this.terAlertSystemService.selectSystemAlertList(terAlertSystem);
/* 38 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/listToCount"})
/*    */   public AjaxResult listToCount() {
/* 46 */     TerAlertCount terAlertCount = new TerAlertCount();
/* 47 */     return success(this.terAlertSystemService.selectTerAlertSystemCount(terAlertCount));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "告警导出", businessType = BusinessType.EXPORT)
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, TerAlertSystem terAlertSystem) {
/* 56 */     this.logger.info("告警导出请求:{}", JSON.toJSONString(terAlertSystem));
/* 57 */     List<TerAlertSystem> list = this.terAlertSystemService.selectSystemAlertList(terAlertSystem);
/* 58 */     ExcelUtil<TerAlertSystem> util = new ExcelUtil(TerAlertSystem.class);
/* 59 */     util.exportExcel(response, list, "告警记录");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "告警处理", businessType = BusinessType.UPDATE)
/*    */   @PutMapping({"/deal/{id}"})
/*    */   public AjaxResult dealTerAlert(@PathVariable Long id) {
/* 68 */     return toAjax(this.terAlertSystemService.dealTerAlertSystem(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "告警处理", businessType = BusinessType.UPDATE)
/*    */   @PutMapping({"/solver/{id}"})
/*    */   public AjaxResult solverTerAlert(@PathVariable Long id) {
/* 77 */     return toAjax(this.terAlertSystemService.solverTerAlert(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "告警处理", businessType = BusinessType.UPDATE)
/*    */   @PutMapping({"/dealBatch"})
/*    */   public AjaxResult dealTerAlertBatch(@RequestBody Long[] ids) {
/* 86 */     return toAjax(this.terAlertSystemService.dealTerAlertSystemBatch(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerAlertSystemController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */