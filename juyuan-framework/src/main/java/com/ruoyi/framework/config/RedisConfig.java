/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import org.springframework.cache.annotation.CachingConfigurerSupport;
/*    */ import org.springframework.cache.annotation.EnableCaching;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.data.redis.connection.RedisConnectionFactory;
/*    */ import org.springframework.data.redis.core.RedisTemplate;
/*    */ import org.springframework.data.redis.core.script.DefaultRedisScript;
/*    */ import org.springframework.data.redis.serializer.RedisSerializer;
/*    */ import org.springframework.data.redis.serializer.StringRedisSerializer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ @EnableCaching
/*    */ public class RedisConfig
/*    */   extends CachingConfigurerSupport
/*    */ {
/*    */   @Bean
/*    */   public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
/* 25 */     RedisTemplate<Object, Object> template = new RedisTemplate();
/* 26 */     template.setConnectionFactory(connectionFactory);
/*    */     
/* 28 */     FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
/*    */ 
/*    */     
/* 31 */     template.setKeySerializer((RedisSerializer)new StringRedisSerializer());
/* 32 */     template.setValueSerializer(serializer);
/*    */ 
/*    */     
/* 35 */     template.setHashKeySerializer((RedisSerializer)new StringRedisSerializer());
/* 36 */     template.setHashValueSerializer(serializer);
/*    */     
/* 38 */     template.afterPropertiesSet();
/* 39 */     return template;
/*    */   }
/*    */ 
/*    */   
/*    */   @Bean
/*    */   public DefaultRedisScript<Long> limitScript() {
/* 45 */     DefaultRedisScript<Long> redisScript = new DefaultRedisScript();
/* 46 */     redisScript.setScriptText(limitScriptText());
/* 47 */     redisScript.setResultType(Long.class);
/* 48 */     return redisScript;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String limitScriptText() {
/* 56 */     return "local key = KEYS[1]\nlocal count = tonumber(ARGV[1])\nlocal time = tonumber(ARGV[2])\nlocal current = redis.call('get', key);\nif current and tonumber(current) > count then\n    return tonumber(current);\nend\ncurrent = redis.call('incr', key)\nif tonumber(current) == 1 then\n    redis.call('expire', key, time)\nend\nreturn tonumber(current);";
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/RedisConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */