/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.iotlighting.domain.BuildingLifeguard;
/*    */ import com.ruoyi.iotlighting.service.IBuildingLifeguardService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PutMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/lifeguard"})
/*    */ public class BuildingLifeguardController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingLifeguardService buildingLifeguardService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:lifeguard:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(BuildingLifeguard buildingLifeguard) {
/* 34 */     startPage();
/* 35 */     List<BuildingLifeguard> list = this.buildingLifeguardService.selectBuildingLifeguardList(buildingLifeguard);
/* 36 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:lifeguard:query')")
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 46 */     return success(this.buildingLifeguardService.selectBuildingLifeguardById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:lifeguard:edit')")
/*    */   @Log(title = "救生员", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody List<BuildingLifeguard> buildingLifeguardList) {
/* 56 */     return toAjax(this.buildingLifeguardService.updateBuildingLifeguard(buildingLifeguardList));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingLifeguardController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */