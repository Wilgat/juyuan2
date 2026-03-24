/*    */ package com.ruoyi.framework.web.domain.server;
/*    */ 
/*    */ import com.ruoyi.common.utils.Arith;
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
/*    */ public class Mem
/*    */ {
/*    */   private double total;
/*    */   private double used;
/*    */   private double free;
/*    */   
/*    */   public double getTotal() {
/* 29 */     return Arith.div(this.total, 1.073741824E9D, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotal(long total) {
/* 34 */     this.total = total;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getUsed() {
/* 39 */     return Arith.div(this.used, 1.073741824E9D, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUsed(long used) {
/* 44 */     this.used = used;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getFree() {
/* 49 */     return Arith.div(this.free, 1.073741824E9D, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFree(long free) {
/* 54 */     this.free = free;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getUsage() {
/* 59 */     return Arith.mul(Arith.div(this.used, this.total, 4), 100.0D);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/domain/server/Mem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */