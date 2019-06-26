package com.txr.spbbasic.callback;

import org.junit.Test;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class TestCallBack {

    @Test
    public void testCallBack() {
        Student stu = new Student();
        stu.register(new Teacher(), stu);
        stu.doHomeWork();

        System.out.println("==========================");

        Student stu2 = new Student();
        stu.register(new Teacher(), stu2);
        stu.calculate(3, 2);
    }
}
