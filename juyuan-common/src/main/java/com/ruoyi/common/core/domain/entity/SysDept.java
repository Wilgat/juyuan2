/*     */ package com.ruoyi.common.core.domain.entity;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.validation.constraints.Email;
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
/*     */ public class SysDept
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long deptId;
/*     */   private Long parentId;
/*     */   private String ancestors;
/*     */   private String deptName;
/*     */   private Integer orderNum;
/*     */   private String leader;
/*     */   private String phone;
/*     */   private String email;
/*     */   private String status;
/*     */   private String delFlag;
/*     */   private String parentName;
/*  56 */   private List<SysDept> children = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public Long getDeptId() {
/*  60 */     return this.deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptId(Long deptId) {
/*  65 */     this.deptId = deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getParentId() {
/*  70 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentId(Long parentId) {
/*  75 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAncestors() {
/*  80 */     return this.ancestors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAncestors(String ancestors) {
/*  85 */     this.ancestors = ancestors;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "部门名称不能为空")
/*     */   @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
/*     */   public String getDeptName() {
/*  92 */     return this.deptName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptName(String deptName) {
/*  97 */     this.deptName = deptName;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull(message = "显示顺序不能为空")
/*     */   public Integer getOrderNum() {
/* 103 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrderNum(Integer orderNum) {
/* 108 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLeader() {
/* 113 */     return this.leader;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeader(String leader) {
/* 118 */     this.leader = leader;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
/*     */   public String getPhone() {
/* 124 */     return this.phone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhone(String phone) {
/* 129 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   
/*     */   @Email(message = "邮箱格式不正确")
/*     */   @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
/*     */   public String getEmail() {
/* 136 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmail(String email) {
/* 141 */     this.email = email;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 146 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 151 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDelFlag() {
/* 156 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDelFlag(String delFlag) {
/* 161 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentName() {
/* 166 */     return this.parentName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentName(String parentName) {
/* 171 */     this.parentName = parentName;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SysDept> getChildren() {
/* 176 */     return this.children;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildren(List<SysDept> children) {
/* 181 */     this.children = children;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 186 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 187 */       .append("deptId", getDeptId())
/* 188 */       .append("parentId", getParentId())
/* 189 */       .append("ancestors", getAncestors())
/* 190 */       .append("deptName", getDeptName())
/* 191 */       .append("orderNum", getOrderNum())
/* 192 */       .append("leader", getLeader())
/* 193 */       .append("phone", getPhone())
/* 194 */       .append("email", getEmail())
/* 195 */       .append("status", getStatus())
/* 196 */       .append("delFlag", getDelFlag())
/* 197 */       .append("createBy", getCreateBy())
/* 198 */       .append("createTime", getCreateTime())
/* 199 */       .append("updateBy", getUpdateBy())
/* 200 */       .append("updateTime", getUpdateTime())
/* 201 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysDept.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */