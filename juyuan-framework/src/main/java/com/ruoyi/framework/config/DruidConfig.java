/*     */ package com.ruoyi.framework.config;
/*     */ 
/*     */ import com.alibaba.druid.pool.DruidDataSource;
/*     */ import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
/*     */ import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
/*     */ import com.alibaba.druid.util.Utils;
/*     */ import com.ruoyi.common.enums.DataSourceType;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import com.ruoyi.framework.config.properties.DruidProperties;
/*     */ import com.ruoyi.framework.datasource.DynamicDataSource;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.Filter;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.FilterConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.sql.DataSource;
/*     */ import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
/*     */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*     */ import org.springframework.boot.web.servlet.FilterRegistrationBean;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.context.annotation.Primary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Configuration
/*     */ public class DruidConfig
/*     */ {
/*     */   @Bean
/*     */   @ConfigurationProperties("spring.datasource.druid.master")
/*     */   public DataSource masterDataSource(DruidProperties druidProperties) {
/*  39 */     DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
/*  40 */     return (DataSource)druidProperties.dataSource(dataSource);
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   @ConfigurationProperties("spring.datasource.druid.slave")
/*     */   @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = {"enabled"}, havingValue = "true")
/*     */   public DataSource slaveDataSource(DruidProperties druidProperties) {
/*  48 */     DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
/*  49 */     return (DataSource)druidProperties.dataSource(dataSource);
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean(name = {"dynamicDataSource"})
/*     */   @Primary
/*     */   public DynamicDataSource dataSource(DataSource masterDataSource) {
/*  56 */     Map<Object, Object> targetDataSources = new HashMap<>();
/*  57 */     targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
/*  58 */     setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
/*  59 */     return new DynamicDataSource(masterDataSource, targetDataSources);
/*     */   }
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
/*     */   public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
/*     */     try {
/*  73 */       DataSource dataSource = (DataSource)SpringUtils.getBean(beanName);
/*  74 */       targetDataSources.put(sourceName, dataSource);
/*     */     }
/*  76 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Bean
/*     */   @ConditionalOnProperty(name = {"spring.datasource.druid.statViewServlet.enabled"}, havingValue = "true")
/*     */   public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
/*  90 */     DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
/*     */     
/*  92 */     String pattern = (config.getUrlPattern() != null) ? config.getUrlPattern() : "/druid/*";
/*  93 */     String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
/*  94 */     String filePath = "support/http/resources/js/common.js";
/*     */     
/*  96 */     Filter filter = new Filter()
/*     */       {
/*     */         public void init(FilterConfig filterConfig) throws ServletException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/* 106 */           chain.doFilter(request, response);
/*     */           
/* 108 */           response.resetBuffer();
/*     */           
/* 110 */           String text = Utils.readFromResource("support/http/resources/js/common.js");
/*     */           
/* 112 */           text = text.replaceAll("<a.*?banner\"></a><br/>", "");
/* 113 */           text = text.replaceAll("powered.*?shrek.wang</a>", "");
/* 114 */           response.getWriter().write(text);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void destroy() {}
/*     */       };
/* 121 */     FilterRegistrationBean registrationBean = new FilterRegistrationBean();
/* 122 */     registrationBean.setFilter(filter);
/* 123 */     registrationBean.addUrlPatterns(new String[] { commonJsPattern });
/* 124 */     return registrationBean;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/DruidConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */