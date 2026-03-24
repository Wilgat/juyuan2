/*    */ package com.ruoyi.common.core.page;
/*    */ 
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PageDomain
/*    */ {
/*    */   private Integer pageNum;
/*    */   private Integer pageSize;
/*    */   private String orderByColumn;
/* 22 */   private String isAsc = "asc";
/*    */ 
/*    */   
/* 25 */   private Boolean reasonable = Boolean.valueOf(true);
/*    */ 
/*    */   
/*    */   public String getOrderBy() {
/* 29 */     if (StringUtils.isEmpty(this.orderByColumn))
/*    */     {
/* 31 */       return "";
/*    */     }
/* 33 */     return StringUtils.toUnderScoreCase(this.orderByColumn) + " " + this.isAsc;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getPageNum() {
/* 38 */     return this.pageNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPageNum(Integer pageNum) {
/* 43 */     this.pageNum = pageNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getPageSize() {
/* 48 */     return this.pageSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPageSize(Integer pageSize) {
/* 53 */     this.pageSize = pageSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOrderByColumn() {
/* 58 */     return this.orderByColumn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrderByColumn(String orderByColumn) {
/* 63 */     this.orderByColumn = orderByColumn;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIsAsc() {
/* 68 */     return this.isAsc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsAsc(String isAsc) {
/* 73 */     if (StringUtils.isNotEmpty(isAsc)) {
/*    */ 
/*    */       
/* 76 */       if ("ascending".equals(isAsc)) {
/*    */         
/* 78 */         isAsc = "asc";
/*    */       }
/* 80 */       else if ("descending".equals(isAsc)) {
/*    */         
/* 82 */         isAsc = "desc";
/*    */       } 
/* 84 */       this.isAsc = isAsc;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean getReasonable() {
/* 90 */     if (StringUtils.isNull(this.reasonable))
/*    */     {
/* 92 */       return Boolean.TRUE;
/*    */     }
/* 94 */     return this.reasonable;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setReasonable(Boolean reasonable) {
/* 99 */     this.reasonable = reasonable;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/page/PageDomain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */