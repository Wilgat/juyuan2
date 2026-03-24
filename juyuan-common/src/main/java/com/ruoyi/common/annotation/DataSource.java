package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.DataSourceType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
  DataSourceType value() default DataSourceType.MASTER;
}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/annotation/DataSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */