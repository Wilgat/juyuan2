package com.ruoyi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels {
  Excel[] value();
}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/annotation/Excels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */