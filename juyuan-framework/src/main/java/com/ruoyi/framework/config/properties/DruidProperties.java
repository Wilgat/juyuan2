/*    */ package com.ruoyi.framework.config.properties;
/*    */ 
/*    */ import com.alibaba.druid.pool.DruidDataSource;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.context.annotation.Configuration;
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
/*    */ @Configuration
/*    */ public class DruidProperties
/*    */ {
/*    */   @Value("${spring.datasource.druid.initialSize}")
/*    */   private int initialSize;
/*    */   @Value("${spring.datasource.druid.minIdle}")
/*    */   private int minIdle;
/*    */   @Value("${spring.datasource.druid.maxActive}")
/*    */   private int maxActive;
/*    */   @Value("${spring.datasource.druid.maxWait}")
/*    */   private int maxWait;
/*    */   @Value("${spring.datasource.druid.connectTimeout}")
/*    */   private int connectTimeout;
/*    */   @Value("${spring.datasource.druid.socketTimeout}")
/*    */   private int socketTimeout;
/*    */   @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
/*    */   private int timeBetweenEvictionRunsMillis;
/*    */   @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
/*    */   private int minEvictableIdleTimeMillis;
/*    */   @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
/*    */   private int maxEvictableIdleTimeMillis;
/*    */   @Value("${spring.datasource.druid.validationQuery}")
/*    */   private String validationQuery;
/*    */   @Value("${spring.datasource.druid.testWhileIdle}")
/*    */   private boolean testWhileIdle;
/*    */   @Value("${spring.datasource.druid.testOnBorrow}")
/*    */   private boolean testOnBorrow;
/*    */   @Value("${spring.datasource.druid.testOnReturn}")
/*    */   private boolean testOnReturn;
/*    */   
/*    */   public DruidDataSource dataSource(DruidDataSource datasource) {
/* 57 */     datasource.setInitialSize(this.initialSize);
/* 58 */     datasource.setMaxActive(this.maxActive);
/* 59 */     datasource.setMinIdle(this.minIdle);
/*    */ 
/*    */     
/* 62 */     datasource.setMaxWait(this.maxWait);
/*    */ 
/*    */     
/* 65 */     datasource.setConnectTimeout(this.connectTimeout);
/*    */ 
/*    */     
/* 68 */     datasource.setSocketTimeout(this.socketTimeout);
/*    */ 
/*    */     
/* 71 */     datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
/*    */ 
/*    */     
/* 74 */     datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
/* 75 */     datasource.setMaxEvictableIdleTimeMillis(this.maxEvictableIdleTimeMillis);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 80 */     datasource.setValidationQuery(this.validationQuery);
/*    */     
/* 82 */     datasource.setTestWhileIdle(this.testWhileIdle);
/*    */     
/* 84 */     datasource.setTestOnBorrow(this.testOnBorrow);
/*    */     
/* 86 */     datasource.setTestOnReturn(this.testOnReturn);
/* 87 */     return datasource;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/properties/DruidProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */