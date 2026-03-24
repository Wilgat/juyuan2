/*     */ package com.ruoyi.framework.config;
/*     */ 
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.ibatis.io.VFS;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ import org.mybatis.spring.SqlSessionFactoryBean;
/*     */ import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.core.env.Environment;
/*     */ import org.springframework.core.io.DefaultResourceLoader;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.ResourceLoader;
/*     */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*     */ import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
/*     */ import org.springframework.core.type.classreading.MetadataReader;
/*     */ import org.springframework.util.ClassUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Configuration
/*     */ public class MyBatisConfig
/*     */ {
/*     */   @Autowired
/*     */   private Environment env;
/*     */   static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
/*     */   
/*     */   public static String setTypeAliasesPackage(String typeAliasesPackage) {
/*  42 */     PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
/*  43 */     CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory((ResourceLoader)pathMatchingResourcePatternResolver);
/*  44 */     List<String> allResult = new ArrayList<>();
/*     */     
/*     */     try {
/*  47 */       for (String aliasesPackage : typeAliasesPackage.split(",")) {
/*     */         
/*  49 */         List<String> result = new ArrayList<>();
/*     */         
/*  51 */         aliasesPackage = "classpath*:" + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + "**/*.class";
/*  52 */         Resource[] resources = pathMatchingResourcePatternResolver.getResources(aliasesPackage);
/*  53 */         if (resources != null && resources.length > 0) {
/*     */           
/*  55 */           MetadataReader metadataReader = null;
/*  56 */           for (Resource resource : resources) {
/*     */             
/*  58 */             if (resource.isReadable()) {
/*     */               
/*  60 */               metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
/*     */               
/*     */               try {
/*  63 */                 result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
/*     */               }
/*  65 */               catch (ClassNotFoundException e) {
/*     */                 
/*  67 */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*  72 */         if (result.size() > 0) {
/*     */           
/*  74 */           HashSet<String> hashResult = new HashSet<>(result);
/*  75 */           allResult.addAll(hashResult);
/*     */         } 
/*     */       } 
/*  78 */       if (allResult.size() > 0)
/*     */       {
/*  80 */         typeAliasesPackage = String.join(",", (CharSequence[])allResult.<String>toArray(new String[0]));
/*     */       }
/*     */       else
/*     */       {
/*  84 */         throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
/*     */       }
/*     */     
/*  87 */     } catch (IOException e) {
/*     */       
/*  89 */       e.printStackTrace();
/*     */     } 
/*  91 */     return typeAliasesPackage;
/*     */   }
/*     */ 
/*     */   
/*     */   public Resource[] resolveMapperLocations(String[] mapperLocations) {
/*  96 */     PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
/*  97 */     List<Resource> resources = new ArrayList<>();
/*  98 */     if (mapperLocations != null)
/*     */     {
/* 100 */       for (String mapperLocation : mapperLocations) {
/*     */ 
/*     */         
/*     */         try {
/* 104 */           Resource[] mappers = pathMatchingResourcePatternResolver.getResources(mapperLocation);
/* 105 */           resources.addAll(Arrays.asList(mappers));
/*     */         }
/* 107 */         catch (IOException iOException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 113 */     return resources.<Resource>toArray(new Resource[resources.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
/* 119 */     String typeAliasesPackage = this.env.getProperty("mybatis.typeAliasesPackage");
/* 120 */     String mapperLocations = this.env.getProperty("mybatis.mapperLocations");
/* 121 */     String configLocation = this.env.getProperty("mybatis.configLocation");
/* 122 */     typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
/* 123 */     VFS.addImplClass(SpringBootVFS.class);
/*     */     
/* 125 */     SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
/* 126 */     sessionFactory.setDataSource(dataSource);
/* 127 */     sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
/* 128 */     sessionFactory.setMapperLocations(resolveMapperLocations(StringUtils.split(mapperLocations, ",")));
/* 129 */     sessionFactory.setConfigLocation((new DefaultResourceLoader()).getResource(configLocation));
/* 130 */     return sessionFactory.getObject();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/MyBatisConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */