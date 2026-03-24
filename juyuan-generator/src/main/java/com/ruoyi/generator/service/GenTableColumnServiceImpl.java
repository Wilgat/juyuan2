/*    */ package com.ruoyi.generator.service;
/*    */ 
/*    */ import com.ruoyi.common.core.text.Convert;
/*    */ import com.ruoyi.generator.domain.GenTableColumn;
/*    */ import com.ruoyi.generator.mapper.GenTableColumnMapper;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class GenTableColumnServiceImpl
/*    */   implements IGenTableColumnService
/*    */ {
/*    */   @Autowired
/*    */   private GenTableColumnMapper genTableColumnMapper;
/*    */   
/*    */   public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
/* 30 */     return this.genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int insertGenTableColumn(GenTableColumn genTableColumn) {
/* 42 */     return this.genTableColumnMapper.insertGenTableColumn(genTableColumn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int updateGenTableColumn(GenTableColumn genTableColumn) {
/* 54 */     return this.genTableColumnMapper.updateGenTableColumn(genTableColumn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int deleteGenTableColumnByIds(String ids) {
/* 66 */     return this.genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-generator-3.8.6/!/com/ruoyi/generator/service/GenTableColumnServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */