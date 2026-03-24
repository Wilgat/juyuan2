package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OperatorType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
  String title() default "";
  
  BusinessType businessType() default BusinessType.OTHER;
  
  OperatorType operatorType() default OperatorType.MANAGE;
  
  boolean isSaveRequestData() default true;
  
  boolean isSaveResponseData() default true;
  
  String[] excludeParamNames() default {};
}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/annotation/Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */