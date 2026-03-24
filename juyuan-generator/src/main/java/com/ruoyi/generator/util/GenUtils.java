/*     */ package com.ruoyi.generator.util;
/*     */ 
/*     */ import com.ruoyi.common.constant.GenConstants;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.generator.config.GenConfig;
/*     */ import com.ruoyi.generator.domain.GenTable;
/*     */ import com.ruoyi.generator.domain.GenTableColumn;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.lang3.RegExUtils;
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
/*     */ public class GenUtils
/*     */ {
/*     */   public static void initTable(GenTable genTable, String operName) {
/*  23 */     genTable.setClassName(convertClassName(genTable.getTableName()));
/*  24 */     genTable.setPackageName(GenConfig.getPackageName());
/*  25 */     genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
/*  26 */     genTable.setBusinessName(getBusinessName(genTable.getTableName()));
/*  27 */     genTable.setFunctionName(replaceText(genTable.getTableComment()));
/*  28 */     genTable.setFunctionAuthor(GenConfig.getAuthor());
/*  29 */     genTable.setCreateBy(operName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initColumnField(GenTableColumn column, GenTable table) {
/*  37 */     String dataType = getDbType(column.getColumnType());
/*  38 */     String columnName = column.getColumnName();
/*  39 */     column.setTableId(table.getTableId());
/*  40 */     column.setCreateBy(table.getCreateBy());
/*     */     
/*  42 */     column.setJavaField(StringUtils.toCamelCase(columnName));
/*     */     
/*  44 */     column.setJavaType("String");
/*  45 */     column.setQueryType("EQ");
/*     */     
/*  47 */     if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
/*     */ 
/*     */       
/*  50 */       Integer columnLength = getColumnLength(column.getColumnType());
/*  51 */       String htmlType = (columnLength.intValue() >= 500 || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) ? "textarea" : "input";
/*  52 */       column.setHtmlType(htmlType);
/*     */     }
/*  54 */     else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
/*     */       
/*  56 */       column.setJavaType("Date");
/*  57 */       column.setHtmlType("datetime");
/*     */     }
/*  59 */     else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
/*     */       
/*  61 */       column.setHtmlType("input");
/*     */ 
/*     */       
/*  64 */       String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
/*  65 */       if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
/*     */         
/*  67 */         column.setJavaType("BigDecimal");
/*     */       
/*     */       }
/*  70 */       else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
/*     */         
/*  72 */         column.setJavaType("Integer");
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  77 */         column.setJavaType("Long");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  82 */     column.setIsInsert("1");
/*     */ 
/*     */     
/*  85 */     if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.isPk())
/*     */     {
/*  87 */       column.setIsEdit("1");
/*     */     }
/*     */     
/*  90 */     if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.isPk())
/*     */     {
/*  92 */       column.setIsList("1");
/*     */     }
/*     */     
/*  95 */     if (!arraysContains(GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk())
/*     */     {
/*  97 */       column.setIsQuery("1");
/*     */     }
/*     */ 
/*     */     
/* 101 */     if (StringUtils.endsWithIgnoreCase(columnName, "name"))
/*     */     {
/* 103 */       column.setQueryType("LIKE");
/*     */     }
/*     */     
/* 106 */     if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
/*     */       
/* 108 */       column.setHtmlType("radio");
/*     */     
/*     */     }
/* 111 */     else if (StringUtils.endsWithIgnoreCase(columnName, "type") || 
/* 112 */       StringUtils.endsWithIgnoreCase(columnName, "sex")) {
/*     */       
/* 114 */       column.setHtmlType("select");
/*     */     
/*     */     }
/* 117 */     else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
/*     */       
/* 119 */       column.setHtmlType("imageUpload");
/*     */     
/*     */     }
/* 122 */     else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
/*     */       
/* 124 */       column.setHtmlType("fileUpload");
/*     */     
/*     */     }
/* 127 */     else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
/*     */       
/* 129 */       column.setHtmlType("editor");
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
/*     */   public static boolean arraysContains(String[] arr, String targetValue) {
/* 142 */     return Arrays.<String>asList(arr).contains(targetValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getModuleName(String packageName) {
/* 153 */     int lastIndex = packageName.lastIndexOf(".");
/* 154 */     int nameLength = packageName.length();
/* 155 */     return StringUtils.substring(packageName, lastIndex + 1, nameLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBusinessName(String tableName) {
/* 166 */     int lastIndex = tableName.lastIndexOf("_");
/* 167 */     int nameLength = tableName.length();
/* 168 */     return StringUtils.substring(tableName, lastIndex + 1, nameLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String convertClassName(String tableName) {
/* 179 */     boolean autoRemovePre = GenConfig.getAutoRemovePre();
/* 180 */     String tablePrefix = GenConfig.getTablePrefix();
/* 181 */     if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
/*     */       
/* 183 */       String[] searchList = StringUtils.split(tablePrefix, ",");
/* 184 */       tableName = replaceFirst(tableName, searchList);
/*     */     } 
/* 186 */     return StringUtils.convertToCamelCase(tableName);
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
/*     */   public static String replaceFirst(String replacementm, String[] searchList) {
/* 198 */     String text = replacementm;
/* 199 */     for (String searchString : searchList) {
/*     */       
/* 201 */       if (replacementm.startsWith(searchString)) {
/*     */         
/* 203 */         text = replacementm.replaceFirst(searchString, "");
/*     */         break;
/*     */       } 
/*     */     } 
/* 207 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replaceText(String text) {
/* 218 */     return RegExUtils.replaceAll(text, "(?:表|若依)", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDbType(String columnType) {
/* 229 */     if (StringUtils.indexOf(columnType, "(") > 0)
/*     */     {
/* 231 */       return StringUtils.substringBefore(columnType, "(");
/*     */     }
/*     */ 
/*     */     
/* 235 */     return columnType;
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
/*     */   public static Integer getColumnLength(String columnType) {
/* 247 */     if (StringUtils.indexOf(columnType, "(") > 0) {
/*     */       
/* 249 */       String length = StringUtils.substringBetween(columnType, "(", ")");
/* 250 */       return Integer.valueOf(length);
/*     */     } 
/*     */ 
/*     */     
/* 254 */     return Integer.valueOf(0);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/util/GenUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */