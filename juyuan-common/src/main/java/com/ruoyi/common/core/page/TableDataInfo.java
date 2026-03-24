/*    */ package com.ruoyi.common.core.page;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
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
/*    */ public class TableDataInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private long total;
/*    */   private List<?> rows;
/*    */   private int code;
/*    */   private String msg;
/*    */   
/*    */   public TableDataInfo() {}
/*    */   
/*    */   public TableDataInfo(List<?> list, int total) {
/* 42 */     this.rows = list;
/* 43 */     this.total = total;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getTotal() {
/* 48 */     return this.total;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotal(long total) {
/* 53 */     this.total = total;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<?> getRows() {
/* 58 */     return this.rows;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRows(List<?> rows) {
/* 63 */     this.rows = rows;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCode() {
/* 68 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCode(int code) {
/* 73 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMsg() {
/* 78 */     return this.msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMsg(String msg) {
/* 83 */     this.msg = msg;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/page/TableDataInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */