/*     */ package com.ruoyi.system.domain;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.NotNull;
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
/*     */ public class SysPost
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "岗位序号", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long postId;
/*     */   @Excel(name = "岗位编码")
/*     */   private String postCode;
/*     */   @Excel(name = "岗位名称")
/*     */   private String postName;
/*     */   @Excel(name = "岗位排序")
/*     */   private Integer postSort;
/*     */   @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
/*     */   private String status;
/*     */   private boolean flag = false;
/*     */   
/*     */   public Long getPostId() {
/*  46 */     return this.postId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostId(Long postId) {
/*  51 */     this.postId = postId;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "岗位编码不能为空")
/*     */   @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
/*     */   public String getPostCode() {
/*  58 */     return this.postCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostCode(String postCode) {
/*  63 */     this.postCode = postCode;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "岗位名称不能为空")
/*     */   @Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
/*     */   public String getPostName() {
/*  70 */     return this.postName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostName(String postName) {
/*  75 */     this.postName = postName;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull(message = "显示顺序不能为空")
/*     */   public Integer getPostSort() {
/*  81 */     return this.postSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostSort(Integer postSort) {
/*  86 */     this.postSort = postSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/*  91 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/*  96 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlag() {
/* 101 */     return this.flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlag(boolean flag) {
/* 106 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 112 */       .append("postId", getPostId())
/* 113 */       .append("postCode", getPostCode())
/* 114 */       .append("postName", getPostName())
/* 115 */       .append("postSort", getPostSort())
/* 116 */       .append("status", getStatus())
/* 117 */       .append("createBy", getCreateBy())
/* 118 */       .append("createTime", getCreateTime())
/* 119 */       .append("updateBy", getUpdateBy())
/* 120 */       .append("updateTime", getUpdateTime())
/* 121 */       .append("remark", getRemark())
/* 122 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysPost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */