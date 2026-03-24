/*     */ package com.ruoyi.framework.web.exception;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.AjaxResult;
/*     */ import com.ruoyi.common.exception.DemoModeException;
/*     */ import com.ruoyi.common.exception.ServiceException;
/*     */ import com.ruoyi.common.utils.StringUtils;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.security.access.AccessDeniedException;
/*     */ import org.springframework.validation.BindException;
/*     */ import org.springframework.validation.ObjectError;
/*     */ import org.springframework.web.HttpRequestMethodNotSupportedException;
/*     */ import org.springframework.web.bind.MethodArgumentNotValidException;
/*     */ import org.springframework.web.bind.MissingPathVariableException;
/*     */ import org.springframework.web.bind.annotation.ExceptionHandler;
/*     */ import org.springframework.web.bind.annotation.RestControllerAdvice;
/*     */ import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RestControllerAdvice
/*     */ public class GlobalExceptionHandler
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({AccessDeniedException.class})
/*     */   public AjaxResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
/*  36 */     String requestURI = request.getRequestURI();
/*  37 */     log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
/*  38 */     return AjaxResult.error(403, "没有权限，请联系管理员授权");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
/*     */   public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
/*  48 */     String requestURI = request.getRequestURI();
/*  49 */     log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
/*  50 */     return AjaxResult.error(e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({ServiceException.class})
/*     */   public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request) {
/*  59 */     log.error(e.getMessage(), (Throwable)e);
/*  60 */     Integer code = e.getCode();
/*  61 */     return StringUtils.isNotNull(code) ? AjaxResult.error(code.intValue(), e.getMessage()) : AjaxResult.error(e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({MissingPathVariableException.class})
/*     */   public AjaxResult handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
/*  70 */     String requestURI = request.getRequestURI();
/*  71 */     log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI, e);
/*  72 */     return AjaxResult.error(String.format("请求路径中缺少必需的路径变量[%s]", new Object[] { e.getVariableName() }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({MethodArgumentTypeMismatchException.class})
/*     */   public AjaxResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
/*  81 */     String requestURI = request.getRequestURI();
/*  82 */     log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
/*  83 */     return AjaxResult.error(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", new Object[] { e.getName(), e.getRequiredType().getName(), e.getValue() }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({RuntimeException.class})
/*     */   public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
/*  92 */     String requestURI = request.getRequestURI();
/*  93 */     log.error("请求地址'{}',发生未知异常.", requestURI, e);
/*  94 */     return AjaxResult.error(e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({Exception.class})
/*     */   public AjaxResult handleException(Exception e, HttpServletRequest request) {
/* 103 */     String requestURI = request.getRequestURI();
/* 104 */     log.error("请求地址'{}',发生系统异常.", requestURI, e);
/* 105 */     return AjaxResult.error(e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({BindException.class})
/*     */   public AjaxResult handleBindException(BindException e) {
/* 114 */     log.error(e.getMessage(), (Throwable)e);
/* 115 */     String message = ((ObjectError)e.getAllErrors().get(0)).getDefaultMessage();
/* 116 */     return AjaxResult.error(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({MethodArgumentNotValidException.class})
/*     */   public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
/* 125 */     log.error(e.getMessage(), (Throwable)e);
/* 126 */     String message = e.getBindingResult().getFieldError().getDefaultMessage();
/* 127 */     return AjaxResult.error(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ExceptionHandler({DemoModeException.class})
/*     */   public AjaxResult handleDemoModeException(DemoModeException e) {
/* 136 */     return AjaxResult.error("演示模式，不允许操作");
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/exception/GlobalExceptionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */