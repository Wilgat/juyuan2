/*     */ package com.ruoyi.generator.util;
/*     */ 
/*     */ import com.alibaba.fastjson2.JSON;
/*     */ import com.alibaba.fastjson2.JSONObject;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.generator.domain.GenTable;
/*     */ import com.ruoyi.generator.domain.GenTableColumn;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VelocityUtils
/*     */ {
/*     */   private static final String PROJECT_PATH = "main/java";
/*     */   private static final String MYBATIS_PATH = "main/resources/mapper";
/*     */   private static final String DEFAULT_PARENT_MENU_ID = "3";
/*     */   
/*     */   public static VelocityContext prepareContext(GenTable genTable) {
/*  39 */     String moduleName = genTable.getModuleName();
/*  40 */     String businessName = genTable.getBusinessName();
/*  41 */     String packageName = genTable.getPackageName();
/*  42 */     String tplCategory = genTable.getTplCategory();
/*  43 */     String functionName = genTable.getFunctionName();
/*     */     
/*  45 */     VelocityContext velocityContext = new VelocityContext();
/*  46 */     velocityContext.put("tplCategory", genTable.getTplCategory());
/*  47 */     velocityContext.put("tableName", genTable.getTableName());
/*  48 */     velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
/*  49 */     velocityContext.put("ClassName", genTable.getClassName());
/*  50 */     velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
/*  51 */     velocityContext.put("moduleName", genTable.getModuleName());
/*  52 */     velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
/*  53 */     velocityContext.put("businessName", genTable.getBusinessName());
/*  54 */     velocityContext.put("basePackage", getPackagePrefix(packageName));
/*  55 */     velocityContext.put("packageName", packageName);
/*  56 */     velocityContext.put("author", genTable.getFunctionAuthor());
/*  57 */     velocityContext.put("datetime", DateUtils.getDate());
/*  58 */     velocityContext.put("pkColumn", genTable.getPkColumn());
/*  59 */     velocityContext.put("importList", getImportList(genTable));
/*  60 */     velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
/*  61 */     velocityContext.put("columns", genTable.getColumns());
/*  62 */     velocityContext.put("table", genTable);
/*  63 */     velocityContext.put("dicts", getDicts(genTable));
/*  64 */     setMenuVelocityContext(velocityContext, genTable);
/*  65 */     if ("tree".equals(tplCategory))
/*     */     {
/*  67 */       setTreeVelocityContext(velocityContext, genTable);
/*     */     }
/*  69 */     if ("sub".equals(tplCategory))
/*     */     {
/*  71 */       setSubVelocityContext(velocityContext, genTable);
/*     */     }
/*  73 */     return velocityContext;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
/*  78 */     String options = genTable.getOptions();
/*  79 */     JSONObject paramsObj = JSON.parseObject(options);
/*  80 */     String parentMenuId = getParentMenuId(paramsObj);
/*  81 */     context.put("parentMenuId", parentMenuId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
/*  86 */     String options = genTable.getOptions();
/*  87 */     JSONObject paramsObj = JSON.parseObject(options);
/*  88 */     String treeCode = getTreecode(paramsObj);
/*  89 */     String treeParentCode = getTreeParentCode(paramsObj);
/*  90 */     String treeName = getTreeName(paramsObj);
/*     */     
/*  92 */     context.put("treeCode", treeCode);
/*  93 */     context.put("treeParentCode", treeParentCode);
/*  94 */     context.put("treeName", treeName);
/*  95 */     context.put("expandColumn", Integer.valueOf(getExpandColumn(genTable)));
/*  96 */     if (paramsObj.containsKey("treeParentCode"))
/*     */     {
/*  98 */       context.put("tree_parent_code", paramsObj.getString("treeParentCode"));
/*     */     }
/* 100 */     if (paramsObj.containsKey("treeName"))
/*     */     {
/* 102 */       context.put("tree_name", paramsObj.getString("treeName"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
/* 108 */     GenTable subTable = genTable.getSubTable();
/* 109 */     String subTableName = genTable.getSubTableName();
/* 110 */     String subTableFkName = genTable.getSubTableFkName();
/* 111 */     String subClassName = genTable.getSubTable().getClassName();
/* 112 */     String subTableFkClassName = StringUtils.convertToCamelCase(subTableFkName);
/*     */     
/* 114 */     context.put("subTable", subTable);
/* 115 */     context.put("subTableName", subTableName);
/* 116 */     context.put("subTableFkName", subTableFkName);
/* 117 */     context.put("subTableFkClassName", subTableFkClassName);
/* 118 */     context.put("subTableFkclassName", StringUtils.uncapitalize(subTableFkClassName));
/* 119 */     context.put("subClassName", subClassName);
/* 120 */     context.put("subclassName", StringUtils.uncapitalize(subClassName));
/* 121 */     context.put("subImportList", getImportList(genTable.getSubTable()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getTemplateList(String tplCategory) {
/* 131 */     List<String> templates = new ArrayList<>();
/* 132 */     templates.add("vm/java/domain.java.vm");
/* 133 */     templates.add("vm/java/mapper.java.vm");
/* 134 */     templates.add("vm/java/service.java.vm");
/* 135 */     templates.add("vm/java/serviceImpl.java.vm");
/* 136 */     templates.add("vm/java/controller.java.vm");
/* 137 */     templates.add("vm/xml/mapper.xml.vm");
/* 138 */     templates.add("vm/sql/sql.vm");
/* 139 */     templates.add("vm/js/api.js.vm");
/* 140 */     if ("crud".equals(tplCategory)) {
/*     */       
/* 142 */       templates.add("vm/vue/index.vue.vm");
/*     */     }
/* 144 */     else if ("tree".equals(tplCategory)) {
/*     */       
/* 146 */       templates.add("vm/vue/index-tree.vue.vm");
/*     */     }
/* 148 */     else if ("sub".equals(tplCategory)) {
/*     */       
/* 150 */       templates.add("vm/vue/index.vue.vm");
/* 151 */       templates.add("vm/java/sub-domain.java.vm");
/*     */     } 
/* 153 */     return templates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFileName(String template, GenTable genTable) {
/* 162 */     String fileName = "";
/*     */     
/* 164 */     String packageName = genTable.getPackageName();
/*     */     
/* 166 */     String moduleName = genTable.getModuleName();
/*     */     
/* 168 */     String className = genTable.getClassName();
/*     */     
/* 170 */     String businessName = genTable.getBusinessName();
/*     */     
/* 172 */     String javaPath = "main/java/" + StringUtils.replace(packageName, ".", "/");
/* 173 */     String mybatisPath = "main/resources/mapper/" + moduleName;
/* 174 */     String vuePath = "vue";
/*     */     
/* 176 */     if (template.contains("domain.java.vm"))
/*     */     {
/* 178 */       fileName = StringUtils.format("{}/domain/{}.java", new Object[] { javaPath, className });
/*     */     }
/* 180 */     if (template.contains("sub-domain.java.vm") && StringUtils.equals("sub", genTable.getTplCategory())) {
/*     */       
/* 182 */       fileName = StringUtils.format("{}/domain/{}.java", new Object[] { javaPath, genTable.getSubTable().getClassName() });
/*     */     }
/* 184 */     else if (template.contains("mapper.java.vm")) {
/*     */       
/* 186 */       fileName = StringUtils.format("{}/mapper/{}Mapper.java", new Object[] { javaPath, className });
/*     */     }
/* 188 */     else if (template.contains("service.java.vm")) {
/*     */       
/* 190 */       fileName = StringUtils.format("{}/service/I{}Service.java", new Object[] { javaPath, className });
/*     */     }
/* 192 */     else if (template.contains("serviceImpl.java.vm")) {
/*     */       
/* 194 */       fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", new Object[] { javaPath, className });
/*     */     }
/* 196 */     else if (template.contains("controller.java.vm")) {
/*     */       
/* 198 */       fileName = StringUtils.format("{}/controller/{}Controller.java", new Object[] { javaPath, className });
/*     */     }
/* 200 */     else if (template.contains("mapper.xml.vm")) {
/*     */       
/* 202 */       fileName = StringUtils.format("{}/{}Mapper.xml", new Object[] { mybatisPath, className });
/*     */     }
/* 204 */     else if (template.contains("sql.vm")) {
/*     */       
/* 206 */       fileName = businessName + "Menu.sql";
/*     */     }
/* 208 */     else if (template.contains("api.js.vm")) {
/*     */       
/* 210 */       fileName = StringUtils.format("{}/api/{}/{}.js", new Object[] { vuePath, moduleName, businessName });
/*     */     }
/* 212 */     else if (template.contains("index.vue.vm")) {
/*     */       
/* 214 */       fileName = StringUtils.format("{}/views/{}/{}/index.vue", new Object[] { vuePath, moduleName, businessName });
/*     */     }
/* 216 */     else if (template.contains("index-tree.vue.vm")) {
/*     */       
/* 218 */       fileName = StringUtils.format("{}/views/{}/{}/index.vue", new Object[] { vuePath, moduleName, businessName });
/*     */     } 
/* 220 */     return fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPackagePrefix(String packageName) {
/* 231 */     int lastIndex = packageName.lastIndexOf(".");
/* 232 */     return StringUtils.substring(packageName, 0, lastIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashSet<String> getImportList(GenTable genTable) {
/* 243 */     List<GenTableColumn> columns = genTable.getColumns();
/* 244 */     GenTable subGenTable = genTable.getSubTable();
/* 245 */     HashSet<String> importList = new HashSet<>();
/* 246 */     if (StringUtils.isNotNull(subGenTable))
/*     */     {
/* 248 */       importList.add("java.util.List");
/*     */     }
/* 250 */     for (GenTableColumn column : columns) {
/*     */       
/* 252 */       if (!column.isSuperColumn() && "Date".equals(column.getJavaType())) {
/*     */         
/* 254 */         importList.add("java.util.Date");
/* 255 */         importList.add("com.fasterxml.jackson.annotation.JsonFormat"); continue;
/*     */       } 
/* 257 */       if (!column.isSuperColumn() && "BigDecimal".equals(column.getJavaType()))
/*     */       {
/* 259 */         importList.add("java.math.BigDecimal");
/*     */       }
/*     */     } 
/* 262 */     return importList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDicts(GenTable genTable) {
/* 273 */     List<GenTableColumn> columns = genTable.getColumns();
/* 274 */     Set<String> dicts = new HashSet<>();
/* 275 */     addDicts(dicts, columns);
/* 276 */     if (StringUtils.isNotNull(genTable.getSubTable())) {
/*     */       
/* 278 */       List<GenTableColumn> subColumns = genTable.getSubTable().getColumns();
/* 279 */       addDicts(dicts, subColumns);
/*     */     } 
/* 281 */     return StringUtils.join(dicts, ", ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addDicts(Set<String> dicts, List<GenTableColumn> columns) {
/* 292 */     for (GenTableColumn column : columns) {
/*     */       
/* 294 */       if (!column.isSuperColumn() && StringUtils.isNotEmpty(column.getDictType()) && StringUtils.equalsAny(column
/* 295 */           .getHtmlType(), (CharSequence[])new String[] { "select", "radio", "checkbox"
/*     */           }))
/*     */       {
/* 298 */         dicts.add("'" + column.getDictType() + "'");
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
/*     */   public static String getPermissionPrefix(String moduleName, String businessName) {
/* 312 */     return StringUtils.format("{}:{}", new Object[] { moduleName, businessName });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getParentMenuId(JSONObject paramsObj) {
/* 323 */     if (StringUtils.isNotEmpty((Map)paramsObj) && paramsObj.containsKey("parentMenuId") && 
/* 324 */       StringUtils.isNotEmpty(paramsObj.getString("parentMenuId")))
/*     */     {
/* 326 */       return paramsObj.getString("parentMenuId");
/*     */     }
/* 328 */     return "3";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTreecode(JSONObject paramsObj) {
/* 339 */     if (paramsObj.containsKey("treeCode"))
/*     */     {
/* 341 */       return StringUtils.toCamelCase(paramsObj.getString("treeCode"));
/*     */     }
/* 343 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTreeParentCode(JSONObject paramsObj) {
/* 354 */     if (paramsObj.containsKey("treeParentCode"))
/*     */     {
/* 356 */       return StringUtils.toCamelCase(paramsObj.getString("treeParentCode"));
/*     */     }
/* 358 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTreeName(JSONObject paramsObj) {
/* 369 */     if (paramsObj.containsKey("treeName"))
/*     */     {
/* 371 */       return StringUtils.toCamelCase(paramsObj.getString("treeName"));
/*     */     }
/* 373 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getExpandColumn(GenTable genTable) {
/* 384 */     String options = genTable.getOptions();
/* 385 */     JSONObject paramsObj = JSON.parseObject(options);
/* 386 */     String treeName = paramsObj.getString("treeName");
/* 387 */     int num = 0;
/* 388 */     for (GenTableColumn column : genTable.getColumns()) {
/*     */       
/* 390 */       if (column.isList()) {
/*     */         
/* 392 */         num++;
/* 393 */         String columnName = column.getColumnName();
/* 394 */         if (columnName.equals(treeName)) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 400 */     return num;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/util/VelocityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */