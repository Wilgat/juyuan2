/*     */ package com.ruoyi.system.service.impl;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.entity.SysDictData;
/*     */ import com.ruoyi.common.core.domain.entity.SysDictType;
/*     */ import com.ruoyi.common.core.domain.entity.SysRole;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.DictUtils;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.system.mapper.SysDictDataMapper;
/*     */ import com.ruoyi.system.mapper.SysDictTypeMapper;
/*     */ import com.ruoyi.system.service.ISysDictTypeService;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ public class SysDictTypeServiceImpl
/*     */   implements ISysDictTypeService
/*     */ {
/*     */   @Autowired
/*     */   private SysDictTypeMapper dictTypeMapper;
/*     */   @Autowired
/*     */   private SysDictDataMapper dictDataMapper;
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  44 */     loadingDictCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SysDictType> selectDictTypeList(SysDictType dictType) {
/*  55 */     return this.dictTypeMapper.selectDictTypeList(dictType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SysDictType> selectDictTypeAll() {
/*  65 */     return this.dictTypeMapper.selectDictTypeAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SysDictData> selectDictDataByType(String dictType) {
/*  76 */     List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
/*  77 */     if (StringUtils.isNotEmpty(dictDatas)) {
/*  78 */       return filterDictData(dictType, dictDatas);
/*     */     }
/*  80 */     dictDatas = this.dictDataMapper.selectDictDataByType(dictType);
/*  81 */     if (StringUtils.isNotEmpty(dictDatas)) {
/*  82 */       DictUtils.setDictCache(dictType, dictDatas);
/*  83 */       return filterDictData(dictType, dictDatas);
/*     */     } 
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   private List<SysDictData> filterDictData(String dictType, List<SysDictData> dictDatas) {
/*  89 */     if (!StringUtils.equals(dictType, "ter_alert_type")) {
/*  90 */       return dictDatas;
/*     */     }
/*  92 */     LoginUser loginUser = SecurityUtils.getLoginUser();
/*  93 */     Set<String> roleKeys = (Set<String>)loginUser.getUser().getRoles().stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
/*     */     
/*  95 */     long count = roleKeys.stream().filter(roleKey -> roleKey.contains("admin")).count();
/*  96 */     if (count > 0L) {
/*  97 */       return dictDatas;
/*     */     }
/*  99 */     List<SysDictData> dictDatasNew = (List<SysDictData>)dictDatas.stream().filter(dictData -> roleKeys.contains("alert_" + dictData.getDictValue())).collect(Collectors.toList());
/* 100 */     return dictDatasNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SysDictType selectDictTypeById(Long dictId) {
/* 111 */     return this.dictTypeMapper.selectDictTypeById(dictId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SysDictType selectDictTypeByType(String dictType) {
/* 122 */     return this.dictTypeMapper.selectDictTypeByType(dictType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteDictTypeByIds(Long[] dictIds) {
/* 132 */     for (Long dictId : dictIds) {
/* 133 */       SysDictType dictType = selectDictTypeById(dictId);
/* 134 */       if (this.dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
/* 135 */         throw new ServiceException(String.format("%1$s已分配,不能删除", new Object[] { dictType.getDictName() }));
/*     */       }
/* 137 */       this.dictTypeMapper.deleteDictTypeById(dictId);
/* 138 */       DictUtils.removeDictCache(dictType.getDictType());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadingDictCache() {
/* 147 */     SysDictData dictData = new SysDictData();
/* 148 */     dictData.setStatus("0");
/* 149 */     Map<String, List<SysDictData>> dictDataMap = (Map<String, List<SysDictData>>)this.dictDataMapper.selectDictDataList(dictData).stream().collect(Collectors.groupingBy(SysDictData::getDictType));
/* 150 */     for (Map.Entry<String, List<SysDictData>> entry : dictDataMap.entrySet()) {
/* 151 */       DictUtils.setDictCache(entry.getKey(), (List)((List)entry.getValue()).stream().sorted(Comparator.comparing(SysDictData::getDictSort)).collect(Collectors.toList()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearDictCache() {
/* 160 */     DictUtils.clearDictCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetDictCache() {
/* 168 */     clearDictCache();
/* 169 */     loadingDictCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int insertDictType(SysDictType dict) {
/* 180 */     int row = this.dictTypeMapper.insertDictType(dict);
/* 181 */     if (row > 0) {
/* 182 */       DictUtils.setDictCache(dict.getDictType(), null);
/*     */     }
/* 184 */     return row;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public int updateDictType(SysDictType dict) {
/* 196 */     SysDictType oldDict = this.dictTypeMapper.selectDictTypeById(dict.getDictId());
/* 197 */     this.dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
/* 198 */     int row = this.dictTypeMapper.updateDictType(dict);
/* 199 */     if (row > 0) {
/* 200 */       List<SysDictData> dictDatas = this.dictDataMapper.selectDictDataByType(dict.getDictType());
/* 201 */       DictUtils.setDictCache(dict.getDictType(), dictDatas);
/*     */     } 
/* 203 */     return row;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkDictTypeUnique(SysDictType dict) {
/* 214 */     Long dictId = Long.valueOf(StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId().longValue());
/* 215 */     SysDictType dictType = this.dictTypeMapper.checkDictTypeUnique(dict.getDictType());
/* 216 */     if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
/* 217 */       return false;
/*     */     }
/* 219 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/impl/SysDictTypeServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */