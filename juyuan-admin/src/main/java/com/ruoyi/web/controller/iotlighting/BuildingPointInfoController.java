/*     */ package com.ruoyi.web.controller.iotlighting;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.iotlighting.domain.BuildingPointInfo;
/*     */ import com.ruoyi.iotlighting.service.IBuildingPointInfoService;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.util.ObjectUtils;
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
/*     */ @RequestMapping({"/lighting/buildingPointInfo"})
/*     */ public class BuildingPointInfoController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private IBuildingPointInfoService buildingPointInfoService;
/*     */   
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(BuildingPointInfo buildingPointInfo) {
/*  40 */     startPage();
/*  41 */     List<BuildingPointInfo> list = this.buildingPointInfoService.selectBuildingPointInfoList(buildingPointInfo);
/*  42 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/listPointTer"})
/*     */   public AjaxResult listPointTer(BuildingPointInfo buildingPointInfo) {
/*  50 */     List<BuildingPointInfo> list = this.buildingPointInfoService.selectBuildingPointTerList(buildingPointInfo);
/*  51 */     return success(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "建筑点位", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, BuildingPointInfo buildingPointInfo) {
/*  60 */     List<BuildingPointInfo> list = this.buildingPointInfoService.selectBuildingPointInfoList(buildingPointInfo);
/*  61 */     ExcelUtil<BuildingPointInfo> util = new ExcelUtil(BuildingPointInfo.class);
/*  62 */     util.exportExcel(response, list, "建筑点位数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/{id}"})
/*     */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/*  70 */     return success(this.buildingPointInfoService.selectBuildingPointInfoById(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "建筑点位", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody BuildingPointInfo buildingPointInfo) {
/*  79 */     BuildingPointInfo dbBuildingPointInfo = this.buildingPointInfoService.selectBuildingPointInfo(buildingPointInfo);
/*  80 */     if (!ObjectUtils.isEmpty(dbBuildingPointInfo)) {
/*  81 */       return error(MessageUtils.message("buildingPointInfo.add.error", new Object[0]));
/*     */     }
/*  83 */     LoginUser loginUser = getLoginUser();
/*  84 */     buildingPointInfo.setCreatedBy(loginUser.getUsername());
/*  85 */     buildingPointInfo.setCreatedTime(new Date());
/*  86 */     buildingPointInfo.setUpdatedBy(loginUser.getUsername());
/*  87 */     buildingPointInfo.setUpdatedTime(new Date());
/*  88 */     buildingPointInfo.setDelFlag("0");
/*  89 */     return toAjax(this.buildingPointInfoService.insertBuildingPointInfo(buildingPointInfo));
/*     */   }
/*     */ 
/*     */   
/*     */   @GetMapping({"/listArea"})
/*     */   public AjaxResult listArea(BuildingPointInfo buildingPointInfo) {
/*  95 */     return success(this.buildingPointInfoService.selectBuildingAreaInfo(buildingPointInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "建筑点位", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody BuildingPointInfo buildingPointInfo) {
/* 104 */     LoginUser loginUser = getLoginUser();
/* 105 */     buildingPointInfo.setUpdatedBy(loginUser.getUsername());
/* 106 */     buildingPointInfo.setUpdatedTime(new Date());
/* 107 */     return toAjax(this.buildingPointInfoService.updateBuildingPointInfo(buildingPointInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Log(title = "建筑点位", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{id}"})
/*     */   public AjaxResult remove(@PathVariable Long id) {
/* 116 */     BuildingPointInfo buildingPointInfo = this.buildingPointInfoService.selectBuildingPointInfoById(id);
/* 117 */     if (!ObjectUtils.isEmpty(buildingPointInfo) && StringUtils.isNotEmpty(buildingPointInfo.getTerSn())) {
/* 118 */       return error(MessageUtils.message("buildingPointInfo.remove.error", new Object[0]));
/*     */     }
/* 120 */     return toAjax(this.buildingPointInfoService.deleteBuildingPointInfoById(id));
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingPointInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */