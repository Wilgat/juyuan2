/*    */ package com.ruoyi.framework.datasource;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.sql.DataSource;
/*    */ import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DynamicDataSource
/*    */   extends AbstractRoutingDataSource
/*    */ {
/*    */   public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
/* 16 */     setDefaultTargetDataSource(defaultTargetDataSource);
/* 17 */     setTargetDataSources(targetDataSources);
/* 18 */     afterPropertiesSet();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Object determineCurrentLookupKey() {
/* 24 */     return DynamicDataSourceContextHolder.getDataSourceType();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/datasource/DynamicDataSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */