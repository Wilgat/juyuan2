/*     */ package com.ruoyi.web.core.config;
/*     */ 
/*     */ import com.ruoyi.common.config.RuoYiConfig;
/*     */ import io.swagger.annotations.ApiOperation;
/*     */ import io.swagger.models.auth.In;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Value;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import springfox.documentation.builders.ApiInfoBuilder;
/*     */ import springfox.documentation.builders.PathSelectors;
/*     */ import springfox.documentation.builders.RequestHandlerSelectors;
/*     */ import springfox.documentation.service.ApiInfo;
/*     */ import springfox.documentation.service.ApiKey;
/*     */ import springfox.documentation.service.AuthorizationScope;
/*     */ import springfox.documentation.service.Contact;
/*     */ import springfox.documentation.service.SecurityReference;
/*     */ import springfox.documentation.service.SecurityScheme;
/*     */ import springfox.documentation.spi.DocumentationType;
/*     */ import springfox.documentation.spi.service.contexts.OperationContext;
/*     */ import springfox.documentation.spi.service.contexts.SecurityContext;
/*     */ import springfox.documentation.spring.web.plugins.Docket;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Configuration
/*     */ public class SwaggerConfig
/*     */ {
/*     */   @Autowired
/*     */   private RuoYiConfig ruoyiConfig;
/*     */   @Value("${swagger.enabled}")
/*     */   private boolean enabled;
/*     */   @Value("${swagger.pathMapping}")
/*     */   private String pathMapping;
/*     */   
/*     */   @Bean
/*     */   public Docket createRestApi() {
/*  51 */     return (new Docket(DocumentationType.OAS_30))
/*     */       
/*  53 */       .enable(this.enabled)
/*     */       
/*  55 */       .apiInfo(apiInfo())
/*     */       
/*  57 */       .select()
/*     */       
/*  59 */       .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
/*     */ 
/*     */ 
/*     */       
/*  63 */       .paths(PathSelectors.any())
/*  64 */       .build()
/*     */       
/*  66 */       .securitySchemes(securitySchemes())
/*  67 */       .securityContexts(securityContexts())
/*  68 */       .pathMapping(this.pathMapping);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SecurityScheme> securitySchemes() {
/*  76 */     List<SecurityScheme> apiKeyList = new ArrayList<>();
/*  77 */     apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
/*  78 */     return apiKeyList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SecurityContext> securityContexts() {
/*  86 */     List<SecurityContext> securityContexts = new ArrayList<>();
/*  87 */     securityContexts.add(
/*  88 */         SecurityContext.builder()
/*  89 */         .securityReferences(defaultAuth())
/*  90 */         .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
/*  91 */         .build());
/*  92 */     return securityContexts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<SecurityReference> defaultAuth() {
/* 100 */     AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
/* 101 */     AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
/* 102 */     authorizationScopes[0] = authorizationScope;
/* 103 */     List<SecurityReference> securityReferences = new ArrayList<>();
/* 104 */     securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
/* 105 */     return securityReferences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ApiInfo apiInfo() {
/* 114 */     return (new ApiInfoBuilder())
/*     */       
/* 116 */       .title("标题：若依管理系统_接口文档")
/*     */       
/* 118 */       .description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
/*     */       
/* 120 */       .contact(new Contact(this.ruoyiConfig.getName(), null, null))
/*     */       
/* 122 */       .version("版本号:" + this.ruoyiConfig.getVersion())
/* 123 */       .build();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/core/config/SwaggerConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */