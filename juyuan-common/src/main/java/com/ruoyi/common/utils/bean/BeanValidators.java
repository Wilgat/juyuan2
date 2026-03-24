/*    */ package com.ruoyi.common.utils.bean;
/*    */ 
/*    */ import java.util.Set;
/*    */ import javax.validation.ConstraintViolation;
/*    */ import javax.validation.ConstraintViolationException;
/*    */ import javax.validation.Validator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BeanValidators
/*    */ {
/*    */   public static void validateWithException(Validator validator, Object object, Class<?>... groups) throws ConstraintViolationException {
/* 18 */     Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
/* 19 */     if (!constraintViolations.isEmpty())
/*    */     {
/* 21 */       throw new ConstraintViolationException(constraintViolations);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/bean/BeanValidators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */