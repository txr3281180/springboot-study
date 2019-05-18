package com.txr.spbbasic.demo.annotation.indexAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xinrui.tian on 2018/12/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnIndexList {

    /**
     *  csv 数据对应实体类的属性。
     *  values ：csv数据','分割后的元素依次对应实体类的属性的下标列表， -1表示该对应的属性忽略不赋值
     */

    int[] values();
}
