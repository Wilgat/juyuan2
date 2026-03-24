/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import cn.hutool.core.util.IdUtil;
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.TerAlert;
/*     */ import com.ruoyi.iotlighting.domain.TerAlertCount;
/*     */ import com.ruoyi.iotlighting.service.ITerAlertService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
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
/*     */ @RestController
/*     */ @RequestMapping({"/terAlert"})
/*     */ public class TerAlertController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ITerAlertService terAlertService;
/*     */   
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(TerAlert terAlert) {
/*  39 */     startPage();
/*  40 */     List<TerAlert> list = this.terAlertService.selectTerAlertList(terAlert);
/*  41 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "告警导出", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, TerAlert terAlert) {
/*  51 */     this.logger.info("告警导出请求:{}", JSON.toJSONString(terAlert));
/*  52 */     List<TerAlert> list = this.terAlertService.selectTerAlertList(terAlert);
/*  53 */     for (TerAlert alert : list) {
/*  54 */       if (StringUtils.equals(alert.getSensorType(), "park")) {
/*  55 */         if (StringUtils.equals("1", alert.getData())) {
/*  56 */           alert.setSensorType("泊车开始"); continue;
/*     */         } 
/*  58 */         alert.setSensorType("泊车结束");
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     ExcelUtil<TerAlert> util = new ExcelUtil(TerAlert.class);
/*  63 */     util.exportExcel(response, list, "告警记录");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/listToCount"})
/*     */   public AjaxResult listToCount() {
/*  72 */     TerAlertCount terAlertCount = new TerAlertCount();
/*  73 */     return success(this.terAlertService.selectTerAlertCount(terAlertCount));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody TerAlert terAlert) {
/*  83 */     terAlert.setId(Long.valueOf(IdUtil.getSnowflakeNextId()));
/*  84 */     return toAjax(this.terAlertService.insertTerAlert(terAlert));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "设备历史数据记录", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody TerAlert terAlert) {
/*  93 */     return toAjax(this.terAlertService.updateTerAlert(terAlert));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "告警处理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/deal/{id}"})
/*     */   public AjaxResult dealTerAlert(@PathVariable Long id) {
/* 102 */     return toAjax(this.terAlertService.dealTerAlert(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "告警处理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/dealBatch"})
/*     */   public AjaxResult dealTerAlertBatch(@RequestBody Long[] ids) {
/* 111 */     return toAjax(this.terAlertService.dealTerAlertBatch(ids));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerAlertController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */