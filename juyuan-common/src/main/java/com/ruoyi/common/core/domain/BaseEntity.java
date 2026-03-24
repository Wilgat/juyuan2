/*     */ package com.ruoyi.common.core.domain;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonFormat;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonInclude;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class BaseEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @JsonIgnore
/*     */   private String searchValue;
/*     */   private String createBy;
/*     */   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
/*     */   private Date createTime;
/*     */   private String updateBy;
/*     */   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
/*     */   private Date updateTime;
/*     */   private String remark;
/*     */   @JsonInclude(JsonInclude.Include.NON_EMPTY)
/*     */   private Map<String, Object> params;
/*     */   
/*     */   public String getSearchValue() {
/*  47 */     return this.searchValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSearchValue(String searchValue) {
/*  52 */     this.searchValue = searchValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreateBy() {
/*  57 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreateBy(String createBy) {
/*  62 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getCreateTime() {
/*  67 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreateTime(Date createTime) {
/*  72 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUpdateBy() {
/*  77 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUpdateBy(String updateBy) {
/*  82 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getUpdateTime() {
/*  87 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUpdateTime(Date updateTime) {
/*  92 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRemark() {
/*  97 */     return this.remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRemark(String remark) {
/* 102 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Object> getParams() {
/* 107 */     if (this.params == null)
/*     */     {
/* 109 */       this.params = new HashMap<>();
/*     */     }
/* 111 */     return this.params;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParams(Map<String, Object> params) {
/* 116 */     this.params = params;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/BaseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */