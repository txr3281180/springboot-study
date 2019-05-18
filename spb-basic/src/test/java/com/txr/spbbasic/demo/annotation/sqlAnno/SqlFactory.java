package com.txr.spbbasic.demo.annotation.sqlAnno;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过注解生成sql
 */
public class SqlFactory {

    private static final String SELECT_FROM = "select * from ";
    private static final String WHERE = " where 1 = 1";
    private static final String AND = " and ";
    private static final String EQ = " = ";


    public static String getSql(Person p) {

        StringBuilder sb = new StringBuilder();
        //得到class (这里换成用参数传入)
        Class clazz = p.getClass();

        //判断注解是否存在
        boolean exists = clazz.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }

        //获取注解的名字
        Table pt = (Table) clazz.getAnnotation(Table.class);
        String tableName = pt.value();

        //拼接sql
        sb.append(SELECT_FROM).append(tableName).append(WHERE);

        //遍历所有字段(字段上的注解)
        Field[] columns = clazz.getDeclaredFields();

        for (Field column : columns) {
            //拿到字段的名字
            boolean fExists = column.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }

            Column col = column.getAnnotation(Column.class);
            String columnName = col.value();

            //拿到字段值
            String filedName = column.getName();

            //得到属性对应的 get方法名
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() //第一个字母大写
                    + filedName.substring(1);
            Object filedValue = null; //属性的值 object 通用类型
            try {
                //获得对应的方法
                Method getMethod = clazz.getMethod(getMethodName);
                //调用
                filedValue = getMethod.invoke(p);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //如果为 null 或者 int型为 0 不做处理
            if(filedValue == null ||
                    (filedValue instanceof Integer && (Integer)filedValue==0)){
                continue;
            }

            /*可以添加处理 多个参数 in() 情况的sql*/

            sb.append(AND).append(filedName);
            if(filedValue instanceof Integer){
                sb.append(EQ).append("'").append(filedValue).append("'");
            }else {
                sb.append(EQ).append(filedValue);
            }
        }

        return sb.toString();
    }
}
