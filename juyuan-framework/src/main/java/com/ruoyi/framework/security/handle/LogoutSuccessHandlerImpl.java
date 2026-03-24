/*    */ package com.ruoyi.framework.security.handle;
/*    */ 
/*    */ import cn.hutool.core.util.ObjectUtil;
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.utils.ServletUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.framework.manager.AsyncManager;
/*    */ import com.ruoyi.framework.manager.factory.AsyncFactory;
/*    */ import com.ruoyi.framework.web.service.SysLoginService;
/*    */ import com.ruoyi.framework.web.service.TokenService;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.security.core.Authentication;
/*    */ import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
/*    */ @Configuration
/*    */ public class LogoutSuccessHandlerImpl
/*    */   implements LogoutSuccessHandler
/*    */ {
/*    */   @Autowired
/*    */   private TokenService tokenService;
/*    */   @Autowired
/*    */   private SysLoginService sysLoginService;
/*    */   
/*    */   public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
/* 45 */     LoginUser loginUser = this.tokenService.getLoginUser(request);
/* 46 */     if (StringUtils.isNotNull(loginUser)) {
/* 47 */       String userName = loginUser.getUsername();
/*    */       
/* 49 */       this.tokenService.delLoginUser(loginUser.getToken());
/* 50 */       if (ObjectUtil.isNotNull(loginUser.getUser()) && StringUtils.isNotNull(loginUser.getUser().getCid())) {
/* 51 */         this.sysLoginService.appLogout(loginUser.getUserId());
/*    */       }
/*    */       
/* 54 */       AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, "Logout", "退出成功", new Object[0]));
/*    */     } 
/* 56 */     ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/security/handle/LogoutSuccessHandlerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */