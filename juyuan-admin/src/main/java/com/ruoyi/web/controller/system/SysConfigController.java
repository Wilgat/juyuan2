/*     */ package com.ruoyi.web.controller.system;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.common.utils.poi.ExcelUtil;
/*     */ import com.ruoyi.system.domain.SysConfig;
/*     */ import com.ruoyi.system.service.ISysConfigService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
/*     */ import org.springframework.validation.annotation.Validated;
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
/*     */ @RequestMapping({"/system/config"})
/*     */ public class SysConfigController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private ISysConfigService configService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo list(SysConfig config) {
/*  44 */     startPage();
/*  45 */     List<SysConfig> list = this.configService.selectConfigList(config);
/*  46 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */   
/*     */   @Log(title = "参数管理", businessType = BusinessType.EXPORT)
/*     */   @PreAuthorize("@ss.hasPermi('system:config:export')")
/*     */   @PostMapping({"/export"})
/*     */   public void export(HttpServletResponse response, SysConfig config) {
/*  54 */     List<SysConfig> list = this.configService.selectConfigList(config);
/*  55 */     ExcelUtil<SysConfig> util = new ExcelUtil(SysConfig.class);
/*  56 */     util.exportExcel(response, list, "参数数据");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:query')")
/*     */   @GetMapping({"/{configId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long configId) {
/*  66 */     return success(this.configService.selectConfigById(configId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/configKey/{configKey}"})
/*     */   public AjaxResult getConfigKey(@PathVariable String configKey) {
/*  75 */     return success(this.configService.selectConfigByKey(configKey));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:add')")
/*     */   @Log(title = "参数管理", businessType = BusinessType.INSERT)
/*     */   @PostMapping
/*     */   public AjaxResult add(@Validated @RequestBody SysConfig config) {
/*  86 */     if (!this.configService.checkConfigKeyUnique(config))
/*     */     {
/*  88 */       return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
/*     */     }
/*  90 */     config.setCreateBy(getUsername());
/*  91 */     return toAjax(this.configService.insertConfig(config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:edit')")
/*     */   @Log(title = "参数管理", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult edit(@Validated @RequestBody SysConfig config) {
/* 102 */     if (!this.configService.checkConfigKeyUnique(config))
/*     */     {
/* 104 */       return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
/*     */     }
/* 106 */     config.setUpdateBy(getUsername());
/* 107 */     return toAjax(this.configService.updateConfig(config));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:remove')")
/*     */   @Log(title = "参数管理", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{configIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] configIds) {
/* 118 */     this.configService.deleteConfigByIds(configIds);
/* 119 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('system:config:remove')")
/*     */   @Log(title = "参数管理", businessType = BusinessType.CLEAN)
/*     */   @DeleteMapping({"/refreshCache"})
/*     */   public AjaxResult refreshCache() {
/* 130 */     this.configService.resetConfigCache();
/* 131 */     return success();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysConfigController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */