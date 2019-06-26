package com.txr.spbbasic.callback;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class Teacher implements CallBack {

    @Override
    public void event() {
        System.out.println("知道了");
    }

    @Override
    public void event(int num) {
        System.out.println("计算结果： "  + num);
    }


}
