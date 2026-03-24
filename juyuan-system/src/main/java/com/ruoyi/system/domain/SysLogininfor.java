/*     */ package com.ruoyi.system.domain;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonFormat;
/*     */ import com.ruoyi.common.annotation.Excel;
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SysLogininfor
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long infoId;
/*     */   @Excel(name = "用户账号")
/*     */   private String userName;
/*     */   @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
/*     */   private String status;
/*     */   @Excel(name = "登录地址")
/*     */   private String ipaddr;
/*     */   @Excel(name = "登录地点")
/*     */   private String loginLocation;
/*     */   @Excel(name = "浏览器")
/*     */   private String browser;
/*     */   @Excel(name = "操作系统")
/*     */   private String os;
/*     */   @Excel(name = "提示消息")
/*     */   private String msg;
/*     */   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
/*     */   @Excel(name = "访问时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
/*     */   private Date loginTime;
/*     */   
/*     */   public Long getInfoId() {
/*  57 */     return this.infoId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInfoId(Long infoId) {
/*  62 */     this.infoId = infoId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserName() {
/*  67 */     return this.userName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUserName(String userName) {
/*  72 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/*  77 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/*  82 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIpaddr() {
/*  87 */     return this.ipaddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIpaddr(String ipaddr) {
/*  92 */     this.ipaddr = ipaddr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLoginLocation() {
/*  97 */     return this.loginLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginLocation(String loginLocation) {
/* 102 */     this.loginLocation = loginLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBrowser() {
/* 107 */     return this.browser;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBrowser(String browser) {
/* 112 */     this.browser = browser;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOs() {
/* 117 */     return this.os;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOs(String os) {
/* 122 */     this.os = os;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMsg() {
/* 127 */     return this.msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMsg(String msg) {
/* 132 */     this.msg = msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getLoginTime() {
/* 137 */     return this.loginTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoginTime(Date loginTime) {
/* 142 */     this.loginTime = loginTime;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysLogininfor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */