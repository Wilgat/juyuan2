/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.iotlighting.domain.BuildingArea;
/*    */ import com.ruoyi.iotlighting.domain.BuildingAreaStaff;
/*    */ import com.ruoyi.iotlighting.service.IBuildingAreaService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/area"})
/*    */ public class BuildingAreaController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingAreaService buildingAreaService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:area:list')")
/*    */   @GetMapping({"/list"})
/*    */   public AjaxResult listArea(BuildingArea buildingArea) {
/* 33 */     List<BuildingArea> list = this.buildingAreaService.selectBuildingAreaList(buildingArea);
/* 34 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:area:list')")
/*    */   @GetMapping({"/listAll"})
/*    */   public AjaxResult listAreaAll(BuildingArea buildingArea) {
/* 43 */     List<BuildingArea> list = this.buildingAreaService.selectBuildingAreaListAll(buildingArea);
/* 44 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:area:list')")
/*    */   @GetMapping({"/list/staff"})
/*    */   public AjaxResult listAreaStaff(BuildingAreaStaff buildingAreaStaff) {
/* 53 */     List<BuildingAreaStaff> list = this.buildingAreaService.selectBuildingAreaStaffList(buildingAreaStaff);
/* 54 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:area:list')")
/*    */   @GetMapping({"/list/staffConfig"})
/*    */   public AjaxResult listAreaStaffConfig(BuildingAreaStaff buildingAreaStaff) {
/* 63 */     List<BuildingAreaStaff> list = this.buildingAreaService.selectBuildingAreaStaffConfigList(buildingAreaStaff);
/* 64 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('lighting:area:list')")
/*    */   @PostMapping({"/update/staffConfig/{areaId}"})
/*    */   public AjaxResult updateAreaStaffConfig(@PathVariable("id") Long buildingAreaId, @RequestBody List<BuildingAreaStaff> buildingAreaStaffList) {
/* 74 */     int count = this.buildingAreaService.updateAreaStaffConfig(buildingAreaId, buildingAreaStaffList);
/* 75 */     return AjaxResult.success(Integer.valueOf(count));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingAreaController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */