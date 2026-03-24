/*    */ package com.ruoyi.web.controller.monitor;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.system.domain.SysOperLog;
/*    */ import com.ruoyi.system.service.ISysOperLogService;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.DeleteMapping;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/monitor/operlog"})
/*    */ public class SysOperlogController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ISysOperLogService operLogService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(SysOperLog operLog) {
/* 38 */     startPage();
/* 39 */     List<SysOperLog> list = this.operLogService.selectOperLogList(operLog);
/* 40 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */   
/*    */   @Log(title = "操作日志", businessType = BusinessType.EXPORT)
/*    */   @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, SysOperLog operLog) {
/* 48 */     List<SysOperLog> list = this.operLogService.selectOperLogList(operLog);
/* 49 */     ExcelUtil<SysOperLog> util = new ExcelUtil(SysOperLog.class);
/* 50 */     util.exportExcel(response, list, "操作日志");
/*    */   }
/*    */ 
/*    */   
/*    */   @Log(title = "操作日志", businessType = BusinessType.DELETE)
/*    */   @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
/*    */   @DeleteMapping({"/{operIds}"})
/*    */   public AjaxResult remove(@PathVariable Long[] operIds) {
/* 58 */     return toAjax(this.operLogService.deleteOperLogByIds(operIds));
/*    */   }
/*    */ 
/*    */   
/*    */   @Log(title = "操作日志", businessType = BusinessType.CLEAN)
/*    */   @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
/*    */   @DeleteMapping({"/clean"})
/*    */   public AjaxResult clean() {
/* 66 */     this.operLogService.cleanOperLog();
/* 67 */     return success();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/monitor/SysOperlogController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */