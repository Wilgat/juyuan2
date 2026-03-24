/*    */ package com.ruoyi.common.core.domain.entity;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Excel;
/*    */ import com.ruoyi.common.core.domain.BaseEntity;
/*    */ import javax.validation.constraints.NotBlank;
/*    */ import javax.validation.constraints.Pattern;
/*    */ import javax.validation.constraints.Size;
/*    */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*    */ import org.apache.commons.lang3.builder.ToStringStyle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SysDictType
/*    */   extends BaseEntity
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Excel(name = "字典主键", cellType = Excel.ColumnType.NUMERIC)
/*    */   private Long dictId;
/*    */   @Excel(name = "字典名称")
/*    */   private String dictName;
/*    */   @Excel(name = "字典类型")
/*    */   private String dictType;
/*    */   @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
/*    */   private String status;
/*    */   
/*    */   public Long getDictId() {
/* 39 */     return this.dictId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDictId(Long dictId) {
/* 44 */     this.dictId = dictId;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotBlank(message = "字典名称不能为空")
/*    */   @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
/*    */   public String getDictName() {
/* 51 */     return this.dictName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDictName(String dictName) {
/* 56 */     this.dictName = dictName;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotBlank(message = "字典类型不能为空")
/*    */   @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
/*    */   @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
/*    */   public String getDictType() {
/* 64 */     return this.dictType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDictType(String dictType) {
/* 69 */     this.dictType = dictType;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStatus() {
/* 74 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatus(String status) {
/* 79 */     this.status = status;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 85 */       .append("dictId", getDictId())
/* 86 */       .append("dictName", getDictName())
/* 87 */       .append("dictType", getDictType())
/* 88 */       .append("status", getStatus())
/* 89 */       .append("createBy", getCreateBy())
/* 90 */       .append("createTime", getCreateTime())
/* 91 */       .append("updateBy", getUpdateBy())
/* 92 */       .append("updateTime", getUpdateTime())
/* 93 */       .append("remark", getRemark())
/* 94 */       .toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysDictType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */