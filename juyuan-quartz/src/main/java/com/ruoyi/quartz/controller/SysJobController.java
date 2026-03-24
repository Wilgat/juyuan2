/*     */ package com.ruoyi.quartz.controller;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.constant.Constants;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.exception.job.TaskException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.quartz.domain.SysJob;
/*     */ import com.ruoyi.quartz.service.ISysJobService;
/*     */ import com.ruoyi.quartz.util.CronUtils;
/*     */ import com.ruoyi.quartz.util.ScheduleUtils;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestController
/*     */ @RequestMapping({"/monitor/job"})
/*     */ public class SysJobController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysJobService jobService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysJob sysJob) {
/*  49 */     startPage();
/*  50 */     List<SysJob> list = this.jobService.selectJobList(sysJob);
/*  51 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:export')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.EXPORT)
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysJob sysJob) {
/*  62 */     List<SysJob> list = this.jobService.selectJobList(sysJob);
/*  63 */     ExcelUtil<SysJob> util = new ExcelUtil(SysJob.class);
/*  64 */     util.exportExcel(response, list, "定时任务");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:query')")
/*     */   @GetMapping({"/{jobId}"})
/*     */   public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
/*  74 */     return success(this.jobService.selectJobById(jobId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:add')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException {
/*  85 */     if (!CronUtils.isValid(job.getCronExpression()))
/*     */     {
/*  87 */       return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
/*     */     }
/*  89 */     if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), "rmi:"))
/*     */     {
/*  91 */       return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
/*     */     }
/*  93 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])new String[] { "ldap:", "ldaps:" }))
/*     */     {
/*  95 */       return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
/*     */     }
/*  97 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])new String[] { "http://", "https://" }))
/*     */     {
/*  99 */       return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
/*     */     }
/* 101 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])Constants.JOB_ERROR_STR))
/*     */     {
/* 103 */       return error("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
/*     */     }
/* 105 */     if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
/*     */     {
/* 107 */       return error("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
/*     */     }
/* 109 */     job.setCreateBy(getUsername());
/* 110 */     return toAjax(this.jobService.insertJob(job));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException {
/* 121 */     if (!CronUtils.isValid(job.getCronExpression()))
/*     */     {
/* 123 */       return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
/*     */     }
/* 125 */     if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), "rmi:"))
/*     */     {
/* 127 */       return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
/*     */     }
/* 129 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])new String[] { "ldap:", "ldaps:" }))
/*     */     {
/* 131 */       return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
/*     */     }
/* 133 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])new String[] { "http://", "https://" }))
/*     */     {
/* 135 */       return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
/*     */     }
/* 137 */     if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), (CharSequence[])Constants.JOB_ERROR_STR))
/*     */     {
/* 139 */       return error("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
/*     */     }
/* 141 */     if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
/*     */     {
/* 143 */       return error("修改任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
/*     */     }
/* 145 */     job.setUpdateBy(getUsername());
/* 146 */     return toAjax(this.jobService.updateJob(job));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/changeStatus"})
/*     */   public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
/* 157 */     SysJob newJob = this.jobService.selectJobById(job.getJobId());
/* 158 */     newJob.setStatus(job.getStatus());
/* 159 */     return toAjax(this.jobService.changeStatus(newJob));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.UPDATE)
/*     */   @PutMapping({"/run"})
/*     */   public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {
/* 170 */     boolean result = this.jobService.run(job);
/* 171 */     return result ? success() : error("任务不存在或已过期！");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
/*     */   @Log(title = "定时任务", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{jobIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException {
/* 182 */     this.jobService.deleteJobByIds(jobIds);
/* 183 */     return success();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-quartz-3.8.6/!/com/ruoyi/quartz/controller/SysJobController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */