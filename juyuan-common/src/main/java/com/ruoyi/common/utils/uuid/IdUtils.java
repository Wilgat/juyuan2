/*    */ package com.ruoyi.common.utils.uuid;
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
/*    */ 
/*    */ 
/*    */ public class IdUtils
/*    */ {
/*    */   public static String randomUUID() {
/* 17 */     return UUID.randomUUID().toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String simpleUUID() {
/* 27 */     return UUID.randomUUID().toString(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String fastUUID() {
/* 37 */     return UUID.fastUUID().toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String fastSimpleUUID() {
/* 47 */     return UUID.fastUUID().toString(true);
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/uuid/IdUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */