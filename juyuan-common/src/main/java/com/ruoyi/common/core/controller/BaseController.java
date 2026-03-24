/*     */ package com.ruoyi.common.core.controller;
/*     */ 
/*     */ import com.github.pagehelper.PageHelper;
/*     */ import com.github.pagehelper.PageInfo;
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.core.domain.model.LoginUser;
/*     */ import com.ruoyi.common.core.page.PageDomain;
/*     */ import com.ruoyi.common.core.page.TableDataInfo;
/*     */ import com.ruoyi.common.core.page.TableSupport;
/*     */ import com.ruoyi.common.utils.DateUtils;
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.PageUtils;
/*     */ import com.ruoyi.common.utils.SecurityUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import com.ruoyi.common.utils.sql.SqlUtil;
/*     */ import java.beans.PropertyEditorSupport;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.web.bind.WebDataBinder;
/*     */ import org.springframework.web.bind.annotation.InitBinder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseController
/*     */ {
/*  29 */   protected final Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @InitBinder
/*     */   public void initBinder(WebDataBinder binder) {
/*  38 */     binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
/*     */         {
/*     */           
/*     */           public void setAsText(String text)
/*     */           {
/*  43 */             setValue(DateUtils.parseDate(text));
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startPage() {
/*  53 */     PageUtils.startPage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startOrderBy() {
/*  61 */     PageDomain pageDomain = TableSupport.buildPageRequest();
/*  62 */     if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
/*     */       
/*  64 */       String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
/*  65 */       PageHelper.orderBy(orderBy);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clearPage() {
/*  74 */     PageUtils.clearPage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TableDataInfo getDataTable(List<?> list) {
/*  83 */     TableDataInfo rspData = new TableDataInfo();
/*  84 */     rspData.setCode(200);
/*  85 */     rspData.setMsg("查询成功");
/*  86 */     rspData.setRows(list);
/*  87 */     rspData.setTotal((new PageInfo(list)).getTotal());
/*  88 */     return rspData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult success() {
/*  96 */     String message = MessageUtils.message("option.success", new Object[0]);
/*  97 */     return AjaxResult.success(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult error() {
/* 105 */     return AjaxResult.error(MessageUtils.message("option.error", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult success(String message) {
/* 113 */     return AjaxResult.success(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult success(Object data) {
/* 121 */     return AjaxResult.success(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult error(String message) {
/* 129 */     return AjaxResult.error(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult warn(String message) {
/* 137 */     return AjaxResult.warn(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AjaxResult toAjax(int rows) {
/* 148 */     return (rows > 0) ? AjaxResult.success() : AjaxResult.error();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AjaxResult toAjax(boolean result) {
/* 159 */     return result ? success() : error();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String redirect(String url) {
/* 167 */     return StringUtils.format("redirect:{}", new Object[] { url });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginUser getLoginUser() {
/* 175 */     return SecurityUtils.getLoginUser();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getUserId() {
/* 183 */     return getLoginUser().getUserId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getDeptId() {
/* 191 */     return getLoginUser().getDeptId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 199 */     return getLoginUser().getUsername();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/controller/BaseController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */