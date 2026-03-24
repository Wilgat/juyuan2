/*     */ package com.ruoyi.common.core.domain.entity;
/*     */ 
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.annotation.Excels;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.xss.Xss;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.validation.constraints.Email;
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
/*     */ public class SysUser
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
/*     */   private Long userId;
/*     */   @Excel(name = "部门编号", type = Excel.Type.IMPORT)
/*     */   private Long deptId;
/*     */   @Excel(name = "登录名称")
/*     */   private String userName;
/*     */   @Excel(name = "用户名称")
/*     */   private String nickName;
/*     */   @Excel(name = "用户邮箱")
/*     */   private String email;
/*     */   @Excel(name = "手机号码")
/*     */   private String phonenumber;
/*     */   @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
/*     */   private String sex;
/*     */   private String avatar;
/*     */   private String password;
/*     */   @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
/*     */   private String status;
/*     */   private String delFlag;
/*     */   @Excel(name = "最后登录IP", type = Excel.Type.EXPORT)
/*     */   private String loginIp;
/*     */   @Excel(name = "最后登录时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
/*     */   private Date loginDate;
/*     */   @Excels({@Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT), @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)})
/*     */   private SysDept dept;
/*     */   private List<SysRole> roles;
/*     */   private Long[] roleIds;
/*     */   private Long[] postIds;
/*     */   private Long roleId;
/*     */   private String cid;
/*     */   
/*     */   public SysUser() {}
/*     */   
/*     */   public SysUser(Long userId) {
/* 101 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getUserId() {
/* 106 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserId(Long userId) {
/* 111 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdmin() {
/* 116 */     return isAdmin(this.userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAdmin(Long userId) {
/* 121 */     return (userId != null && 1L == userId.longValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getDeptId() {
/* 126 */     return this.deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptId(Long deptId) {
/* 131 */     this.deptId = deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   @Xss(message = "用户昵称不能包含脚本字符")
/*     */   @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
/*     */   public String getNickName() {
/* 138 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNickName(String nickName) {
/* 143 */     this.nickName = nickName;
/*     */   }
/*     */ 
/*     */   
/*     */   @Xss(message = "用户账号不能包含脚本字符")
/*     */   @NotBlank(message = "用户账号不能为空")
/*     */   @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
/*     */   public String getUserName() {
/* 151 */     return this.userName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserName(String userName) {
/* 156 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   
/*     */   @Email(message = "邮箱格式不正确")
/*     */   @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
/*     */   public String getEmail() {
/* 163 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEmail(String email) {
/* 168 */     this.email = email;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
/*     */   public String getPhonenumber() {
/* 174 */     return this.phonenumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhonenumber(String phonenumber) {
/* 179 */     this.phonenumber = phonenumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSex() {
/* 184 */     return this.sex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSex(String sex) {
/* 189 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAvatar() {
/* 194 */     return this.avatar;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAvatar(String avatar) {
/* 199 */     this.avatar = avatar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 204 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/* 209 */     this.password = password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 214 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 219 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDelFlag() {
/* 224 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDelFlag(String delFlag) {
/* 229 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLoginIp() {
/* 234 */     return this.loginIp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginIp(String loginIp) {
/* 239 */     this.loginIp = loginIp;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getLoginDate() {
/* 244 */     return this.loginDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginDate(Date loginDate) {
/* 249 */     this.loginDate = loginDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public SysDept getDept() {
/* 254 */     return this.dept;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDept(SysDept dept) {
/* 259 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SysRole> getRoles() {
/* 264 */     return this.roles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoles(List<SysRole> roles) {
/* 269 */     this.roles = roles;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long[] getRoleIds() {
/* 274 */     return this.roleIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleIds(Long[] roleIds) {
/* 279 */     this.roleIds = roleIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long[] getPostIds() {
/* 284 */     return this.postIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPostIds(Long[] postIds) {
/* 289 */     this.postIds = postIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getRoleId() {
/* 294 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoleId(Long roleId) {
/* 299 */     this.roleId = roleId;
/*     */   }
/*     */   
/*     */   public String getCid() {
/* 303 */     return this.cid;
/*     */   }
/*     */   
/*     */   public void setCid(String cid) {
/* 307 */     this.cid = cid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 312 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 313 */       .append("userId", getUserId())
/* 314 */       .append("deptId", getDeptId())
/* 315 */       .append("userName", getUserName())
/* 316 */       .append("nickName", getNickName())
/* 317 */       .append("email", getEmail())
/* 318 */       .append("phonenumber", getPhonenumber())
/* 319 */       .append("sex", getSex())
/* 320 */       .append("avatar", getAvatar())
/* 321 */       .append("password", getPassword())
/* 322 */       .append("status", getStatus())
/* 323 */       .append("delFlag", getDelFlag())
/* 324 */       .append("loginIp", getLoginIp())
/* 325 */       .append("loginDate", getLoginDate())
/* 326 */       .append("createBy", getCreateBy())
/* 327 */       .append("createTime", getCreateTime())
/* 328 */       .append("updateBy", getUpdateBy())
/* 329 */       .append("updateTime", getUpdateTime())
/* 330 */       .append("remark", getRemark())
/* 331 */       .append("dept", getDept())
/* 332 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */