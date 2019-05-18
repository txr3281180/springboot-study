package com.txr.spbbasic.model.flyweight;

public class MainClass {

    public static void main(String[] args) {
        //创建师范学校
        TeacherSchool ts = new TeacherSchool();

        //从工厂清楚相应的数据
        Teacher t1 = ts.getTeacher("卡卡西");  //第一次获取，没有则创建一个新对象
        Teacher t2 = ts.getTeacher("凯");
        Teacher t4 = ts.getTeacher("卡卡西");  //第二次获取，直接获取第一次创建的对象

        System.out.println(t1.equals(t2));
        System.out.println(t1.equals(t4));  //同一个数据
    }
}
