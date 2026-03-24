/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.iotlighting.domain.BuildingInfo;
/*    */ import com.ruoyi.iotlighting.service.IBuildingInfoService;
/*    */ import java.util.Date;
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
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/buildingInfo"})
/*    */ public class BuildingInfoController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingInfoService buildingInfoService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(BuildingInfo buildingInfo) {
/* 37 */     startPage();
/* 38 */     List<BuildingInfo> list = this.buildingInfoService.selectBuildingInfoList(buildingInfo);
/* 39 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑信息", businessType = BusinessType.EXPORT)
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, BuildingInfo buildingInfo) {
/* 48 */     List<BuildingInfo> list = this.buildingInfoService.selectBuildingInfoList(buildingInfo);
/* 49 */     ExcelUtil<BuildingInfo> util = new ExcelUtil(BuildingInfo.class);
/* 50 */     util.exportExcel(response, list, "建筑信息数据");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 58 */     return success(this.buildingInfoService.selectBuildingInfoById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑信息", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody BuildingInfo buildingInfo) {
/* 67 */     LoginUser loginUser = getLoginUser();
/* 68 */     buildingInfo.setCreatedBy(loginUser.getUsername());
/* 69 */     buildingInfo.setCreatedTime(new Date());
/* 70 */     buildingInfo.setUpdatedBy(loginUser.getUsername());
/* 71 */     buildingInfo.setUpdatedTime(new Date());
/* 72 */     buildingInfo.setDeptId(buildingInfo.getDeptId());
/* 73 */     buildingInfo.setDelFlag("0");
/* 74 */     return toAjax(this.buildingInfoService.insertBuildingInfo(buildingInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑信息", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody BuildingInfo buildingInfo) {
/* 83 */     LoginUser loginUser = getLoginUser();
/* 84 */     buildingInfo.setUpdatedBy(loginUser.getUsername());
/* 85 */     buildingInfo.setUpdatedTime(new Date());
/* 86 */     buildingInfo.setDeptId(buildingInfo.getDeptId());
/* 87 */     return toAjax(this.buildingInfoService.updateBuildingInfo(buildingInfo));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "建筑信息", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{ids}"})
/*    */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 96 */     return toAjax(this.buildingInfoService.deleteBuildingInfoByIds(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */