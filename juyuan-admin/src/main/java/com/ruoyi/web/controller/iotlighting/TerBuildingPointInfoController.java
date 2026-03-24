/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.iotlighting.domain.TerBuildingPointInfo;
/*    */ import com.ruoyi.iotlighting.service.ITerBuildingPointInfoService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
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
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/terBuildingPointInfo"})
/*    */ public class TerBuildingPointInfoController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ITerBuildingPointInfoService terBuildingPointInfoService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(TerBuildingPointInfo terBuildingPointInfo) {
/* 36 */     startPage();
/* 37 */     List<TerBuildingPointInfo> list = this.terBuildingPointInfoService.selectTerBuildingPointInfoList(terBuildingPointInfo);
/* 38 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "设备点位关系", businessType = BusinessType.EXPORT)
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, TerBuildingPointInfo terBuildingPointInfo) {
/* 47 */     List<TerBuildingPointInfo> list = this.terBuildingPointInfoService.selectTerBuildingPointInfoList(terBuildingPointInfo);
/* 48 */     ExcelUtil<TerBuildingPointInfo> util = new ExcelUtil(TerBuildingPointInfo.class);
/* 49 */     util.exportExcel(response, list, "设备点位关系数据");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/{terSn}"})
/*    */   public AjaxResult getInfo(@PathVariable("terSn") String terSn) {
/* 57 */     return success(this.terBuildingPointInfoService.selectTerBuildingPointInfoByTerSn(terSn));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "设备点位关系", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody TerBuildingPointInfo terBuildingPointInfo) {
/* 66 */     String sn = this.terBuildingPointInfoService.getTerInfoBySnOrUpc(terBuildingPointInfo.getTerSn());
/* 67 */     terBuildingPointInfo.setTerSn(sn);
/* 68 */     AjaxResult checkResult = this.terBuildingPointInfoService.checkPointAndTer(terBuildingPointInfo);
/* 69 */     if (checkResult.isError()) {
/* 70 */       return checkResult;
/*    */     }
/* 72 */     return toAjax(this.terBuildingPointInfoService.insertTerBuildingPointInfo(terBuildingPointInfo));
/*    */   }
/*    */   
/*    */   @Log(title = "灯管安装确认")
/*    */   @GetMapping({"/install/{terSn}"})
/*    */   public AjaxResult installConfirm(@PathVariable("terSn") String terSn) {
/* 78 */     terSn = this.terBuildingPointInfoService.getTerInfoBySnOrUpc(terSn);
/* 79 */     this.terBuildingPointInfoService.installConfirm(terSn);
/* 80 */     return AjaxResult.success();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "设备点位关系", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody TerBuildingPointInfo terBuildingPointInfo) {
/* 89 */     return toAjax(this.terBuildingPointInfoService.updateTerBuildingPointInfo(terBuildingPointInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "设备点位关系", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{terSn}"})
/*    */   public AjaxResult remove(@PathVariable String terSn) {
/* 98 */     return toAjax(this.terBuildingPointInfoService.deleteTerBuildingPointInfoByTerSn(terSn));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/TerBuildingPointInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */