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
/*    */ public class Cpu
/*    */ {
/*    */   private int cpuNum;
/*    */   private double total;
/*    */   private double sys;
/*    */   private double used;
/*    */   private double wait;
/*    */   private double free;
/*    */   
/*    */   public int getCpuNum() {
/* 44 */     return this.cpuNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCpuNum(int cpuNum) {
/* 49 */     this.cpuNum = cpuNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getTotal() {
/* 54 */     return Arith.round(Arith.mul(this.total, 100.0D), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTotal(double total) {
/* 59 */     this.total = total;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getSys() {
/* 64 */     return Arith.round(Arith.mul(this.sys / this.total, 100.0D), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSys(double sys) {
/* 69 */     this.sys = sys;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getUsed() {
/* 74 */     return Arith.round(Arith.mul(this.used / this.total, 100.0D), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUsed(double used) {
/* 79 */     this.used = used;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getWait() {
/* 84 */     return Arith.round(Arith.mul(this.wait / this.total, 100.0D), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWait(double wait) {
/* 89 */     this.wait = wait;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getFree() {
/* 94 */     return Arith.round(Arith.mul(this.free / this.total, 100.0D), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFree(double free) {
/* 99 */     this.free = free;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/domain/server/Cpu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */