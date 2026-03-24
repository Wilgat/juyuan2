/*     */ package com.ruoyi.common.core.domain.entity;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SysRole
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long roleId;
/*     */   @Excel(name = "角色名称")
/*     */   private String roleName;
/*     */   @Excel(name = "角色权限")
/*     */   private String roleKey;
/*     */   @Excel(name = "角色排序")
/*     */   private Integer roleSort;
/*     */   @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限,5=仅本人数据权限")
/*     */   private String dataScope;
/*     */   private boolean menuCheckStrictly;
/*     */   private boolean deptCheckStrictly;
/*     */   @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
/*     */   private String status;
/*     */   private String delFlag;
/*     */   private boolean flag = false;
/*     */   private Long[] menuIds;
/*     */   private Long[] deptIds;
/*     */   private Set<String> permissions;
/*     */   
/*     */   public SysRole() {}
/*     */   
/*     */   public SysRole(Long roleId) {
/*  74 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getRoleId() {
/*  79 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleId(Long roleId) {
/*  84 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdmin() {
/*  89 */     return isAdmin(this.roleId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAdmin(Long roleId) {
/*  94 */     return (roleId != null && 1L == roleId.longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "角色名称不能为空")
/*     */   @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
/*     */   public String getRoleName() {
/* 101 */     return this.roleName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleName(String roleName) {
/* 106 */     this.roleName = roleName;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "权限字符不能为空")
/*     */   @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
/*     */   public String getRoleKey() {
/* 113 */     return this.roleKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleKey(String roleKey) {
/* 118 */     this.roleKey = roleKey;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull(message = "显示顺序不能为空")
/*     */   public Integer getRoleSort() {
/* 124 */     return this.roleSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleSort(Integer roleSort) {
/* 129 */     this.roleSort = roleSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDataScope() {
/* 134 */     return this.dataScope;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDataScope(String dataScope) {
/* 139 */     this.dataScope = dataScope;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMenuCheckStrictly() {
/* 144 */     return this.menuCheckStrictly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuCheckStrictly(boolean menuCheckStrictly) {
/* 149 */     this.menuCheckStrictly = menuCheckStrictly;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDeptCheckStrictly() {
/* 154 */     return this.deptCheckStrictly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptCheckStrictly(boolean deptCheckStrictly) {
/* 159 */     this.deptCheckStrictly = deptCheckStrictly;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 164 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 169 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDelFlag() {
/* 174 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDelFlag(String delFlag) {
/* 179 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlag() {
/* 184 */     return this.flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlag(boolean flag) {
/* 189 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long[] getMenuIds() {
/* 194 */     return this.menuIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuIds(Long[] menuIds) {
/* 199 */     this.menuIds = menuIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long[] getDeptIds() {
/* 204 */     return this.deptIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptIds(Long[] deptIds) {
/* 209 */     this.deptIds = deptIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getPermissions() {
/* 214 */     return this.permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPermissions(Set<String> permissions) {
/* 219 */     this.permissions = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 224 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 225 */       .append("roleId", getRoleId())
/* 226 */       .append("roleName", getRoleName())
/* 227 */       .append("roleKey", getRoleKey())
/* 228 */       .append("roleSort", getRoleSort())
/* 229 */       .append("dataScope", getDataScope())
/* 230 */       .append("menuCheckStrictly", isMenuCheckStrictly())
/* 231 */       .append("deptCheckStrictly", isDeptCheckStrictly())
/* 232 */       .append("status", getStatus())
/* 233 */       .append("delFlag", getDelFlag())
/* 234 */       .append("createBy", getCreateBy())
/* 235 */       .append("createTime", getCreateTime())
/* 236 */       .append("updateBy", getUpdateBy())
/* 237 */       .append("updateTime", getUpdateTime())
/* 238 */       .append("remark", getRemark())
/* 239 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysRole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */