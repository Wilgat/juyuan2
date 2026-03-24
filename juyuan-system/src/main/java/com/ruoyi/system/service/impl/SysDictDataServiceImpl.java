/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.entity.SysDictData;
/*     */ import com.ruoyi.common.utils.DictUtils;
/*     */ import com.ruoyi.system.mapper.SysDictDataMapper;
/*     */ import com.ruoyi.system.service.ISysDictDataService;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
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
/*     */ @Service
/*     */ public class SysDictDataServiceImpl
/*     */   implements ISysDictDataService
/*     */ {
/*     */   @Autowired
/*     */   private SysDictDataMapper dictDataMapper;
/*     */   
/*     */   public List<SysDictData> selectDictDataList(SysDictData dictData) {
/*  31 */     return this.dictDataMapper.selectDictDataList(dictData);
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
/*     */   public String selectDictLabel(String dictType, String dictValue) {
/*  44 */     return this.dictDataMapper.selectDictLabel(dictType, dictValue);
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
/*     */   public SysDictData selectDictDataById(Long dictCode) {
/*  56 */     return this.dictDataMapper.selectDictDataById(dictCode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteDictDataByIds(Long[] dictCodes) {
/*  67 */     for (Long dictCode : dictCodes) {
/*     */       
/*  69 */       SysDictData data = selectDictDataById(dictCode);
/*  70 */       this.dictDataMapper.deleteDictDataById(dictCode);
/*  71 */       List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
/*  72 */       DictUtils.setDictCache(data.getDictType(), dictDatas);
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
/*     */   
/*     */   public int insertDictData(SysDictData data) {
/*  85 */     int row = this.dictDataMapper.insertDictData(data);
/*  86 */     if (row > 0) {
/*     */       
/*  88 */       List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
/*  89 */       DictUtils.setDictCache(data.getDictType(), dictDatas);
/*     */     } 
/*  91 */     return row;
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
/*     */   public int updateDictData(SysDictData data) {
/* 103 */     int row = this.dictDataMapper.updateDictData(data);
/* 104 */     if (row > 0) {
/*     */       
/* 106 */       List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(data.getDictType());
/* 107 */       DictUtils.setDictCache(data.getDictType(), dictDatas);
/*     */     } 
/* 109 */     return row;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysDictDataServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */