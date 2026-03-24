/*    */ package com.ruoyi.web.controller.system;
/*    */ 
/*    */ import com.ruoyi.common.config.RuoYiConfig;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RestController;
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
/*    */ @RestController
/*    */ public class SysIndexController
/*    */ {
/*    */   @Autowired
/*    */   private RuoYiConfig ruoyiConfig;
/*    */   
/*    */   @RequestMapping({"/"})
/*    */   public String index() {
/* 27 */     return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", new Object[] { this.ruoyiConfig.getName(), this.ruoyiConfig.getVersion() });
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysIndexController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */