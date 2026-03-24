/*     */ package com.ruoyi.framework.web.service;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.redis.RedisCache;
/*     */ import com.ruoyi.common.utils.ServletUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.ip.AddressUtils;
/*     */ import com.ruoyi.common.utils.ip.IpUtils;
/*     */ import com.ruoyi.common.utils.uuid.IdUtils;
/*     */ import eu.bitwalker.useragentutils.UserAgent;
/*     */ import io.jsonwebtoken.Claims;
/*     */ import io.jsonwebtoken.Jwts;
/*     */ import io.jsonwebtoken.SignatureAlgorithm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Value;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class TokenService
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(TokenService.class);
/*     */ 
/*     */   
/*     */   @Value("${token.header}")
/*     */   private String header;
/*     */ 
/*     */   
/*     */   @Value("${token.secret}")
/*     */   private String secret;
/*     */ 
/*     */   
/*     */   @Value("${token.expireTime}")
/*     */   private int expireTime;
/*     */   
/*     */   protected static final long MILLIS_SECOND = 1000L;
/*     */   
/*     */   protected static final long MILLIS_MINUTE = 60000L;
/*     */   
/*  52 */   private static final Long MILLIS_MINUTE_TEN = Long.valueOf(1200000L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private RedisCache redisCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginUser getLoginUser(HttpServletRequest request) {
/*  65 */     String token = getToken(request);
/*  66 */     if (StringUtils.isNotEmpty(token)) {
/*     */       
/*     */       try {
/*     */         
/*  70 */         Claims claims = parseToken(token);
/*     */         
/*  72 */         String uuid = (String)claims.get("login_user_key");
/*  73 */         String userKey = getTokenKey(uuid);
/*  74 */         LoginUser user = (LoginUser)this.redisCache.getCacheObject(userKey);
/*  75 */         return user;
/*     */       }
/*  77 */       catch (Exception e) {
/*     */         
/*  79 */         log.error("获取用户信息异常'{}'", e.getMessage());
/*     */       } 
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginUser(LoginUser loginUser) {
/*  90 */     if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
/*     */     {
/*  92 */       refreshToken(loginUser);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delLoginUser(String token) {
/* 101 */     if (StringUtils.isNotEmpty(token)) {
/*     */       
/* 103 */       String userKey = getTokenKey(token);
/* 104 */       this.redisCache.deleteObject(userKey);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String createToken(LoginUser loginUser) {
/* 116 */     String token = IdUtils.fastUUID();
/* 117 */     loginUser.setToken(token);
/* 118 */     setUserAgent(loginUser);
/* 119 */     refreshToken(loginUser);
/*     */     
/* 121 */     Map<String, Object> claims = new HashMap<>();
/* 122 */     claims.put("login_user_key", token);
/* 123 */     return createToken(claims);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void verifyToken(LoginUser loginUser) {
/* 134 */     long expireTime = loginUser.getExpireTime().longValue();
/* 135 */     long currentTime = System.currentTimeMillis();
/* 136 */     if (expireTime - currentTime <= MILLIS_MINUTE_TEN.longValue())
/*     */     {
/* 138 */       refreshToken(loginUser);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshToken(LoginUser loginUser) {
/* 149 */     loginUser.setLoginTime(Long.valueOf(System.currentTimeMillis()));
/* 150 */     loginUser.setExpireTime(Long.valueOf(loginUser.getLoginTime().longValue() + this.expireTime * 60000L));
/*     */     
/* 152 */     String userKey = getTokenKey(loginUser.getToken());
/* 153 */     this.redisCache.setCacheObject(userKey, loginUser, Integer.valueOf(this.expireTime), TimeUnit.MINUTES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserAgent(LoginUser loginUser) {
/* 163 */     UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
/* 164 */     String ip = IpUtils.getIpAddr();
/* 165 */     loginUser.setIpaddr(ip);
/* 166 */     loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
/* 167 */     loginUser.setBrowser(userAgent.getBrowser().getName());
/* 168 */     loginUser.setOs(userAgent.getOperatingSystem().getName());
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
/*     */   private String createToken(Map<String, Object> claims) {
/* 181 */     String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, this.secret).compact();
/* 182 */     return token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Claims parseToken(String token) {
/* 193 */     return (Claims)Jwts.parser()
/* 194 */       .setSigningKey(this.secret)
/* 195 */       .parseClaimsJws(token)
/* 196 */       .getBody();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUsernameFromToken(String token) {
/* 207 */     Claims claims = parseToken(token);
/* 208 */     return claims.getSubject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getToken(HttpServletRequest request) {
/* 219 */     String token = request.getHeader(this.header);
/* 220 */     if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer "))
/*     */     {
/* 222 */       token = token.replace("Bearer ", "");
/*     */     }
/* 224 */     return token;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getTokenKey(String uuid) {
/* 229 */     return "login_tokens:" + uuid;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/service/TokenService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */