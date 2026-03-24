/*     */ package com.ruoyi.common.core.domain.model;
/*     */ 
/*     */ import com.alibaba.fastjson2.annotation.JSONField;
/*     */ import com.ruoyi.common.core.domain.entity.SysUser;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.springframework.security.core.GrantedAuthority;
/*     */ import org.springframework.security.core.userdetails.UserDetails;
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
/*     */ public class LoginUser
/*     */   implements UserDetails
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long userId;
/*     */   private Long deptId;
/*     */   private String token;
/*     */   private Long loginTime;
/*     */   private Long expireTime;
/*     */   private String ipaddr;
/*     */   private String loginLocation;
/*     */   private String browser;
/*     */   private String os;
/*     */   private Set<String> permissions;
/*     */   private SysUser user;
/*     */   
/*     */   public LoginUser() {}
/*     */   
/*     */   public LoginUser(SysUser user, Set<String> permissions) {
/*  80 */     this.user = user;
/*  81 */     this.permissions = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
/*  86 */     this.userId = userId;
/*  87 */     this.deptId = deptId;
/*  88 */     this.user = user;
/*  89 */     this.permissions = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getUserId() {
/*  94 */     return this.userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserId(Long userId) {
/*  99 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getDeptId() {
/* 104 */     return this.deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptId(Long deptId) {
/* 109 */     this.deptId = deptId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToken() {
/* 114 */     return this.token;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToken(String token) {
/* 119 */     this.token = token;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JSONField(serialize = false)
/*     */   public String getPassword() {
/* 126 */     return this.user.getPassword();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 132 */     return this.user.getUserName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JSONField(serialize = false)
/*     */   public boolean isAccountNonExpired() {
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JSONField(serialize = false)
/*     */   public boolean isAccountNonLocked() {
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JSONField(serialize = false)
/*     */   public boolean isCredentialsNonExpired() {
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JSONField(serialize = false)
/*     */   public boolean isEnabled() {
/* 178 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getLoginTime() {
/* 183 */     return this.loginTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginTime(Long loginTime) {
/* 188 */     this.loginTime = loginTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIpaddr() {
/* 193 */     return this.ipaddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIpaddr(String ipaddr) {
/* 198 */     this.ipaddr = ipaddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLoginLocation() {
/* 203 */     return this.loginLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginLocation(String loginLocation) {
/* 208 */     this.loginLocation = loginLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBrowser() {
/* 213 */     return this.browser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBrowser(String browser) {
/* 218 */     this.browser = browser;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOs() {
/* 223 */     return this.os;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOs(String os) {
/* 228 */     this.os = os;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getExpireTime() {
/* 233 */     return this.expireTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExpireTime(Long expireTime) {
/* 238 */     this.expireTime = expireTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getPermissions() {
/* 243 */     return this.permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPermissions(Set<String> permissions) {
/* 248 */     this.permissions = permissions;
/*     */   }
/*     */ 
/*     */   
/*     */   public SysUser getUser() {
/* 253 */     return this.user;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUser(SysUser user) {
/* 258 */     this.user = user;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<? extends GrantedAuthority> getAuthorities() {
/* 264 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/model/LoginUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */