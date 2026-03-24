/*     */ package com.ruoyi.generator.service;
/*     */ 
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.generator.domain.GenTable;
/*     */ import com.ruoyi.generator.domain.GenTableColumn;
/*     */ import com.ruoyi.generator.mapper.GenTableColumnMapper;
/*     */ import com.ruoyi.generator.mapper.GenTableMapper;
/*     */ import com.ruoyi.generator.util.GenUtils;
/*     */ import com.ruoyi.generator.util.VelocityInitializer;
/*     */ import com.ruoyi.generator.util.VelocityUtils;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.velocity.Template;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ import org.apache.velocity.app.Velocity;
/*     */ import org.apache.velocity.context.Context;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class GenTableServiceImpl
/*     */   implements IGenTableService
/*     */ {
/*  48 */   private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private GenTableMapper genTableMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private GenTableColumnMapper genTableColumnMapper;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenTable selectGenTableById(Long id) {
/*  65 */     GenTable genTable = this.genTableMapper.selectGenTableById(id);
/*  66 */     setTableFromOptions(genTable);
/*  67 */     return genTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<GenTable> selectGenTableList(GenTable genTable) {
/*  79 */     return this.genTableMapper.selectGenTableList(genTable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<GenTable> selectDbTableList(GenTable genTable) {
/*  91 */     return this.genTableMapper.selectDbTableList(genTable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<GenTable> selectDbTableListByNames(String[] tableNames) {
/* 103 */     return this.genTableMapper.selectDbTableListByNames(tableNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<GenTable> selectGenTableAll() {
/* 114 */     return this.genTableMapper.selectGenTableAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public void updateGenTable(GenTable genTable) {
/* 127 */     String options = JSON.toJSONString(genTable.getParams());
/* 128 */     genTable.setOptions(options);
/* 129 */     int row = this.genTableMapper.updateGenTable(genTable);
/* 130 */     if (row > 0)
/*     */     {
/* 132 */       for (GenTableColumn cenTableColumn : genTable.getColumns())
/*     */       {
/* 134 */         this.genTableColumnMapper.updateGenTableColumn(cenTableColumn);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public void deleteGenTableByIds(Long[] tableIds) {
/* 149 */     this.genTableMapper.deleteGenTableByIds(tableIds);
/* 150 */     this.genTableColumnMapper.deleteGenTableColumnByIds(tableIds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public void importGenTable(List<GenTable> tableList) {
/* 162 */     String operName = SecurityUtils.getUsername();
/*     */     
/*     */     try {
/* 165 */       for (GenTable table : tableList) {
/*     */         
/* 167 */         String tableName = table.getTableName();
/* 168 */         GenUtils.initTable(table, operName);
/* 169 */         int row = this.genTableMapper.insertGenTable(table);
/* 170 */         if (row > 0) {
/*     */ 
/*     */           
/* 173 */           List<GenTableColumn> genTableColumns = this.genTableColumnMapper.selectDbTableColumnsByName(tableName);
/* 174 */           for (GenTableColumn column : genTableColumns)
/*     */           {
/* 176 */             GenUtils.initColumnField(column, table);
/* 177 */             this.genTableColumnMapper.insertGenTableColumn(column);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 182 */     } catch (Exception e) {
/*     */       
/* 184 */       throw new ServiceException("导入失败：" + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> previewCode(Long tableId) {
/* 197 */     Map<String, String> dataMap = new LinkedHashMap<>();
/*     */     
/* 199 */     GenTable table = this.genTableMapper.selectGenTableById(tableId);
/*     */     
/* 201 */     setSubTable(table);
/*     */     
/* 203 */     setPkColumn(table);
/* 204 */     VelocityInitializer.initVelocity();
/*     */     
/* 206 */     VelocityContext context = VelocityUtils.prepareContext(table);
/*     */ 
/*     */     
/* 209 */     List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
/* 210 */     for (String template : templates) {
/*     */ 
/*     */       
/* 213 */       StringWriter sw = new StringWriter();
/* 214 */       Template tpl = Velocity.getTemplate(template, "UTF-8");
/* 215 */       tpl.merge((Context)context, sw);
/* 216 */       dataMap.put(template, sw.toString());
/*     */     } 
/* 218 */     return dataMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] downloadCode(String tableName) {
/* 230 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 231 */     ZipOutputStream zip = new ZipOutputStream(outputStream);
/* 232 */     generatorCode(tableName, zip);
/* 233 */     IOUtils.closeQuietly(zip);
/* 234 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generatorCode(String tableName) {
/* 246 */     GenTable table = this.genTableMapper.selectGenTableByName(tableName);
/*     */     
/* 248 */     setSubTable(table);
/*     */     
/* 250 */     setPkColumn(table);
/*     */     
/* 252 */     VelocityInitializer.initVelocity();
/*     */     
/* 254 */     VelocityContext context = VelocityUtils.prepareContext(table);
/*     */ 
/*     */     
/* 257 */     List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
/* 258 */     for (String template : templates) {
/*     */       
/* 260 */       if (!StringUtils.containsAny(template, new CharSequence[] { "sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm" })) {
/*     */ 
/*     */         
/* 263 */         StringWriter sw = new StringWriter();
/* 264 */         Template tpl = Velocity.getTemplate(template, "UTF-8");
/* 265 */         tpl.merge((Context)context, sw);
/*     */         
/*     */         try {
/* 268 */           String path = getGenPath(table, template);
/* 269 */           FileUtils.writeStringToFile(new File(path), sw.toString(), "UTF-8");
/*     */         }
/* 271 */         catch (IOException e) {
/*     */           
/* 273 */           throw new ServiceException("渲染模板失败，表名：" + table.getTableName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public void synchDb(String tableName) {
/* 288 */     GenTable table = this.genTableMapper.selectGenTableByName(tableName);
/* 289 */     List<GenTableColumn> tableColumns = table.getColumns();
/* 290 */     Map<String, GenTableColumn> tableColumnMap = (Map<String, GenTableColumn>)tableColumns.stream().collect(Collectors.toMap(GenTableColumn::getColumnName, Function.identity()));
/*     */     
/* 292 */     List<GenTableColumn> dbTableColumns = this.genTableColumnMapper.selectDbTableColumnsByName(tableName);
/* 293 */     if (StringUtils.isEmpty(dbTableColumns))
/*     */     {
/* 295 */       throw new ServiceException("同步数据失败，原表结构不存在");
/*     */     }
/* 297 */     List<String> dbTableColumnNames = (List<String>)dbTableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());
/*     */     
/* 299 */     dbTableColumns.forEach(column -> {
/*     */           GenUtils.initColumnField(column, table);
/*     */ 
/*     */           
/*     */           if (tableColumnMap.containsKey(column.getColumnName())) {
/*     */             GenTableColumn prevColumn = (GenTableColumn)tableColumnMap.get(column.getColumnName());
/*     */ 
/*     */             
/*     */             column.setColumnId(prevColumn.getColumnId());
/*     */             
/*     */             if (column.isList()) {
/*     */               column.setDictType(prevColumn.getDictType());
/*     */               
/*     */               column.setQueryType(prevColumn.getQueryType());
/*     */             } 
/*     */             
/*     */             if (StringUtils.isNotEmpty(prevColumn.getIsRequired()) && !column.isPk() && (column.isInsert() || column.isEdit()) && (column.isUsableColumn() || !column.isSuperColumn())) {
/*     */               column.setIsRequired(prevColumn.getIsRequired());
/*     */               
/*     */               column.setHtmlType(prevColumn.getHtmlType());
/*     */             } 
/*     */             
/*     */             this.genTableColumnMapper.updateGenTableColumn(column);
/*     */           } else {
/*     */             this.genTableColumnMapper.insertGenTableColumn(column);
/*     */           } 
/*     */         });
/*     */     
/* 327 */     List<GenTableColumn> delColumns = (List<GenTableColumn>)tableColumns.stream().filter(column -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
/* 328 */     if (StringUtils.isNotEmpty(delColumns))
/*     */     {
/* 330 */       this.genTableColumnMapper.deleteGenTableColumns(delColumns);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] downloadCode(String[] tableNames) {
/* 343 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 344 */     ZipOutputStream zip = new ZipOutputStream(outputStream);
/* 345 */     for (String tableName : tableNames)
/*     */     {
/* 347 */       generatorCode(tableName, zip);
/*     */     }
/* 349 */     IOUtils.closeQuietly(zip);
/* 350 */     return outputStream.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generatorCode(String tableName, ZipOutputStream zip) {
/* 359 */     GenTable table = this.genTableMapper.selectGenTableByName(tableName);
/*     */     
/* 361 */     setSubTable(table);
/*     */     
/* 363 */     setPkColumn(table);
/*     */     
/* 365 */     VelocityInitializer.initVelocity();
/*     */     
/* 367 */     VelocityContext context = VelocityUtils.prepareContext(table);
/*     */ 
/*     */     
/* 370 */     List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
/* 371 */     for (String template : templates) {
/*     */ 
/*     */       
/* 374 */       StringWriter sw = new StringWriter();
/* 375 */       Template tpl = Velocity.getTemplate(template, "UTF-8");
/* 376 */       tpl.merge((Context)context, sw);
/*     */ 
/*     */       
/*     */       try {
/* 380 */         zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
/* 381 */         IOUtils.write(sw.toString(), zip, "UTF-8");
/* 382 */         IOUtils.closeQuietly(sw);
/* 383 */         zip.flush();
/* 384 */         zip.closeEntry();
/*     */       }
/* 386 */       catch (IOException e) {
/*     */         
/* 388 */         log.error("渲染模板失败，表名：" + table.getTableName(), e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateEdit(GenTable genTable) {
/* 401 */     if ("tree".equals(genTable.getTplCategory())) {
/*     */       
/* 403 */       String options = JSON.toJSONString(genTable.getParams());
/* 404 */       JSONObject paramsObj = JSON.parseObject(options);
/* 405 */       if (StringUtils.isEmpty(paramsObj.getString("treeCode")))
/*     */       {
/* 407 */         throw new ServiceException("树编码字段不能为空");
/*     */       }
/* 409 */       if (StringUtils.isEmpty(paramsObj.getString("treeParentCode")))
/*     */       {
/* 411 */         throw new ServiceException("树父编码字段不能为空");
/*     */       }
/* 413 */       if (StringUtils.isEmpty(paramsObj.getString("treeName")))
/*     */       {
/* 415 */         throw new ServiceException("树名称字段不能为空");
/*     */       }
/* 417 */       if ("sub".equals(genTable.getTplCategory())) {
/*     */         
/* 419 */         if (StringUtils.isEmpty(genTable.getSubTableName()))
/*     */         {
/* 421 */           throw new ServiceException("关联子表的表名不能为空");
/*     */         }
/* 423 */         if (StringUtils.isEmpty(genTable.getSubTableFkName()))
/*     */         {
/* 425 */           throw new ServiceException("子表关联的外键名不能为空");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPkColumn(GenTable table) {
/* 438 */     for (GenTableColumn column : table.getColumns()) {
/*     */       
/* 440 */       if (column.isPk()) {
/*     */         
/* 442 */         table.setPkColumn(column);
/*     */         break;
/*     */       } 
/*     */     } 
/* 446 */     if (StringUtils.isNull(table.getPkColumn()))
/*     */     {
/* 448 */       table.setPkColumn(table.getColumns().get(0));
/*     */     }
/* 450 */     if ("sub".equals(table.getTplCategory())) {
/*     */       
/* 452 */       for (GenTableColumn column : table.getSubTable().getColumns()) {
/*     */         
/* 454 */         if (column.isPk()) {
/*     */           
/* 456 */           table.getSubTable().setPkColumn(column);
/*     */           break;
/*     */         } 
/*     */       } 
/* 460 */       if (StringUtils.isNull(table.getSubTable().getPkColumn()))
/*     */       {
/* 462 */         table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubTable(GenTable table) {
/* 474 */     String subTableName = table.getSubTableName();
/* 475 */     if (StringUtils.isNotEmpty(subTableName))
/*     */     {
/* 477 */       table.setSubTable(this.genTableMapper.selectGenTableByName(subTableName));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTableFromOptions(GenTable genTable) {
/* 488 */     JSONObject paramsObj = JSON.parseObject(genTable.getOptions());
/* 489 */     if (StringUtils.isNotNull(paramsObj)) {
/*     */       
/* 491 */       String treeCode = paramsObj.getString("treeCode");
/* 492 */       String treeParentCode = paramsObj.getString("treeParentCode");
/* 493 */       String treeName = paramsObj.getString("treeName");
/* 494 */       String parentMenuId = paramsObj.getString("parentMenuId");
/* 495 */       String parentMenuName = paramsObj.getString("parentMenuName");
/*     */       
/* 497 */       genTable.setTreeCode(treeCode);
/* 498 */       genTable.setTreeParentCode(treeParentCode);
/* 499 */       genTable.setTreeName(treeName);
/* 500 */       genTable.setParentMenuId(parentMenuId);
/* 501 */       genTable.setParentMenuName(parentMenuName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getGenPath(GenTable table, String template) {
/* 514 */     String genPath = table.getGenPath();
/* 515 */     if (StringUtils.equals(genPath, "/"))
/*     */     {
/* 517 */       return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
/*     */     }
/* 519 */     return genPath + File.separator + VelocityUtils.getFileName(template, table);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/service/GenTableServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */