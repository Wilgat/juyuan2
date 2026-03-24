/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.iotlighting.domain.BuildingFloorInfo;
/*    */ import com.ruoyi.iotlighting.domain.BuildingTerStatusVo;
/*    */ import com.ruoyi.iotlighting.service.IBuildingFloorInfoService;
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
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/buildingFloorInfo"})
/*    */ public class BuildingFloorInfoController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingFloorInfoService buildingFloorInfoService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(BuildingFloorInfo buildingFloorInfo) {
/* 35 */     startPage();
/* 36 */     List<BuildingFloorInfo> list = this.buildingFloorInfoService.selectBuildingFloorInfoList(buildingFloorInfo);
/* 37 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/listStatus"})
/*    */   public TableDataInfo listStatus(BuildingFloorInfo buildingFloorInfo) {
/* 45 */     startPage();
/* 46 */     List<BuildingTerStatusVo> list = this.buildingFloorInfoService.selectBuildingFloorInfoListStatus(buildingFloorInfo);
/* 47 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑层信息", businessType = BusinessType.EXPORT)
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, BuildingFloorInfo buildingFloorInfo) {
/* 57 */     List<BuildingFloorInfo> list = this.buildingFloorInfoService.selectBuildingFloorInfoList(buildingFloorInfo);
/* 58 */     ExcelUtil<BuildingFloorInfo> util = new ExcelUtil(BuildingFloorInfo.class);
/* 59 */     util.exportExcel(response, list, "建筑层信息数据");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 67 */     return success(this.buildingFloorInfoService.selectBuildingFloorInfoById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑层信息", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody BuildingFloorInfo buildingFloorInfo) {
/* 76 */     return toAjax(this.buildingFloorInfoService.insertBuildingFloorInfo(buildingFloorInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑层信息", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody BuildingFloorInfo buildingFloorInfo) {
/* 85 */     return toAjax(this.buildingFloorInfoService.updateBuildingFloorInfo(buildingFloorInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑层信息", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{ids}"})
/*    */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 94 */     return toAjax(this.buildingFloorInfoService.deleteBuildingFloorInfoByIds(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingFloorInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */