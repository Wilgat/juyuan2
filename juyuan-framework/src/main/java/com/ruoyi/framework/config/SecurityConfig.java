/*     */ package com.ruoyi.framework.config;
/*     */ 
/*     */ import com.ruoyi.framework.config.properties.PermitAllUrlProperties;
/*     */ import com.ruoyi.framework.security.filter.JwtAuthenticationTokenFilter;
/*     */ import com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl;
/*     */ import com.ruoyi.framework.security.handle.LogoutSuccessHandlerImpl;
/*     */ import javax.servlet.Filter;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.http.HttpMethod;
/*     */ import org.springframework.security.authentication.AuthenticationManager;
/*     */ import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
/*     */ import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
/*     */ import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*     */ import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*     */ import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
/*     */ import org.springframework.security.config.http.SessionCreationPolicy;
/*     */ import org.springframework.security.core.userdetails.UserDetailsService;
/*     */ import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*     */ import org.springframework.security.crypto.password.PasswordEncoder;
/*     */ import org.springframework.security.web.AuthenticationEntryPoint;
/*     */ import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*     */ import org.springframework.security.web.authentication.logout.LogoutFilter;
/*     */ import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
/*     */ import org.springframework.web.filter.CorsFilter;
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
/*     */ 
/*     */ 
/*     */ @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
/*     */ public class SecurityConfig
/*     */   extends WebSecurityConfigurerAdapter
/*     */ {
/*     */   @Autowired
/*     */   private UserDetailsService userDetailsService;
/*     */   @Autowired
/*     */   private AuthenticationEntryPointImpl unauthorizedHandler;
/*     */   @Autowired
/*     */   private LogoutSuccessHandlerImpl logoutSuccessHandler;
/*     */   @Autowired
/*     */   private JwtAuthenticationTokenFilter authenticationTokenFilter;
/*     */   @Autowired
/*     */   private CorsFilter corsFilter;
/*     */   @Autowired
/*     */   private PermitAllUrlProperties permitAllUrl;
/*     */   
/*     */   @Bean
/*     */   public AuthenticationManager authenticationManagerBean() throws Exception {
/*  75 */     return super.authenticationManagerBean();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configure(HttpSecurity httpSecurity) throws Exception {
/*  96 */     ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
/*  97 */     this.permitAllUrl.getUrls().forEach(url -> ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)registry.antMatchers(new String[] { url })).permitAll());
/*     */     
/*  99 */     ((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)httpSecurity
/*     */       
/* 101 */       .csrf().disable())
/*     */       
/* 103 */       .headers().cacheControl().disable().and())
/*     */       
/* 105 */       .exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint)this.unauthorizedHandler).and())
/*     */       
/* 107 */       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and())
/*     */       
/* 109 */       .authorizeRequests()
/*     */       
/* 111 */       .antMatchers(new String[] { "/login", "/register", "/captchaImage", "/sendEmailCaptcha", "/common/changeLanguages", "/webSocketServer/**", "/common/file/**" })).permitAll()
/*     */       
/* 113 */       .antMatchers(HttpMethod.GET, new String[] { "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**" })).permitAll()
/* 114 */       .antMatchers(new String[] { "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**" })).permitAll()
/*     */ 
/*     */       
/* 117 */       .anyRequest()).authenticated()
/* 118 */       .and())
/* 119 */       .headers().frameOptions().disable();
/*     */     
/* 121 */     httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler((LogoutSuccessHandler)this.logoutSuccessHandler);
/*     */     
/* 123 */     httpSecurity.addFilterBefore((Filter)this.authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
/*     */     
/* 125 */     httpSecurity.addFilterBefore((Filter)this.corsFilter, JwtAuthenticationTokenFilter.class);
/* 126 */     httpSecurity.addFilterBefore((Filter)this.corsFilter, LogoutFilter.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public BCryptPasswordEncoder bCryptPasswordEncoder() {
/* 134 */     return new BCryptPasswordEncoder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/* 142 */     auth.userDetailsService(this.userDetailsService).passwordEncoder((PasswordEncoder)bCryptPasswordEncoder());
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/SecurityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */