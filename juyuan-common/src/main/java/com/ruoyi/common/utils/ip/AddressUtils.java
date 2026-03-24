/*    */ package com.ruoyi.common.utils.ip;
/*    */ 
/*    */ import com.alibaba.fastjson2.JSON;
/*    */ import com.alibaba.fastjson2.JSONObject;
/*    */ import com.ruoyi.common.config.RuoYiConfig;
/*    */ import com.ruoyi.common.utils.StringUtils;
/*    */ import com.ruoyi.common.utils.http.HttpUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddressUtils
/*    */ {
/* 19 */   private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);
/*    */ 
/*    */   
/*    */   public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
/*    */ 
/*    */   
/*    */   public static final String UNKNOWN = "XX XX";
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getRealAddressByIP(String ip) {
/* 30 */     if (IpUtils.internalIp(ip))
/*    */     {
/* 32 */       return "内网IP";
/*    */     }
/* 34 */     if (RuoYiConfig.isAddressEnabled()) {
/*    */       
/*    */       try {
/*    */         
/* 38 */         String rspStr = HttpUtils.sendGet("http://whois.pconline.com.cn/ipJson.jsp", "ip=" + ip + "&json=true", "GBK");
/* 39 */         if (StringUtils.isEmpty(rspStr)) {
/*    */           
/* 41 */           log.error("获取地理位置异常 {}", ip);
/* 42 */           return "XX XX";
/*    */         } 
/* 44 */         JSONObject obj = JSON.parseObject(rspStr);
/* 45 */         String region = obj.getString("pro");
/* 46 */         String city = obj.getString("city");
/* 47 */         return String.format("%s %s", new Object[] { region, city });
/*    */       }
/* 49 */       catch (Exception e) {
/*    */         
/* 51 */         log.error("获取地理位置异常 {}", ip);
/*    */       } 
/*    */     }
/* 54 */     return "XX XX";
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/ip/AddressUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */