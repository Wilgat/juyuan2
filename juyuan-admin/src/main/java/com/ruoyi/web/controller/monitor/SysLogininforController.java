/*    */ package com.ruoyi.web.controller.monitor;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*    */ import com.ruoyi.framework.web.service.SysPasswordService;
/*    */ import com.ruoyi.system.domain.SysLogininfor;
/*    */ import com.ruoyi.system.service.ISysLogininforService;
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
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/monitor/logininfor"})
/*    */ public class SysLogininforController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ISysLogininforService logininforService;
/*    */   @Autowired
/*    */   private SysPasswordService passwordService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(SysLogininfor logininfor) {
/* 42 */     startPage();
/* 43 */     List<SysLogininfor> list = this.logininforService.selectLogininforList(logininfor);
/* 44 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */   
/*    */   @Log(title = "登录日志", businessType = BusinessType.EXPORT)
/*    */   @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
/*    */   @PostMapping({"/export"})
/*    */   public void export(HttpServletResponse response, SysLogininfor logininfor) {
/* 52 */     List<SysLogininfor> list = this.logininforService.selectLogininforList(logininfor);
/* 53 */     ExcelUtil<SysLogininfor> util = new ExcelUtil(SysLogininfor.class);
/* 54 */     util.exportExcel(response, list, "登录日志");
/*    */   }
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
/*    */   @Log(title = "登录日志", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{infoIds}"})
/*    */   public AjaxResult remove(@PathVariable Long[] infoIds) {
/* 62 */     return toAjax(this.logininforService.deleteLogininforByIds(infoIds));
/*    */   }
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
/*    */   @Log(title = "登录日志", businessType = BusinessType.CLEAN)
/*    */   @DeleteMapping({"/clean"})
/*    */   public AjaxResult clean() {
/* 70 */     this.logininforService.cleanLogininfor();
/* 71 */     return success();
/*    */   }
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:logininfor:unlock')")
/*    */   @Log(title = "账户解锁", businessType = BusinessType.OTHER)
/*    */   @GetMapping({"/unlock/{userName}"})
/*    */   public AjaxResult unlock(@PathVariable("userName") String userName) {
/* 79 */     this.passwordService.clearLoginRecordCache(userName);
/* 80 */     return success();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/monitor/SysLogininforController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */