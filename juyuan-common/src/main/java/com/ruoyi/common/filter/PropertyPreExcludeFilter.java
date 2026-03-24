/*    */ package com.ruoyi.common.filter;
/*    */ 
/*    */ import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyPreExcludeFilter
/*    */   extends SimplePropertyPreFilter
/*    */ {
/*    */   public PropertyPreExcludeFilter() {
/* 13 */     super(new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public PropertyPreExcludeFilter addExcludes(String... filters) {
/* 18 */     for (int i = 0; i < filters.length; i++)
/*    */     {
/* 20 */       getExcludes().add(filters[i]);
/*    */     }
/* 22 */     return this;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/filter/PropertyPreExcludeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */