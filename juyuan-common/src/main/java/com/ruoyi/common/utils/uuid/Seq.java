/*    */ package com.ruoyi.common.utils.uuid;
/*    */ 
/*    */ import com.ruoyi.common.utils.DateUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Seq
/*    */ {
/*    */   public static final String commSeqType = "COMMON";
/*    */   public static final String uploadSeqType = "UPLOAD";
/* 19 */   private static AtomicInteger commSeq = new AtomicInteger(1);
/*    */ 
/*    */   
/* 22 */   private static AtomicInteger uploadSeq = new AtomicInteger(1);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final String machineCode = "A";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getId() {
/* 34 */     return getId("COMMON");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getId(String type) {
/* 44 */     AtomicInteger atomicInt = commSeq;
/* 45 */     if ("UPLOAD".equals(type))
/*    */     {
/* 47 */       atomicInt = uploadSeq;
/*    */     }
/* 49 */     return getId(atomicInt, 3);
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
/*    */   public static String getId(AtomicInteger atomicInt, int length) {
/* 61 */     String result = DateUtils.dateTimeNow();
/* 62 */     result = result + "A";
/* 63 */     result = result + getSeq(atomicInt, length);
/* 64 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static synchronized String getSeq(AtomicInteger atomicInt, int length) {
/* 75 */     int value = atomicInt.getAndIncrement();
/*    */ 
/*    */     
/* 78 */     int maxSeq = (int)Math.pow(10.0D, length);
/* 79 */     if (atomicInt.get() >= maxSeq)
/*    */     {
/* 81 */       atomicInt.set(1);
/*    */     }
/*    */     
/* 84 */     return StringUtils.padl(Integer.valueOf(value), length);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/uuid/Seq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */