/*    */ package com.ruoyi.web.controller.monitor;
/*    */ 
/*    */ import com.ruoyi.common.annotation.Log;
/*    */ import com.ruoyi.common.core.controller.BaseController;
/*    */ import com.ruoyi.common.core.domain.AjaxResult;
/*    */ import com.ruoyi.common.core.domain.model.LoginUser;
/*    */ import com.ruoyi.common.core.page.TableDataInfo;
/*    */ import com.ruoyi.common.core.redis.RedisCache;
/*    */ import com.ruoyi.common.enums.BusinessType;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.system.domain.SysUserOnline;
/*    */ import com.ruoyi.system.service.ISysUserOnlineService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.access.prepost.PreAuthorize;
/*    */ import org.springframework.web.bind.annotation.DeleteMapping;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
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
/*    */ @RestController
/*    */ @RequestMapping({"/monitor/online"})
/*    */ public class SysUserOnlineController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private ISysUserOnlineService userOnlineService;
/*    */   @Autowired
/*    */   private RedisCache redisCache;
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:online:list')")
/*    */   @GetMapping({"/list"})
/*    */   public TableDataInfo list(String ipaddr, String userName) {
/* 45 */     Collection<String> keys = this.redisCache.keys("login_tokens:*");
/* 46 */     List<SysUserOnline> userOnlineList = new ArrayList<>();
/* 47 */     for (String key : keys) {
/*    */       
/* 49 */       LoginUser user = (LoginUser)this.redisCache.getCacheObject(key);
/* 50 */       if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
/*    */         
/* 52 */         userOnlineList.add(this.userOnlineService.selectOnlineByInfo(ipaddr, userName, user)); continue;
/*    */       } 
/* 54 */       if (StringUtils.isNotEmpty(ipaddr)) {
/*    */         
/* 56 */         userOnlineList.add(this.userOnlineService.selectOnlineByIpaddr(ipaddr, user)); continue;
/*    */       } 
/* 58 */       if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser())) {
/*    */         
/* 60 */         userOnlineList.add(this.userOnlineService.selectOnlineByUserName(userName, user));
/*    */         
/*    */         continue;
/*    */       } 
/* 64 */       userOnlineList.add(this.userOnlineService.loginUserToUserOnline(user));
/*    */     } 
/*    */     
/* 67 */     Collections.reverse(userOnlineList);
/* 68 */     userOnlineList.removeAll(Collections.singleton(null));
/* 69 */     return getDataTable(userOnlineList);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
/*    */   @Log(title = "在线用户", businessType = BusinessType.FORCE)
/*    */   @DeleteMapping({"/{tokenId}"})
/*    */   public AjaxResult forceLogout(@PathVariable String tokenId) {
/* 80 */     this.redisCache.deleteObject("login_tokens:" + tokenId);
/* 81 */     return success();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/web/controller/monitor/SysUserOnlineController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */