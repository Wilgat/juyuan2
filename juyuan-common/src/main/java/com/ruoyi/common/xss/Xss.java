package com.ruoyi.common.xss;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Constraint(validatedBy = {XssValidator.class})
public @interface Xss {
  String message() default "不允许任何脚本运行";
  
  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/xss/Xss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */