/*    */ package com.ruoyi.common.utils;
/*    */ 
/*    */ import com.github.pagehelper.PageHelper;
/*    */ import com.ruoyi.common.core.page.PageDomain;
/*    */ import com.ruoyi.common.core.page.TableSupport;
/*    */ import com.ruoyi.common.utils.sql.SqlUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PageUtils
/*    */   extends PageHelper
/*    */ {
/*    */   public static void startPage() {
/* 20 */     PageDomain pageDomain = TableSupport.buildPageRequest();
/* 21 */     Integer pageNum = pageDomain.getPageNum();
/* 22 */     Integer pageSize = pageDomain.getPageSize();
/* 23 */     String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
/* 24 */     Boolean reasonable = pageDomain.getReasonable();
/* 25 */     PageHelper.startPage(pageNum.intValue(), pageSize.intValue(), orderBy).setReasonable(reasonable);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clearPage() {
/* 33 */     PageHelper.clearPage();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/PageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */