/*    */ package com.ruoyi.web.controller.monitor;
/*    */ 
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.framework.web.domain.Server;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ @RequestMapping({"/monitor/server"})
/*    */ public class ServerController
/*    */ {
/*    */   @PreAuthorize("@ss.hasPermi('monitor:server:list')")
/*    */   @GetMapping
/*    */   public AjaxResult getInfo() throws Exception {
/* 23 */     Server server = new Server();
/* 24 */     server.copyTo();
/* 25 */     return AjaxResult.success(server);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/monitor/ServerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */