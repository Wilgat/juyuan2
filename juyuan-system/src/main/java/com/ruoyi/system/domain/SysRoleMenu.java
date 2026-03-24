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
/*    */ public class SysRoleMenu
/*    */ {
/*    */   private Long roleId;
/*    */   private Long menuId;
/*    */   
/*    */   public Long getRoleId() {
/* 21 */     return this.roleId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRoleId(Long roleId) {
/* 26 */     this.roleId = roleId;
/*    */   }
/*    */ 
/*    */   
/*    */   public Long getMenuId() {
/* 31 */     return this.menuId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMenuId(Long menuId) {
/* 36 */     this.menuId = menuId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE))
/* 42 */       .append("roleId", getRoleId())
/* 43 */       .append("menuId", getMenuId())
/* 44 */       .toString();
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/domain/SysRoleMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */