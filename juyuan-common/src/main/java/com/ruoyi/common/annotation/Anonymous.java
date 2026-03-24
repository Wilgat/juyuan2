package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/annotation/Anonymous.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */