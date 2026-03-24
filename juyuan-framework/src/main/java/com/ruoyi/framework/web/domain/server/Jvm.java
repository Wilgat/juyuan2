/*     */ package com.ruoyi.framework.web.domain.server;
/*     */ 
/*     */ import com.ruoyi.common.utils.Arith;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import java.lang.management.ManagementFactory;
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
/*     */ public class Jvm
/*     */ {
/*     */   private double total;
/*     */   private double max;
/*     */   private double free;
/*     */   private String version;
/*     */   private String home;
/*     */   
/*     */   public double getTotal() {
/*  41 */     return Arith.div(this.total, 1048576.0D, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTotal(double total) {
/*  46 */     this.total = total;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMax() {
/*  51 */     return Arith.div(this.max, 1048576.0D, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMax(double max) {
/*  56 */     this.max = max;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getFree() {
/*  61 */     return Arith.div(this.free, 1048576.0D, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFree(double free) {
/*  66 */     this.free = free;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getUsed() {
/*  71 */     return Arith.div(this.total - this.free, 1048576.0D, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getUsage() {
/*  76 */     return Arith.mul(Arith.div(this.total - this.free, this.total, 4), 100.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  84 */     return ManagementFactory.getRuntimeMXBean().getVmName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getVersion() {
/*  89 */     return this.version;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/*  94 */     this.version = version;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHome() {
/*  99 */     return this.home;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHome(String home) {
/* 104 */     this.home = home;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartTime() {
/* 112 */     return DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getServerStartDate());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRunTime() {
/* 120 */     return DateUtils.timeDistance(DateUtils.getNowDate(), DateUtils.getServerStartDate());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInputArgs() {
/* 128 */     return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/domain/server/Jvm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */