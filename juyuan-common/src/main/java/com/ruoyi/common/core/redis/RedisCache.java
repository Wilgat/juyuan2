/*     */ package com.ruoyi.common.core.redis;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.redis.core.BoundSetOperations;
/*     */ import org.springframework.data.redis.core.HashOperations;
/*     */ import org.springframework.data.redis.core.RedisTemplate;
/*     */ import org.springframework.data.redis.core.ValueOperations;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ public class RedisCache
/*     */ {
/*     */   @Autowired
/*     */   public RedisTemplate redisTemplate;
/*     */   
/*     */   public <T> void setCacheObject(String key, T value) {
/*  36 */     this.redisTemplate.opsForValue().set(key, value);
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
/*     */   public <T> void setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
/*  49 */     this.redisTemplate.opsForValue().set(key, value, timeout.intValue(), timeUnit);
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
/*     */   public boolean expire(String key, long timeout) {
/*  61 */     return expire(key, timeout, TimeUnit.SECONDS);
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
/*     */   public boolean expire(String key, long timeout, TimeUnit unit) {
/*  74 */     return this.redisTemplate.expire(key, timeout, unit).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getExpire(String key) {
/*  85 */     return this.redisTemplate.getExpire(key).longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean hasKey(String key) {
/*  96 */     return this.redisTemplate.hasKey(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T getCacheObject(String key) {
/* 107 */     ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
/* 108 */     return (T)operation.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deleteObject(String key) {
/* 118 */     return this.redisTemplate.delete(key).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deleteObject(Collection collection) {
/* 129 */     return (this.redisTemplate.delete(collection).longValue() > 0L);
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
/*     */   public <T> long setCacheList(String key, List<T> dataList) {
/* 141 */     Long count = this.redisTemplate.opsForList().rightPushAll(key, dataList);
/* 142 */     return (count == null) ? 0L : count.longValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> List<T> getCacheList(String key) {
/* 153 */     return this.redisTemplate.opsForList().range(key, 0L, -1L);
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
/*     */   public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
/* 165 */     BoundSetOperations<String, T> setOperation = this.redisTemplate.boundSetOps(key);
/* 166 */     Iterator<T> it = dataSet.iterator();
              for (T item : dataSet) {
                  setOperation.add(item);  // ← passes single T, compiler auto-boxes to T...
              }
/* 171 */     return setOperation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Set<T> getCacheSet(String key) {
/* 182 */     return this.redisTemplate.opsForSet().members(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void setCacheMap(String key, Map<String, T> dataMap) {
/* 193 */     if (dataMap != null) {
/* 194 */       this.redisTemplate.opsForHash().putAll(key, dataMap);
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
/*     */   public <T> Map<String, T> getCacheMap(String key) {
/* 206 */     return this.redisTemplate.opsForHash().entries(key);
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
/*     */   public <T> void setCacheMapValue(String key, String hKey, T value) {
/* 218 */     this.redisTemplate.opsForHash().put(key, hKey, value);
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
/*     */   public <T> T getCacheMapValue(String key, String hKey) {
/* 230 */     HashOperations<String, String, T> opsForHash = this.redisTemplate.opsForHash();
/* 231 */     return (T)opsForHash.get(key, hKey);
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
/*     */   public <T> List<T> getMultiCacheMapValue(String key, Collection<Object> hKeys) {
/* 243 */     return this.redisTemplate.opsForHash().multiGet(key, hKeys);
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
/*     */   public boolean deleteCacheMapValue(String key, String hKey) {
/* 255 */     return (this.redisTemplate.opsForHash().delete(key, new Object[] { hKey }).longValue() > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> keys(String pattern) {
/* 266 */     return this.redisTemplate.keys(pattern);
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/redis/RedisCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */