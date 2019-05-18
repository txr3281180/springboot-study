package com.txr.spbbasic.model.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TeacherSchool {

    private Map<String, Teacher> teacherSchool;

    public TeacherSchool() {
        this.teacherSchool = new HashMap<>();
    }

    public Teacher getTeacher(String str){
        Teacher t = teacherSchool.get(str);
        if(t == null) {  //如果享元数据为空
            t = new Teacher();      //创建一个
            buildTeacher(t, str);   //模拟数据获取对应的数据
            teacherSchool.put(str, t);  //存入享元数据集合中
        }
        return t;
    }

    private void buildTeacher(Teacher t, String str){
        t.setName(str);
        t.setSex("男");
    }
}
