/*     */ package com.ruoyi.common.core.domain;
/*     */ 
/*     */ import com.ruoyi.common.utils.MessageUtils;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import java.util.HashMap;
/*     */ import java.util.Objects;
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
/*     */ public class AjaxResult
/*     */   extends HashMap<String, Object>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String CODE_TAG = "code";
/*     */   public static final String MSG_TAG = "msg";
/*     */   public static final String DATA_TAG = "data";
/*     */   
/*     */   public AjaxResult() {}
/*     */   
/*     */   public AjaxResult(int code, String msg) {
/*  42 */     super.put("code", Integer.valueOf(code));
/*  43 */     super.put("msg", msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AjaxResult(int code, String msg, Object data) {
/*  55 */     super.put("code", Integer.valueOf(code));
/*  56 */     super.put("msg", msg);
/*  57 */     if (StringUtils.isNotNull(data))
/*     */     {
/*  59 */       super.put("data", data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult success() {
/*  70 */     return success(MessageUtils.message("option.success", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult success(Object data) {
/*  80 */     return success(MessageUtils.message("option.success", new Object[0]), data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult success(String msg) {
/*  91 */     return success(msg, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult success(String msg, Object data) {
/* 103 */     return new AjaxResult(200, msg, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult warn(String msg) {
/* 114 */     return warn(msg, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult warn(String msg, Object data) {
/* 126 */     return new AjaxResult(601, msg, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult error() {
/* 136 */     return error(MessageUtils.message("option.error", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult error(String msg) {
/* 147 */     return error(msg, (Object)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult error(String msg, Object data) {
/* 159 */     return new AjaxResult(500, msg, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AjaxResult error(int code, String msg) {
/* 171 */     return new AjaxResult(code, msg, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuccess() {
/* 181 */     return Objects.equals(Integer.valueOf(200), get("code"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWarn() {
/* 191 */     return Objects.equals(Integer.valueOf(601), get("code"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isError() {
/* 201 */     return Objects.equals(Integer.valueOf(500), get("code"));
/*     */   }
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
/*     */   public AjaxResult put(String key, Object value) {
/* 214 */     super.put(key, value);
/* 215 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/core/domain/AjaxResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */