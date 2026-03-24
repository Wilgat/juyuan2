/*     */ package com.ruoyi.generator.domain;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import javax.validation.constraints.NotBlank;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenTableColumn
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long columnId;
/*     */   private Long tableId;
/*     */   private String columnName;
/*     */   private String columnComment;
/*     */   private String columnType;
/*     */   private String javaType;
/*     */   @NotBlank(message = "Java属性不能为空")
/*     */   private String javaField;
/*     */   private String isPk;
/*     */   private String isIncrement;
/*     */   private String isRequired;
/*     */   private String isInsert;
/*     */   private String isEdit;
/*     */   private String isList;
/*     */   private String isQuery;
/*     */   private String queryType;
/*     */   private String htmlType;
/*     */   private String dictType;
/*     */   private Integer sort;
/*     */   
/*     */   public void setColumnId(Long columnId) {
/*  73 */     this.columnId = columnId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getColumnId() {
/*  78 */     return this.columnId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableId(Long tableId) {
/*  83 */     this.tableId = tableId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getTableId() {
/*  88 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnName(String columnName) {
/*  93 */     this.columnName = columnName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnName() {
/*  98 */     return this.columnName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnComment(String columnComment) {
/* 103 */     this.columnComment = columnComment;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnComment() {
/* 108 */     return this.columnComment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnType(String columnType) {
/* 113 */     this.columnType = columnType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColumnType() {
/* 118 */     return this.columnType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJavaType(String javaType) {
/* 123 */     this.javaType = javaType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJavaType() {
/* 128 */     return this.javaType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJavaField(String javaField) {
/* 133 */     this.javaField = javaField;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJavaField() {
/* 138 */     return this.javaField;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCapJavaField() {
/* 143 */     return StringUtils.capitalize(this.javaField);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsPk(String isPk) {
/* 148 */     this.isPk = isPk;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsPk() {
/* 153 */     return this.isPk;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPk() {
/* 158 */     return isPk(this.isPk);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPk(String isPk) {
/* 163 */     return (isPk != null && StringUtils.equals("1", isPk));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsIncrement() {
/* 168 */     return this.isIncrement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsIncrement(String isIncrement) {
/* 173 */     this.isIncrement = isIncrement;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIncrement() {
/* 178 */     return isIncrement(this.isIncrement);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIncrement(String isIncrement) {
/* 183 */     return (isIncrement != null && StringUtils.equals("1", isIncrement));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsRequired(String isRequired) {
/* 188 */     this.isRequired = isRequired;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsRequired() {
/* 193 */     return this.isRequired;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRequired() {
/* 198 */     return isRequired(this.isRequired);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRequired(String isRequired) {
/* 203 */     return (isRequired != null && StringUtils.equals("1", isRequired));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsInsert(String isInsert) {
/* 208 */     this.isInsert = isInsert;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsInsert() {
/* 213 */     return this.isInsert;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsert() {
/* 218 */     return isInsert(this.isInsert);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsert(String isInsert) {
/* 223 */     return (isInsert != null && StringUtils.equals("1", isInsert));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsEdit(String isEdit) {
/* 228 */     this.isEdit = isEdit;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsEdit() {
/* 233 */     return this.isEdit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEdit() {
/* 238 */     return isInsert(this.isEdit);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEdit(String isEdit) {
/* 243 */     return (isEdit != null && StringUtils.equals("1", isEdit));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsList(String isList) {
/* 248 */     this.isList = isList;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsList() {
/* 253 */     return this.isList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isList() {
/* 258 */     return isList(this.isList);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isList(String isList) {
/* 263 */     return (isList != null && StringUtils.equals("1", isList));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsQuery(String isQuery) {
/* 268 */     this.isQuery = isQuery;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsQuery() {
/* 273 */     return this.isQuery;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuery() {
/* 278 */     return isQuery(this.isQuery);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isQuery(String isQuery) {
/* 283 */     return (isQuery != null && StringUtils.equals("1", isQuery));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQueryType(String queryType) {
/* 288 */     this.queryType = queryType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQueryType() {
/* 293 */     return this.queryType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHtmlType() {
/* 298 */     return this.htmlType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHtmlType(String htmlType) {
/* 303 */     this.htmlType = htmlType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictType(String dictType) {
/* 308 */     this.dictType = dictType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDictType() {
/* 313 */     return this.dictType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSort(Integer sort) {
/* 318 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getSort() {
/* 323 */     return this.sort;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperColumn() {
/* 328 */     return isSuperColumn(this.javaField);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSuperColumn(String javaField) {
/* 333 */     return StringUtils.equalsAnyIgnoreCase(javaField, new CharSequence[] { "createBy", "createTime", "updateBy", "updateTime", "remark", "parentName", "parentId", "orderNum", "ancestors" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsableColumn() {
/* 342 */     return isUsableColumn(this.javaField);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isUsableColumn(String javaField) {
/* 348 */     return StringUtils.equalsAnyIgnoreCase(javaField, new CharSequence[] { "parentId", "orderNum", "remark" });
/*     */   }
/*     */ 
/*     */   
/*     */   public String readConverterExp() {
/* 353 */     String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
/* 354 */     StringBuffer sb = new StringBuffer();
/* 355 */     if (StringUtils.isNotEmpty(remarks)) {
/*     */       
/* 357 */       for (String value : remarks.split(" ")) {
/*     */         
/* 359 */         if (StringUtils.isNotEmpty(value)) {
/*     */           
/* 361 */           Object startStr = value.subSequence(0, 1);
/* 362 */           String endStr = value.substring(1);
/* 363 */           sb.append("").append(startStr).append("=").append(endStr).append(",");
/*     */         } 
/*     */       } 
/* 366 */       return sb.deleteCharAt(sb.length() - 1).toString();
/*     */     } 
/*     */ 
/*     */     
/* 370 */     return this.columnComment;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/domain/GenTableColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */