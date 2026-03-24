/*    */ package com.ruoyi.common.core.page;
/*    */ 
/*    */ import com.ruoyi.common.core.text.Convert;
/*    */ import com.ruoyi.common.utils.ServletUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableSupport
/*    */ {
/*    */   public static final String PAGE_NUM = "pageNum";
/*    */   public static final String PAGE_SIZE = "pageSize";
/*    */   public static final String ORDER_BY_COLUMN = "orderByColumn";
/*    */   public static final String IS_ASC = "isAsc";
/*    */   public static final String REASONABLE = "reasonable";
/*    */   
/*    */   public static PageDomain getPageDomain() {
/* 43 */     PageDomain pageDomain = new PageDomain();
/* 44 */     pageDomain.setPageNum(Convert.toInt(ServletUtils.getParameter("pageNum"), Integer.valueOf(1)));
/* 45 */     pageDomain.setPageSize(Convert.toInt(ServletUtils.getParameter("pageSize"), Integer.valueOf(10)));
/* 46 */     pageDomain.setOrderByColumn(ServletUtils.getParameter("orderByColumn"));
/* 47 */     pageDomain.setIsAsc(ServletUtils.getParameter("isAsc"));
/* 48 */     pageDomain.setReasonable(ServletUtils.getParameterToBool("reasonable"));
/* 49 */     return pageDomain;
/*    */   }
/*    */ 
/*    */   
/*    */   public static PageDomain buildPageRequest() {
/* 54 */     return getPageDomain();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/page/TableSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */