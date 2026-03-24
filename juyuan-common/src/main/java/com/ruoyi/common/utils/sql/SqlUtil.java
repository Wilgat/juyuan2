/*    */ package com.ruoyi.common.utils.sql;
/*    */ 
/*    */ import com.ruoyi.common.exception.UtilException;
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
/*    */ public class SqlUtil
/*    */ {
/* 16 */   public static String SQL_REGEX = "and |extractvalue|updatexml|exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |or |+|user()";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final int ORDER_BY_MAX_LENGTH = 500;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String escapeOrderBySql(String value) {
/* 33 */     if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
/*    */     {
/* 35 */       throw new UtilException("参数不符合规范，不能进行查询");
/*    */     }
/* 37 */     if (StringUtils.length(value) > 500)
/*    */     {
/* 39 */       throw new UtilException("参数已超过最大限制，不能进行查询");
/*    */     }
/* 41 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isValidOrderBySql(String value) {
/* 49 */     return value.matches(SQL_PATTERN);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void filterKeyword(String value) {
/* 57 */     if (StringUtils.isEmpty(value)) {
/*    */       return;
/*    */     }
/*    */     
/* 61 */     String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
/* 62 */     for (String sqlKeyword : sqlKeywords) {
/*    */       
/* 64 */       if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1)
/*    */       {
/* 66 */         throw new UtilException("参数存在SQL注入风险");
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/sql/SqlUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */