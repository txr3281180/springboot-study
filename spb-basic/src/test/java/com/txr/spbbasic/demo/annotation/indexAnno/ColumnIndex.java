package com.txr.spbbasic.demo.annotation.indexAnno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnIndex {

    /**
     *  csv 数据对应实体类的属性。
     *  被该注解标识的属性，其value值对应csv 字段的下标
     */

    int value();
}
