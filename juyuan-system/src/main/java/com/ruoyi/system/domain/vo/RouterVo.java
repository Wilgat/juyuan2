/*     */ package com.ruoyi.system.domain.vo;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonInclude;
/*     */ import java.util.List;
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
/*     */ @JsonInclude(JsonInclude.Include.NON_EMPTY)
/*     */ public class RouterVo
/*     */ {
/*     */   private String name;
/*     */   private String path;
/*     */   private boolean hidden;
/*     */   private String redirect;
/*     */   private String component;
/*     */   private String query;
/*     */   private Boolean alwaysShow;
/*     */   private MetaVo meta;
/*     */   private List<RouterVo> children;
/*     */   
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  66 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPath() {
/*  71 */     return this.path;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPath(String path) {
/*  76 */     this.path = path;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHidden() {
/*  81 */     return this.hidden;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHidden(boolean hidden) {
/*  86 */     this.hidden = hidden;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRedirect() {
/*  91 */     return this.redirect;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRedirect(String redirect) {
/*  96 */     this.redirect = redirect;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getComponent() {
/* 101 */     return this.component;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComponent(String component) {
/* 106 */     this.component = component;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQuery() {
/* 111 */     return this.query;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQuery(String query) {
/* 116 */     this.query = query;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean getAlwaysShow() {
/* 121 */     return this.alwaysShow;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlwaysShow(Boolean alwaysShow) {
/* 126 */     this.alwaysShow = alwaysShow;
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaVo getMeta() {
/* 131 */     return this.meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMeta(MetaVo meta) {
/* 136 */     this.meta = meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RouterVo> getChildren() {
/* 141 */     return this.children;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChildren(List<RouterVo> children) {
/* 146 */     this.children = children;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/vo/RouterVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */