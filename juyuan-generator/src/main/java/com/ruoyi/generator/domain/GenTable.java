/*     */ package com.ruoyi.generator.domain;
/*     */ 
/*     */ import com.ruoyi.common.constant.GenConstants;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.util.List;
/*     */ import javax.validation.Valid;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import org.apache.commons.lang3.ArrayUtils;
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
/*     */ public class GenTable
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long tableId;
/*     */   @NotBlank(message = "表名称不能为空")
/*     */   private String tableName;
/*     */   @NotBlank(message = "表描述不能为空")
/*     */   private String tableComment;
/*     */   private String subTableName;
/*     */   private String subTableFkName;
/*     */   @NotBlank(message = "实体类名称不能为空")
/*     */   private String className;
/*     */   private String tplCategory;
/*     */   @NotBlank(message = "生成包路径不能为空")
/*     */   private String packageName;
/*     */   @NotBlank(message = "生成模块名不能为空")
/*     */   private String moduleName;
/*     */   @NotBlank(message = "生成业务名不能为空")
/*     */   private String businessName;
/*     */   @NotBlank(message = "生成功能名不能为空")
/*     */   private String functionName;
/*     */   @NotBlank(message = "作者不能为空")
/*     */   private String functionAuthor;
/*     */   private String genType;
/*     */   private String genPath;
/*     */   private GenTableColumn pkColumn;
/*     */   private GenTable subTable;
/*     */   @Valid
/*     */   private List<GenTableColumn> columns;
/*     */   private String options;
/*     */   private String treeCode;
/*     */   private String treeParentCode;
/*     */   private String treeName;
/*     */   private String parentMenuId;
/*     */   private String parentMenuName;
/*     */   
/*     */   public Long getTableId() {
/* 100 */     return this.tableId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableId(Long tableId) {
/* 105 */     this.tableId = tableId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableName() {
/* 110 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableName(String tableName) {
/* 115 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTableComment() {
/* 120 */     return this.tableComment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTableComment(String tableComment) {
/* 125 */     this.tableComment = tableComment;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubTableName() {
/* 130 */     return this.subTableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubTableName(String subTableName) {
/* 135 */     this.subTableName = subTableName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubTableFkName() {
/* 140 */     return this.subTableFkName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubTableFkName(String subTableFkName) {
/* 145 */     this.subTableFkName = subTableFkName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 150 */     return this.className;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClassName(String className) {
/* 155 */     this.className = className;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTplCategory() {
/* 160 */     return this.tplCategory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTplCategory(String tplCategory) {
/* 165 */     this.tplCategory = tplCategory;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPackageName() {
/* 170 */     return this.packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 175 */     this.packageName = packageName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModuleName() {
/* 180 */     return this.moduleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModuleName(String moduleName) {
/* 185 */     this.moduleName = moduleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBusinessName() {
/* 190 */     return this.businessName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusinessName(String businessName) {
/* 195 */     this.businessName = businessName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 200 */     return this.functionName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFunctionName(String functionName) {
/* 205 */     this.functionName = functionName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFunctionAuthor() {
/* 210 */     return this.functionAuthor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFunctionAuthor(String functionAuthor) {
/* 215 */     this.functionAuthor = functionAuthor;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGenType() {
/* 220 */     return this.genType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenType(String genType) {
/* 225 */     this.genType = genType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGenPath() {
/* 230 */     return this.genPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGenPath(String genPath) {
/* 235 */     this.genPath = genPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenTableColumn getPkColumn() {
/* 240 */     return this.pkColumn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPkColumn(GenTableColumn pkColumn) {
/* 245 */     this.pkColumn = pkColumn;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenTable getSubTable() {
/* 250 */     return this.subTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubTable(GenTable subTable) {
/* 255 */     this.subTable = subTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<GenTableColumn> getColumns() {
/* 260 */     return this.columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumns(List<GenTableColumn> columns) {
/* 265 */     this.columns = columns;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOptions() {
/* 270 */     return this.options;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptions(String options) {
/* 275 */     this.options = options;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTreeCode() {
/* 280 */     return this.treeCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTreeCode(String treeCode) {
/* 285 */     this.treeCode = treeCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTreeParentCode() {
/* 290 */     return this.treeParentCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTreeParentCode(String treeParentCode) {
/* 295 */     this.treeParentCode = treeParentCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTreeName() {
/* 300 */     return this.treeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTreeName(String treeName) {
/* 305 */     this.treeName = treeName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentMenuId() {
/* 310 */     return this.parentMenuId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentMenuId(String parentMenuId) {
/* 315 */     this.parentMenuId = parentMenuId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentMenuName() {
/* 320 */     return this.parentMenuName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentMenuName(String parentMenuName) {
/* 325 */     this.parentMenuName = parentMenuName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSub() {
/* 330 */     return isSub(this.tplCategory);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSub(String tplCategory) {
/* 335 */     return (tplCategory != null && StringUtils.equals("sub", tplCategory));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTree() {
/* 340 */     return isTree(this.tplCategory);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTree(String tplCategory) {
/* 345 */     return (tplCategory != null && StringUtils.equals("tree", tplCategory));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrud() {
/* 350 */     return isCrud(this.tplCategory);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCrud(String tplCategory) {
/* 355 */     return (tplCategory != null && StringUtils.equals("crud", tplCategory));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperColumn(String javaField) {
/* 360 */     return isSuperColumn(this.tplCategory, javaField);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSuperColumn(String tplCategory, String javaField) {
/* 365 */     if (isTree(tplCategory))
/*     */     {
/* 367 */       return StringUtils.equalsAnyIgnoreCase(javaField, 
/* 368 */           (CharSequence[])ArrayUtils.addAll((Object[])GenConstants.TREE_ENTITY, (Object[])GenConstants.BASE_ENTITY));
/*     */     }
/* 370 */     return StringUtils.equalsAnyIgnoreCase(javaField, (CharSequence[])GenConstants.BASE_ENTITY);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/domain/GenTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */