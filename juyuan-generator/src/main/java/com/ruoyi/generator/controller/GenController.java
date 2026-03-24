/*     */ package com.ruoyi.generator.controller;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Log;
/*     */ import com.ruoyi.common.core.controller.BaseController;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.core.text.Convert;
/*     */ import com.ruoyi.common.enums.BusinessType;
/*     */ import com.ruoyi.generator.domain.GenTable;
/*     */ import com.ruoyi.generator.domain.GenTableColumn;
/*     */ import com.ruoyi.generator.service.IGenTableColumnService;
/*     */ import com.ruoyi.generator.service.IGenTableService;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.IOUtils;
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
/*     */ @RequestMapping({"/tool/gen"})
/*     */ public class GenController
/*     */   extends BaseController
/*     */ {
/*     */   @Autowired
/*     */   private IGenTableService genTableService;
/*     */   @Autowired
/*     */   private IGenTableColumnService genTableColumnService;
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
/*     */   @GetMapping({"/list"})
/*     */   public TableDataInfo genList(GenTable genTable) {
/*  53 */     startPage();
/*  54 */     List<GenTable> list = this.genTableService.selectGenTableList(genTable);
/*  55 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:query')")
/*     */   @GetMapping({"/{tableId}"})
/*     */   public AjaxResult getInfo(@PathVariable Long tableId) {
/*  65 */     GenTable table = this.genTableService.selectGenTableById(tableId);
/*  66 */     List<GenTable> tables = this.genTableService.selectGenTableAll();
/*  67 */     List<GenTableColumn> list = this.genTableColumnService.selectGenTableColumnListByTableId(tableId);
/*  68 */     Map<String, Object> map = new HashMap<>();
/*  69 */     map.put("info", table);
/*  70 */     map.put("rows", list);
/*  71 */     map.put("tables", tables);
/*  72 */     return success(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
/*     */   @GetMapping({"/db/list"})
/*     */   public TableDataInfo dataList(GenTable genTable) {
/*  82 */     startPage();
/*  83 */     List<GenTable> list = this.genTableService.selectDbTableList(genTable);
/*  84 */     return getDataTable(list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
/*     */   @GetMapping({"/column/{tableId}"})
/*     */   public TableDataInfo columnList(Long tableId) {
/*  94 */     TableDataInfo dataInfo = new TableDataInfo();
/*  95 */     List<GenTableColumn> list = this.genTableColumnService.selectGenTableColumnListByTableId(tableId);
/*  96 */     dataInfo.setRows(list);
/*  97 */     dataInfo.setTotal(list.size());
/*  98 */     return dataInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:import')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.IMPORT)
/*     */   @PostMapping({"/importTable"})
/*     */   public AjaxResult importTableSave(String tables) {
/* 109 */     String[] tableNames = Convert.toStrArray(tables);
/*     */     
/* 111 */     List<GenTable> tableList = this.genTableService.selectDbTableListByNames(tableNames);
/* 112 */     this.genTableService.importGenTable(tableList);
/* 113 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.UPDATE)
/*     */   @PutMapping
/*     */   public AjaxResult editSave(@Validated @RequestBody GenTable genTable) {
/* 124 */     this.genTableService.validateEdit(genTable);
/* 125 */     this.genTableService.updateGenTable(genTable);
/* 126 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:remove')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.DELETE)
/*     */   @DeleteMapping({"/{tableIds}"})
/*     */   public AjaxResult remove(@PathVariable Long[] tableIds) {
/* 137 */     this.genTableService.deleteGenTableByIds(tableIds);
/* 138 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:preview')")
/*     */   @GetMapping({"/preview/{tableId}"})
/*     */   public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
/* 148 */     Map<String, String> dataMap = this.genTableService.previewCode(tableId);
/* 149 */     return success(dataMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.GENCODE)
/*     */   @GetMapping({"/download/{tableName}"})
/*     */   public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
/* 160 */     byte[] data = this.genTableService.downloadCode(tableName);
/* 161 */     genCode(response, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.GENCODE)
/*     */   @GetMapping({"/genCode/{tableName}"})
/*     */   public AjaxResult genCode(@PathVariable("tableName") String tableName) {
/* 172 */     this.genTableService.generatorCode(tableName);
/* 173 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.UPDATE)
/*     */   @GetMapping({"/synchDb/{tableName}"})
/*     */   public AjaxResult synchDb(@PathVariable("tableName") String tableName) {
/* 184 */     this.genTableService.synchDb(tableName);
/* 185 */     return success();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
/*     */   @Log(title = "代码生成", businessType = BusinessType.GENCODE)
/*     */   @GetMapping({"/batchGenCode"})
/*     */   public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
/* 196 */     String[] tableNames = Convert.toStrArray(tables);
/* 197 */     byte[] data = this.genTableService.downloadCode(tableNames);
/* 198 */     genCode(response, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void genCode(HttpServletResponse response, byte[] data) throws IOException {
/* 206 */     response.reset();
/* 207 */     response.addHeader("Access-Control-Allow-Origin", "*");
/* 208 */     response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
/* 209 */     response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
/* 210 */     response.addHeader("Content-Length", "" + data.length);
/* 211 */     response.setContentType("application/octet-stream; charset=UTF-8");
/* 212 */     IOUtils.write(data, (OutputStream)response.getOutputStream());
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/controller/GenController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */