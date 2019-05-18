package com.txr.spbbasic.model.prototype;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Student s1 = new Student();
        List<String> f = new ArrayList<>();
        f.add("aa");
        f.add("bb");
        s1.setName("AA");
        s1.setFriend(f);

        Student s2 = s1;   //引用赋值
        s2.setName("BB");  //修改后影响 s1 的结果
        s2.getFriend().add("cc");

        Student s3 = s1.clone();
        s3.setName("CC");
        s3.getFriend().add("dd");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
