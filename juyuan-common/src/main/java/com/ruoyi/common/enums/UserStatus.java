/*    */ package com.ruoyi.common.enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum UserStatus
/*    */ {
/* 10 */   OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");
/*    */   
/*    */   private final String info;
/*    */   
/*    */   private final String code;
/*    */   
/*    */   UserStatus(String code, String info) {
/* 17 */     this.code = code;
/* 18 */     this.info = info;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode() {
/* 23 */     return this.code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getInfo() {
/* 28 */     return this.info;
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/enums/UserStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */