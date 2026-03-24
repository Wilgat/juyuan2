/*    */ package com.ruoyi.system.domain;
/*    */ 
/*    */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*    */ import org.apache.commons.lang3.builder.ToStringStyle;
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
/*    */ public class SysUserRole
/*    */ {
/*    */   private Long userId;
/*    */   private Long roleId;
/*    */   
/*    */   public Long getUserId() {
/* 21 */     return this.userId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setUserId(Long userId) {
/* 26 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getRoleId() {
/* 31 */     return this.roleId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRoleId(Long roleId) {
/* 36 */     this.roleId = roleId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 42 */       .append("userId", getUserId())
/* 43 */       .append("roleId", getRoleId())
/* 44 */       .toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysUserRole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */