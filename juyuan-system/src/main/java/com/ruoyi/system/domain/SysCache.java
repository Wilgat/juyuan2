/*    */ package com.ruoyi.system.domain;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SysCache
/*    */ {
/* 13 */   private String cacheName = "";
/*    */ 
/*    */   
/* 16 */   private String cacheKey = "";
/*    */ 
/*    */   
/* 19 */   private String cacheValue = "";
/*    */ 
/*    */   
/* 22 */   private String remark = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public SysCache() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public SysCache(String cacheName, String remark) {
/* 31 */     this.cacheName = cacheName;
/* 32 */     this.remark = remark;
/*    */   }
/*    */ 
/*    */   
/*    */   public SysCache(String cacheName, String cacheKey, String cacheValue) {
/* 37 */     this.cacheName = StringUtils.replace(cacheName, ":", "");
/* 38 */     this.cacheKey = StringUtils.replace(cacheKey, cacheName, "");
/* 39 */     this.cacheValue = cacheValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCacheName() {
/* 44 */     return this.cacheName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCacheName(String cacheName) {
/* 49 */     this.cacheName = cacheName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCacheKey() {
/* 54 */     return this.cacheKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCacheKey(String cacheKey) {
/* 59 */     this.cacheKey = cacheKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCacheValue() {
/* 64 */     return this.cacheValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCacheValue(String cacheValue) {
/* 69 */     this.cacheValue = cacheValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRemark() {
/* 74 */     return this.remark;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRemark(String remark) {
/* 79 */     this.remark = remark;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */