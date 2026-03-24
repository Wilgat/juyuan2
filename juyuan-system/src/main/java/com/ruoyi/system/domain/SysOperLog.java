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
/*     */ public class SysOperLog
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Excel(name = "操作序号", cellType = Excel.ColumnType.NUMERIC)
/*     */   private Long operId;
/*     */   @Excel(name = "操作模块")
/*     */   private String title;
/*     */   @Excel(name = "业务类型", readConverterExp = "0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据")
/*     */   private Integer businessType;
/*     */   private Integer[] businessTypes;
/*     */   @Excel(name = "请求方法")
/*     */   private String method;
/*     */   @Excel(name = "请求方式")
/*     */   private String requestMethod;
/*     */   @Excel(name = "操作类别", readConverterExp = "0=其它,1=后台用户,2=手机端用户")
/*     */   private Integer operatorType;
/*     */   @Excel(name = "操作人员")
/*     */   private String operName;
/*     */   @Excel(name = "部门名称")
/*     */   private String deptName;
/*     */   @Excel(name = "请求地址")
/*     */   private String operUrl;
/*     */   @Excel(name = "操作地址")
/*     */   private String operIp;
/*     */   @Excel(name = "操作地点")
/*     */   private String operLocation;
/*     */   @Excel(name = "请求参数")
/*     */   private String operParam;
/*     */   @Excel(name = "返回参数")
/*     */   private String jsonResult;
/*     */   @Excel(name = "状态", readConverterExp = "0=正常,1=异常")
/*     */   private Integer status;
/*     */   @Excel(name = "错误消息")
/*     */   private String errorMsg;
/*     */   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
/*     */   @Excel(name = "操作时间", width = 30.0D, dateFormat = "yyyy-MM-dd HH:mm:ss")
/*     */   private Date operTime;
/*     */   @Excel(name = "消耗时间", suffix = "毫秒")
/*     */   private Long costTime;
/*     */   
/*     */   public Long getOperId() {
/*  92 */     return this.operId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperId(Long operId) {
/*  97 */     this.operId = operId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 102 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 107 */     this.title = title;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getBusinessType() {
/* 112 */     return this.businessType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusinessType(Integer businessType) {
/* 117 */     this.businessType = businessType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer[] getBusinessTypes() {
/* 122 */     return this.businessTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusinessTypes(Integer[] businessTypes) {
/* 127 */     this.businessTypes = businessTypes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMethod() {
/* 132 */     return this.method;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMethod(String method) {
/* 137 */     this.method = method;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRequestMethod() {
/* 142 */     return this.requestMethod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequestMethod(String requestMethod) {
/* 147 */     this.requestMethod = requestMethod;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getOperatorType() {
/* 152 */     return this.operatorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperatorType(Integer operatorType) {
/* 157 */     this.operatorType = operatorType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOperName() {
/* 162 */     return this.operName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperName(String operName) {
/* 167 */     this.operName = operName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDeptName() {
/* 172 */     return this.deptName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeptName(String deptName) {
/* 177 */     this.deptName = deptName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOperUrl() {
/* 182 */     return this.operUrl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperUrl(String operUrl) {
/* 187 */     this.operUrl = operUrl;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOperIp() {
/* 192 */     return this.operIp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperIp(String operIp) {
/* 197 */     this.operIp = operIp;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOperLocation() {
/* 202 */     return this.operLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperLocation(String operLocation) {
/* 207 */     this.operLocation = operLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOperParam() {
/* 212 */     return this.operParam;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperParam(String operParam) {
/* 217 */     this.operParam = operParam;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJsonResult() {
/* 222 */     return this.jsonResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJsonResult(String jsonResult) {
/* 227 */     this.jsonResult = jsonResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getStatus() {
/* 232 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(Integer status) {
/* 237 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getErrorMsg() {
/* 242 */     return this.errorMsg;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setErrorMsg(String errorMsg) {
/* 247 */     this.errorMsg = errorMsg;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getOperTime() {
/* 252 */     return this.operTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOperTime(Date operTime) {
/* 257 */     this.operTime = operTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getCostTime() {
/* 262 */     return this.costTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCostTime(Long costTime) {
/* 267 */     this.costTime = costTime;
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysOperLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */