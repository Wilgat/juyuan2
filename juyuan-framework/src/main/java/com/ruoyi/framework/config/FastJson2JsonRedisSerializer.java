/*    */ package com.ruoyi.framework.config;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.alibaba.fastjson2.JSONReader;
/*    */ import com.alibaba.fastjson2.JSONWriter;
/*    */ import com.alibaba.fastjson2.filter.Filter;
/*    */ import com.ruoyi.common.constant.Constants;
/*    */ import java.nio.charset.Charset;
/*    */ import org.springframework.data.redis.serializer.RedisSerializer;
/*    */ import org.springframework.data.redis.serializer.SerializationException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastJson2JsonRedisSerializer<T>
/*    */   implements RedisSerializer<T>
/*    */ {
/* 19 */   public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
/*    */   
/* 21 */   static final Filter AUTO_TYPE_FILTER = (Filter)JSONReader.autoTypeFilter(Constants.JSON_WHITELIST_STR);
/*    */ 
/*    */   
/*    */   private Class<T> clazz;
/*    */ 
/*    */   
/*    */   public FastJson2JsonRedisSerializer(Class<T> clazz) {
/* 28 */     this.clazz = clazz;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] serialize(T t) throws SerializationException {
/* 34 */     if (t == null)
/*    */     {
/* 36 */       return new byte[0];
/*    */     }
/* 38 */     return JSON.toJSONString(t, new JSONWriter.Feature[] { JSONWriter.Feature.WriteClassName }).getBytes(DEFAULT_CHARSET);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public T deserialize(byte[] bytes) throws SerializationException {
/* 44 */     if (bytes == null || bytes.length <= 0)
/*    */     {
/* 46 */       return null;
/*    */     }
/* 48 */     String str = new String(bytes, DEFAULT_CHARSET);
/*    */     
/* 50 */     return (T)JSON.parseObject(str, this.clazz, AUTO_TYPE_FILTER, new JSONReader.Feature[0]);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/config/FastJson2JsonRedisSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */