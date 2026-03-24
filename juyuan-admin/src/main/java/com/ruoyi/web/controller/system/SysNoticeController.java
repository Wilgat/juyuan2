/*    */ package com.ruoyi.web.controller.system;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.system.domain.SysNotice;
/*    */ import com.ruoyi.system.service.ISysNoticeService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.validation.annotation.Validated;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/system/notice"})
/*    */ public class SysNoticeController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ISysNoticeService noticeService;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('system:notice:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(SysNotice notice) {
/* 42 */     startPage();
/* 43 */     List<SysNotice> list = this.noticeService.selectNoticeList(notice);
/* 44 */     return getDataTable(list);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('system:notice:query')")
/*    */   @GetMapping({"/{noticeId}"})
/*    */   public AjaxResult getInfo(@PathVariable Long noticeId) {
/* 54 */     return success(this.noticeService.selectNoticeById(noticeId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('system:notice:add')")
/*    */   @Log(title = "通知公告", businessType = BusinessType.INSERT)
/*    */   @PostMapping
/*    */   public AjaxResult add(@Validated @RequestBody SysNotice notice) {
/* 65 */     notice.setCreateBy(getUsername());
/* 66 */     return toAjax(this.noticeService.insertNotice(notice));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('system:notice:edit')")
/*    */   @Log(title = "通知公告", businessType = BusinessType.UPDATE)
/*    */   @PutMapping
/*    */   public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
/* 77 */     notice.setUpdateBy(getUsername());
/* 78 */     return toAjax(this.noticeService.updateNotice(notice));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('system:notice:remove')")
/*    */   @Log(title = "通知公告", businessType = BusinessType.DELETE)
/*    */   @DeleteMapping({"/{noticeIds}"})
/*    */   public AjaxResult remove(@PathVariable Long[] noticeIds) {
/* 89 */     return toAjax(this.noticeService.deleteNoticeByIds(noticeIds));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysNoticeController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */