/*     */ package com.ruoyi.common.core.domain.entity;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Size;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
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
/*     */ public class SysDictData
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "字典编码", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long dictCode;
/*     */   @Excel(name = "字典排序", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long dictSort;
/*     */   @Excel(name = "字典标签")
/*     */   private String dictLabel;
/*     */   @Excel(name = "字典键值")
/*     */   private String dictValue;
/*     */   @Excel(name = "字典类型")
/*     */   private String dictType;
/*     */   private String cssClass;
/*     */   private String listClass;
/*     */   @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
/*     */   private String isDefault;
/*     */   @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
/*     */   private String status;
/*     */   
/*     */   public Long getDictCode() {
/*  57 */     return this.dictCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictCode(Long dictCode) {
/*  62 */     this.dictCode = dictCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getDictSort() {
/*  67 */     return this.dictSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictSort(Long dictSort) {
/*  72 */     this.dictSort = dictSort;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "字典标签不能为空")
/*     */   @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
/*     */   public String getDictLabel() {
/*  79 */     return this.dictLabel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictLabel(String dictLabel) {
/*  84 */     this.dictLabel = dictLabel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "字典键值不能为空")
/*     */   @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
/*     */   public String getDictValue() {
/*  91 */     return this.dictValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictValue(String dictValue) {
/*  96 */     this.dictValue = dictValue;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "字典类型不能为空")
/*     */   @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
/*     */   public String getDictType() {
/* 103 */     return this.dictType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDictType(String dictType) {
/* 108 */     this.dictType = dictType;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
/*     */   public String getCssClass() {
/* 114 */     return this.cssClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCssClass(String cssClass) {
/* 119 */     this.cssClass = cssClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getListClass() {
/* 124 */     return this.listClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListClass(String listClass) {
/* 129 */     this.listClass = listClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDefault() {
/* 134 */     return "Y".equals(this.isDefault);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsDefault() {
/* 139 */     return this.isDefault;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsDefault(String isDefault) {
/* 144 */     this.isDefault = isDefault;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 149 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 154 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 160 */       .append("dictCode", getDictCode())
/* 161 */       .append("dictSort", getDictSort())
/* 162 */       .append("dictLabel", getDictLabel())
/* 163 */       .append("dictValue", getDictValue())
/* 164 */       .append("dictType", getDictType())
/* 165 */       .append("cssClass", getCssClass())
/* 166 */       .append("listClass", getListClass())
/* 167 */       .append("isDefault", getIsDefault())
/* 168 */       .append("status", getStatus())
/* 169 */       .append("createBy", getCreateBy())
/* 170 */       .append("createTime", getCreateTime())
/* 171 */       .append("updateBy", getUpdateBy())
/* 172 */       .append("updateTime", getUpdateTime())
/* 173 */       .append("remark", getRemark())
/* 174 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysDictData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */