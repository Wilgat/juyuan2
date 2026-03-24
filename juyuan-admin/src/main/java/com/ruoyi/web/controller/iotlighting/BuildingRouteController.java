/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.iotlighting.domain.BuildingRoute;
/*    */ import com.ruoyi.iotlighting.service.IBuildingRouteService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/route"})
/*    */ public class BuildingRouteController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingRouteService buildingRouteService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public AjaxResult listPatrol(BuildingRoute buildingRoute) {
/* 34 */     List<BuildingRoute> list = this.buildingRouteService.selectBuildingRouteList(buildingRoute);
/* 35 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/start/{routeId}"})
/*    */   public AjaxResult startPatrol(@PathVariable Long routeId) {
/* 43 */     LoginUser loginUser = getLoginUser();
/* 44 */     Long patrolLogId = this.buildingRouteService.startRoutePatrol(routeId, loginUser.getUserId());
/* 45 */     return AjaxResult.success(patrolLogId.toString());
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingRouteController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */