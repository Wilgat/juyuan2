/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.TerGateway;
/*     */ import com.ruoyi.iotlighting.service.ITerGatewayService;
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
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/lighting/gateway"})
/*     */ public class TerGatewayController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ITerGatewayService terGatewayService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:gateway:list')")
/*     */   @GetMapping({"/listPage"})
/*     */   public TableDataInfo listPage(TerGateway terGateway) {
/*  39 */     startPage();
/*  40 */     List<TerGateway> list = this.terGatewayService.selectTerGatewayList(terGateway);
/*  41 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/list"})
/*     */   public AjaxResult list(TerGateway terGateway) {
/*  49 */     List<TerGateway> list = this.terGatewayService.selectTerGatewayList(terGateway);
/*  50 */     return success(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:gateway:export')")
/*     */   @Log(title = "网关信息", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, TerGateway terGateway) {
/*  60 */     List<TerGateway> list = this.terGatewayService.selectTerGatewayList(terGateway);
/*  61 */     ExcelUtil<TerGateway> util = new ExcelUtil(TerGateway.class);
/*  62 */     util.exportExcel(response, list, "网关信息数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/{id}"})
/*     */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/*  70 */     return success(this.terGatewayService.selectTerGatewayById(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:gateway:add')")
/*     */   @Log(title = "网关信息", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody TerGateway terGateway) {
/*  80 */     this.terGatewayService.checkTerGateway(terGateway);
/*  81 */     terGateway.setUpdatedBy(SecurityUtils.getUsername());
/*  82 */     terGateway.setUpdatedTime(new Date());
/*  83 */     return toAjax(this.terGatewayService.insertTerGateway(terGateway));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:gateway:edit')")
/*     */   @Log(title = "网关信息", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody TerGateway terGateway) {
/*  93 */     this.terGatewayService.checkTerGateway(terGateway);
/*  94 */     terGateway.setUpdatedBy(SecurityUtils.getUsername());
/*  95 */     terGateway.setUpdatedTime(new Date());
/*  96 */     return toAjax(this.terGatewayService.updateTerGateway(terGateway));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('lighting:gateway:remove')")
/*     */   @Log(title = "网关信息", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{ids}"})
/*     */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 106 */     return toAjax(this.terGatewayService.deleteTerGatewayByIds(ids));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerGatewayController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */