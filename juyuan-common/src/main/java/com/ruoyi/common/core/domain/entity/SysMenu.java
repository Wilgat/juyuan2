/*     */ package com.ruoyi.common.core.domain.entity;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class SysMenu
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long menuId;
/*     */   private String menuName;
/*     */   private String parentName;
/*     */   private Long parentId;
/*     */   private Integer orderNum;
/*     */   private String path;
/*     */   private String component;
/*     */   private String query;
/*     */   private String isFrame;
/*     */   private String isCache;
/*     */   private String menuType;
/*     */   private String visible;
/*     */   private String status;
/*     */   private String perms;
/*     */   private String icon;
/*  67 */   private List<SysMenu> children = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public Long getMenuId() {
/*  71 */     return this.menuId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuId(Long menuId) {
/*  76 */     this.menuId = menuId;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "菜单名称不能为空")
/*     */   @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
/*     */   public String getMenuName() {
/*  83 */     return this.menuName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuName(String menuName) {
/*  88 */     this.menuName = menuName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getParentName() {
/*  93 */     return this.parentName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentName(String parentName) {
/*  98 */     this.parentName = parentName;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getParentId() {
/* 103 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentId(Long parentId) {
/* 108 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull(message = "显示顺序不能为空")
/*     */   public Integer getOrderNum() {
/* 114 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrderNum(Integer orderNum) {
/* 119 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
/*     */   public String getPath() {
/* 125 */     return this.path;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPath(String path) {
/* 130 */     this.path = path;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
/*     */   public String getComponent() {
/* 136 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponent(String component) {
/* 141 */     this.component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQuery() {
/* 146 */     return this.query;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQuery(String query) {
/* 151 */     this.query = query;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsFrame() {
/* 156 */     return this.isFrame;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsFrame(String isFrame) {
/* 161 */     this.isFrame = isFrame;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIsCache() {
/* 166 */     return this.isCache;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsCache(String isCache) {
/* 171 */     this.isCache = isCache;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotBlank(message = "菜单类型不能为空")
/*     */   public String getMenuType() {
/* 177 */     return this.menuType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMenuType(String menuType) {
/* 182 */     this.menuType = menuType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getVisible() {
/* 187 */     return this.visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(String visible) {
/* 192 */     this.visible = visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 197 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 202 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
/*     */   public String getPerms() {
/* 208 */     return this.perms;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPerms(String perms) {
/* 213 */     this.perms = perms;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIcon() {
/* 218 */     return this.icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(String icon) {
/* 223 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SysMenu> getChildren() {
/* 228 */     return this.children;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildren(List<SysMenu> children) {
/* 233 */     this.children = children;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 238 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 239 */       .append("menuId", getMenuId())
/* 240 */       .append("menuName", getMenuName())
/* 241 */       .append("parentId", getParentId())
/* 242 */       .append("orderNum", getOrderNum())
/* 243 */       .append("path", getPath())
/* 244 */       .append("component", getComponent())
/* 245 */       .append("isFrame", getIsFrame())
/* 246 */       .append("IsCache", getIsCache())
/* 247 */       .append("menuType", getMenuType())
/* 248 */       .append("visible", getVisible())
/* 249 */       .append("status ", getStatus())
/* 250 */       .append("perms", getPerms())
/* 251 */       .append("icon", getIcon())
/* 252 */       .append("createBy", getCreateBy())
/* 253 */       .append("createTime", getCreateTime())
/* 254 */       .append("updateBy", getUpdateBy())
/* 255 */       .append("updateTime", getUpdateTime())
/* 256 */       .append("remark", getRemark())
/* 257 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/entity/SysMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */