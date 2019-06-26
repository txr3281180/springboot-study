package com.txr.spbbasic.callback;


/**
 * Created by xinrui.tian on 2019/6/26
 */
public class Student {

    private CallBack callBack;

    public Student register(CallBack callBack, Student stu) {
        this.callBack = callBack;
        return stu;
    }

    public void doHomeWork() {
        System.out.println("作业做完了");
        callBack.event();
    }

    public void calculate(int a, int b) {
        System.out.println("计算： " + a + " + " + b);
        callBack.event(a + b);

    }
}
