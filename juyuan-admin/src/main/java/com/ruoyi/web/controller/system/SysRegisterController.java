/*    */ package com.ruoyi.web.controller.system;
/*    */ 
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.RegisterBody;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.framework.web.service.SysRegisterService;
/*    */ import com.ruoyi.system.service.ISysConfigService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestBody;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RestController
/*    */ public class SysRegisterController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private SysRegisterService registerService;
/*    */   @Autowired
/*    */   private ISysConfigService configService;
/*    */   
/*    */   @PostMapping({"/register"})
/*    */   public AjaxResult register(@RequestBody RegisterBody user) {
/* 31 */     if (!"true".equals(this.configService.selectConfigByKey("sys.account.registerUser")))
/*    */     {
/* 33 */       return error("当前系统没有开启注册功能！");
/*    */     }
/* 35 */     String msg = this.registerService.register(user);
/* 36 */     return StringUtils.isEmpty(msg) ? success() : error(msg);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/system/SysRegisterController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */