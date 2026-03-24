/*     */ package com.ruoyi.common.utils;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
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
/*     */ public class Arith
/*     */ {
/*     */   private static final int DEF_DIV_SCALE = 10;
/*     */   
/*     */   public static double add(double v1, double v2) {
/*  30 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  31 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  32 */     return b1.add(b2).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double sub(double v1, double v2) {
/*  43 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  44 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  45 */     return b1.subtract(b2).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mul(double v1, double v2) {
/*  56 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  57 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  58 */     return b1.multiply(b2).doubleValue();
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
/*     */   public static double div(double v1, double v2) {
/*  70 */     return div(v1, v2, 10);
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
/*     */   public static double div(double v1, double v2, int scale) {
/*  83 */     if (scale < 0)
/*     */     {
/*  85 */       throw new IllegalArgumentException("The scale must be a positive integer or zero");
/*     */     }
/*     */     
/*  88 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  89 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  90 */     if (b1.compareTo(BigDecimal.ZERO) == 0)
/*     */     {
/*  92 */       return BigDecimal.ZERO.doubleValue();
/*     */     }
/*  94 */     return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double round(double v, int scale) {
/* 105 */     if (scale < 0)
/*     */     {
/* 107 */       throw new IllegalArgumentException("The scale must be a positive integer or zero");
/*     */     }
/*     */     
/* 110 */     BigDecimal b = new BigDecimal(Double.toString(v));
/* 111 */     BigDecimal one = BigDecimal.ONE;
/* 112 */     return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/Arith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */