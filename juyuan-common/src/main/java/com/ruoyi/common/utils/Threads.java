/*    */ package com.ruoyi.common.utils;
/*    */ 
/*    */ import java.util.concurrent.CancellationException;
/*    */ import java.util.concurrent.ExecutionException;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Future;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Threads
/*    */ {
/* 18 */   private static final Logger logger = LoggerFactory.getLogger(Threads.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sleep(long milliseconds) {
/*    */     try {
/* 27 */       Thread.sleep(milliseconds);
/*    */     }
/* 29 */     catch (InterruptedException e) {
/*    */       return;
/*    */     } 
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
/*    */   
/*    */   public static void shutdownAndAwaitTermination(ExecutorService pool) {
/* 44 */     if (pool != null && !pool.isShutdown()) {
/*    */       
/* 46 */       pool.shutdown();
/*    */       
/*    */       try {
/* 49 */         if (!pool.awaitTermination(120L, TimeUnit.SECONDS))
/*    */         {
/* 51 */           pool.shutdownNow();
/* 52 */           if (!pool.awaitTermination(120L, TimeUnit.SECONDS))
/*    */           {
/* 54 */             logger.info("Pool did not terminate");
/*    */           }
/*    */         }
/*    */       
/* 58 */       } catch (InterruptedException ie) {
/*    */         
/* 60 */         pool.shutdownNow();
/* 61 */         Thread.currentThread().interrupt();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void printException(Runnable r, Throwable t) {
/* 71 */     if (t == null && r instanceof Future) {
/*    */       
/*    */       try {
/*    */         
/* 75 */         Future<?> future = (Future)r;
/* 76 */         if (future.isDone())
/*    */         {
/* 78 */           future.get();
/*    */         }
/*    */       }
/* 81 */       catch (CancellationException ce) {
/*    */         
/* 83 */         t = ce;
/*    */       }
/* 85 */       catch (ExecutionException ee) {
/*    */         
/* 87 */         t = ee.getCause();
/*    */       }
/* 89 */       catch (InterruptedException ie) {
/*    */         
/* 91 */         Thread.currentThread().interrupt();
/*    */       } 
/*    */     }
/* 94 */     if (t != null)
/*    */     {
/* 96 */       logger.error(t.getMessage(), t);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/Threads.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */