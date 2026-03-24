/*     */ package com.ruoyi.system.domain.vo;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
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
/*     */ public class MetaVo
/*     */ {
/*     */   private String title;
/*     */   private String icon;
/*     */   private boolean noCache;
/*     */   private String link;
/*     */   
/*     */   public MetaVo() {}
/*     */   
/*     */   public MetaVo(String title, String icon) {
/*  38 */     this.title = title;
/*  39 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaVo(String title, String icon, boolean noCache) {
/*  44 */     this.title = title;
/*  45 */     this.icon = icon;
/*  46 */     this.noCache = noCache;
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaVo(String title, String icon, String link) {
/*  51 */     this.title = title;
/*  52 */     this.icon = icon;
/*  53 */     this.link = link;
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaVo(String title, String icon, boolean noCache, String link) {
/*  58 */     this.title = title;
/*  59 */     this.icon = icon;
/*  60 */     this.noCache = noCache;
/*  61 */     if (StringUtils.ishttp(link))
/*     */     {
/*  63 */       this.link = link;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNoCache() {
/*  69 */     return this.noCache;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoCache(boolean noCache) {
/*  74 */     this.noCache = noCache;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  79 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/*  84 */     this.title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIcon() {
/*  89 */     return this.icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIcon(String icon) {
/*  94 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLink() {
/*  99 */     return this.link;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLink(String link) {
/* 104 */     this.link = link;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/vo/MetaVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */