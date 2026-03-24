/*    */ package com.ruoyi.web.controller.iotlighting;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSONObject;
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.iotlighting.domain.BuildingPatrolLog;
/*    */ import com.ruoyi.iotlighting.domain.BuildingPatrolLogDetail;
/*    */ import com.ruoyi.iotlighting.service.IBuildingPatrolLogService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.DeleteMapping;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.PutMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/lighting/patrolLog"})
/*    */ public class BuildingPatrolLogController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private IBuildingPatrolLogService buildingPatrolLogService;
/*    */   
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(BuildingPatrolLog buildingPatrolLog) {
/* 36 */     startPage();
/* 37 */     List<BuildingPatrolLog> list = this.buildingPatrolLogService.selectBuildingPatrolLogList(buildingPatrolLog);
/* 38 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/currentPatrol"})
/*    */   public AjaxResult currentPatrol(@RequestParam(required = false) String patrolLogId, @RequestParam(required = false) String completeFlag) {
/* 44 */     LoginUser loginUser = getLoginUser();
/* 45 */     BuildingPatrolLogDetail buildingPatrolLogDetail = new BuildingPatrolLogDetail();
/* 46 */     buildingPatrolLogDetail.setUserId(loginUser.getUserId());
/* 47 */     if (StringUtils.isNotEmpty(patrolLogId)) {
/* 48 */       buildingPatrolLogDetail.setPatrolLogId(Long.valueOf(Long.parseLong(patrolLogId)));
/*    */     }
/* 50 */     buildingPatrolLogDetail.setCompleteFlag(completeFlag);
/* 51 */     List<BuildingPatrolLogDetail> list = this.buildingPatrolLogService.selectCurrentPatrolLogDetailList(buildingPatrolLogDetail);
/* 52 */     this.logger.info("返回数据:" + JSONObject.toJSONString(list, new com.alibaba.fastjson2.JSONWriter.Feature[0]));
/* 53 */     return AjaxResult.success(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/{id}"})
/*    */   public AjaxResult getInfo(@PathVariable("id") Long id) {
/* 61 */     return success(this.buildingPatrolLogService.selectBuildingPatrolLogById(id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@RequestBody BuildingPatrolLog buildingPatrolLog) {
/* 70 */     return toAjax(this.buildingPatrolLogService.insertBuildingPatrolLog(buildingPatrolLog));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@RequestBody BuildingPatrolLog buildingPatrolLog) {
/* 79 */     return toAjax(this.buildingPatrolLogService.updateBuildingPatrolLog(buildingPatrolLog));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Log(title = "巡检记录", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{ids}"})
/*    */   public AjaxResult remove(@PathVariable Long[] ids) {
/* 88 */     return toAjax(this.buildingPatrolLogService.deleteBuildingPatrolLogByIds(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/iotlighting/BuildingPatrolLogController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */