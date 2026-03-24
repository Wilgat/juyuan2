/*     */ package com.ruoyi.system.domain;
/*     */ 
/*     */ import com.ruoyi.common.core.domain.BaseEntity;
/*     */ import com.ruoyi.common.xss.Xss;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Size;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
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
/*     */ public class SysNotice
/*     */   extends BaseEntity
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long noticeId;
/*     */   private String noticeTitle;
/*     */   private String noticeType;
/*     */   private String noticeContent;
/*     */   private String status;
/*     */   
/*     */   public Long getNoticeId() {
/*  36 */     return this.noticeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoticeId(Long noticeId) {
/*  41 */     this.noticeId = noticeId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoticeTitle(String noticeTitle) {
/*  46 */     this.noticeTitle = noticeTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   @Xss(message = "公告标题不能包含脚本字符")
/*     */   @NotBlank(message = "公告标题不能为空")
/*     */   @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
/*     */   public String getNoticeTitle() {
/*  54 */     return this.noticeTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoticeType(String noticeType) {
/*  59 */     this.noticeType = noticeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoticeType() {
/*  64 */     return this.noticeType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoticeContent(String noticeContent) {
/*  69 */     this.noticeContent = noticeContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNoticeContent() {
/*  74 */     return this.noticeContent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/*  79 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatus() {
/*  84 */     return this.status;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  89 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/*  90 */       .append("noticeId", getNoticeId())
/*  91 */       .append("noticeTitle", getNoticeTitle())
/*  92 */       .append("noticeType", getNoticeType())
/*  93 */       .append("noticeContent", getNoticeContent())
/*  94 */       .append("status", getStatus())
/*  95 */       .append("createBy", getCreateBy())
/*  96 */       .append("createTime", getCreateTime())
/*  97 */       .append("updateBy", getUpdateBy())
/*  98 */       .append("updateTime", getUpdateTime())
/*  99 */       .append("remark", getRemark())
/* 100 */       .toString();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysNotice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */