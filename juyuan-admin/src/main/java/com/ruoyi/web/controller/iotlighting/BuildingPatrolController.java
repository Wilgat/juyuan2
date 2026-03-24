/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.iotlighting.domain.BuildingPatrol;
/*    */ import com.ruoyi.iotlighting.service.IBuildingPatrolService;
/*    */ import java.util.List;
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
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/patrol"})
/*    */ public class BuildingPatrolController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingPatrolService buildingPatrolService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(BuildingPatrol buildingPatrol) {
/* 32 */     startPage();
/* 33 */     List<BuildingPatrol> list = this.buildingPatrolService.selectBuildingPatrolList(buildingPatrol);
/* 34 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 42 */     return success(this.buildingPatrolService.selectBuildingPatrolById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody BuildingPatrol buildingPatrol) {
/* 51 */     return toAjax(this.buildingPatrolService.insertBuildingPatrol(buildingPatrol));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody List<BuildingPatrol> buildingPatrolList) {
/* 60 */     return toAjax(this.buildingPatrolService.updateBuildingPatrol(buildingPatrolList));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{routeId}"})
/*    */   public AjaxResult remove(@PathVariable Long routeId) {
/* 69 */     return toAjax(this.buildingPatrolService.deleteBuildingPatrolById(routeId));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingPatrolController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */