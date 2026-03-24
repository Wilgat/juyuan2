/*    */ package com.ruoyi.framework.manager.factory;
/*    */ 
/*    */ import com.ruoyi.common.utils.LogUtils;
/*    */ import com.ruoyi.common.utils.ServletUtils;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.common.utils.ip.AddressUtils;
/*    */ import com.ruoyi.common.utils.ip.IpUtils;
/*    */ import com.ruoyi.common.utils.spring.SpringUtils;
/*    */ import com.ruoyi.system.domain.SysLogininfor;
/*    */ import com.ruoyi.system.domain.SysOperLog;
/*    */ import com.ruoyi.system.service.ISysLogininforService;
/*    */ import com.ruoyi.system.service.ISysOperLogService;
/*    */ import eu.bitwalker.useragentutils.UserAgent;
/*    */ import java.util.TimerTask;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AsyncFactory
/*    */ {
/* 26 */   private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");
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
/*    */   public static TimerTask recordLogininfor(final String username, final String status, final String message, Object... args) {
/* 40 */     final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
/* 41 */     final String ip = IpUtils.getIpAddr();
/* 42 */     return new TimerTask()
/*    */       {
/*    */         
/*    */         public void run()
/*    */         {
/* 47 */           String address = AddressUtils.getRealAddressByIP(ip);
/* 48 */           StringBuilder s = new StringBuilder();
/* 49 */           s.append(LogUtils.getBlock(ip));
/* 50 */           s.append(address);
/* 51 */           s.append(LogUtils.getBlock(username));
/* 52 */           s.append(LogUtils.getBlock(status));
/* 53 */           s.append(LogUtils.getBlock(message));
/*    */           
/* 55 */           AsyncFactory.sys_user_logger.info(s.toString(), args);
/*    */           
/* 57 */           String os = userAgent.getOperatingSystem().getName();
/*    */           
/* 59 */           String browser = userAgent.getBrowser().getName();
/*    */           
/* 61 */           SysLogininfor logininfor = new SysLogininfor();
/* 62 */           logininfor.setUserName(username);
/* 63 */           logininfor.setIpaddr(ip);
/* 64 */           logininfor.setLoginLocation(address);
/* 65 */           logininfor.setBrowser(browser);
/* 66 */           logininfor.setOs(os);
/* 67 */           logininfor.setMsg(message);
/*    */           
/* 69 */           if (StringUtils.equalsAny(status, new CharSequence[] { "Success", "Logout", "Register" })) {
/*    */             
/* 71 */             logininfor.setStatus("0");
/*    */           }
/* 73 */           else if ("Error".equals(status)) {
/*    */             
/* 75 */             logininfor.setStatus("1");
/*    */           } 
/*    */           
/* 78 */           ((ISysLogininforService)SpringUtils.getBean(ISysLogininforService.class)).insertLogininfor(logininfor);
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TimerTask recordOper(final SysOperLog operLog) {
/* 91 */     return new TimerTask()
/*    */       {
/*    */ 
/*    */         
/*    */         public void run()
/*    */         {
/* 97 */           operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
/* 98 */           ((ISysOperLogService)SpringUtils.getBean(ISysOperLogService.class)).insertOperlog(operLog);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/manager/factory/AsyncFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */