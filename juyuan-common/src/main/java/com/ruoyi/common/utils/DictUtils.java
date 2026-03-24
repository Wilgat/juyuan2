/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ import com.alibaba.fastjson2.JSONArray;
/*     */ import com.ruoyi.common.core.domain.entity.SysDictData;
/*     */ import com.ruoyi.common.core.redis.RedisCache;
/*     */ import com.ruoyi.common.utils.spring.SpringUtils;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public class DictUtils
/*     */ {
/*     */   public static final String SEPARATOR = ",";
/*     */   
/*     */   public static void setDictCache(String key, List<SysDictData> dictDatas) {
/*  30 */     ((RedisCache)SpringUtils.getBean(RedisCache.class)).setCacheObject(getCacheKey(key), dictDatas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<SysDictData> getDictCache(String key) {
/*  40 */     JSONArray arrayCache = (JSONArray)((RedisCache)SpringUtils.getBean(RedisCache.class)).getCacheObject(getCacheKey(key));
/*  41 */     if (StringUtils.isNotNull(arrayCache)) {
/*  42 */       return arrayCache.toList(SysDictData.class, new com.alibaba.fastjson2.JSONReader.Feature[0]);
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDictLabel(String dictType, String dictValue) {
/*  55 */     return getDictLabel(dictType, dictValue, ",");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDictValue(String dictType, String dictLabel) {
/*  66 */     return getDictValue(dictType, dictLabel, ",");
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
/*     */   public static String getDictLabel(String dictType, String dictValue, String separator) {
/*  78 */     StringBuilder propertyString = new StringBuilder();
/*  79 */     List<SysDictData> datas = getDictCache(dictType);
/*     */     
/*  81 */     if (StringUtils.isNotNull(datas)) {
/*  82 */       if (StringUtils.containsAny(separator, dictValue)) {
/*  83 */         for (SysDictData dict : datas) {
/*  84 */           for (String value : dictValue.split(separator)) {
/*  85 */             if (value.equals(dict.getDictValue())) {
/*  86 */               propertyString.append(dict.getDictLabel()).append(separator);
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*  92 */         for (SysDictData dict : datas) {
/*  93 */           if (dictValue.equals(dict.getDictValue())) {
/*  94 */             return dict.getDictLabel();
/*     */           }
/*     */         } 
/*  97 */         return dictValue;
/*     */       } 
/*     */     }
/* 100 */     return StringUtils.stripEnd(propertyString.toString(), separator);
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
/*     */   public static String getDictValue(String dictType, String dictLabel, String separator) {
/* 112 */     StringBuilder propertyString = new StringBuilder();
/* 113 */     List<SysDictData> datas = getDictCache(dictType);
/*     */     
/* 115 */     if (StringUtils.containsAny(separator, dictLabel) && StringUtils.isNotEmpty(datas)) {
/* 116 */       for (SysDictData dict : datas) {
/* 117 */         for (String label : dictLabel.split(separator)) {
/* 118 */           if (label.equals(dict.getDictLabel())) {
/* 119 */             propertyString.append(dict.getDictValue()).append(separator);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 125 */       for (SysDictData dict : datas) {
/* 126 */         if (dictLabel.equals(dict.getDictLabel())) {
/* 127 */           return dict.getDictValue();
/*     */         }
/*     */       } 
/*     */     } 
/* 131 */     return StringUtils.stripEnd(propertyString.toString(), separator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeDictCache(String key) {
/* 140 */     ((RedisCache)SpringUtils.getBean(RedisCache.class)).deleteObject(getCacheKey(key));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearDictCache() {
/* 147 */     Collection<String> keys = ((RedisCache)SpringUtils.getBean(RedisCache.class)).keys("sys_dict:*");
/* 148 */     ((RedisCache)SpringUtils.getBean(RedisCache.class)).deleteObject(keys);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCacheKey(String configKey) {
/* 158 */     return "sys_dict:" + configKey;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/DictUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */