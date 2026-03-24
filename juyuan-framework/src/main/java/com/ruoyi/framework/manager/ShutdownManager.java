/*    */ package com.ruoyi.framework.manager;
/*    */ 
/*    */ import javax.annotation.PreDestroy;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ShutdownManager
/*    */ {
/* 16 */   private static final Logger logger = LoggerFactory.getLogger("sys-user");
/*    */ 
/*    */   
/*    */   @PreDestroy
/*    */   public void destroy() {
/* 21 */     shutdownAsyncManager();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void shutdownAsyncManager() {
/*    */     try {
/* 31 */       logger.info("====关闭后台任务任务线程池====");
/* 32 */       AsyncManager.me().shutdown();
/*    */     }
/* 34 */     catch (Exception e) {
/*    */       
/* 36 */       logger.error(e.getMessage(), e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/manager/ShutdownManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */