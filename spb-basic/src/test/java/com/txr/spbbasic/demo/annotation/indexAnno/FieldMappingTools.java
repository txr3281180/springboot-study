package com.txr.spbbasic.demo.annotation.indexAnno;

import com.txr.spbbasic.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinrui.tian on 2018/12/7
 */
public class FieldMappingTools {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public static<T> List<T> classListMapping (Class<T> clazz, List<String> data) {
        if (data == null || data.size() == 0) return null;

        List<T> result = new ArrayList<>(data.size());
        for (String datum : data) {
            T t = classMapping(clazz, datum);
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> T classMapping (Class<T> clazz, String fieldStr) {
        if (fieldStr == null) return null;
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return t;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return t;
        }

        String[] fieldVal = fieldStr.split(",", -1);
        boolean exists = clazz.isAnnotationPresent(ColumnIndexList.class);
        if (!exists) return null;

        ColumnIndexList annotation = clazz.getAnnotation(ColumnIndexList.class);
        int[] values = annotation.values();
        Field[] columns = clazz.getDeclaredFields();

        int fieLen = fieldVal.length;
        int valLen = values.length;
        int colLen = columns.length;

        if (valLen != colLen && valLen != fieLen) {
            logger.error("The values length: " + valLen +"], The number of fields:" + colLen + ", The number of data column:" + fieLen);
        }

        int len = valLen > colLen ? colLen : valLen;
        for (int i = 0; i < len; i++) {
            if (values[i] < 0) continue;
            if (fieLen < values[i]) logger.warn("The " + values[i] + " column of the data does not exist.");

            String filedName = columns[i].getName();
            //Class<?> type = columns[i].getType();  // TODO 需要处理属性为不同类型的赋值

            //得到属性对应的 get方法名
            String setMethodName = "set" + filedName.substring(0, 1).toUpperCase() //第一个字母大写
                    + filedName.substring(1);

            try {
                //获得对应的方法
                Method getMethod = clazz.getMethod(setMethodName, String.class);
                //调用
                getMethod.invoke(t, fieldVal[values[i]]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public static <T> T columnMapping (T t, String fieldStr) {
        Class<?> clazz = t.getClass();

        if (fieldStr == null) {
            return null;
        }

        String[] fieldVal = fieldStr.split(",", -1);

        //遍历所有字段(字段上的注解)
        Field[] columns = clazz.getDeclaredFields();
        for (Field column : columns) {
            //拿到字段的名字
            boolean exists = column.isAnnotationPresent(ColumnIndex.class);
            if (!exists) {
                continue;
            }

            ColumnIndex col = column.getAnnotation(ColumnIndex.class);
            int index = col.value();

            //拿到字段值
            String filedName = column.getName();

            //得到属性对应的 get方法名
            String setMethodName = "set" + filedName.substring(0, 1).toUpperCase() //第一个字母大写
                    + filedName.substring(1);
            try {
                //获得对应的方法
                Method getMethod = clazz.getMethod(setMethodName, String.class);
                //调用
                getMethod.invoke(t, fieldVal[index]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
