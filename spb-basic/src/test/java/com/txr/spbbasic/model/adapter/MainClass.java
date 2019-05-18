package com.txr.spbbasic.model.adapter;

public class MainClass {
    public static void main(String[] args) {
        //使用继承方式适配
        Adapter adapter = new Adapter();
        adapter.use18V();   //最终还是会使用 220v,在使用18v时进行了适配操作

        //使用构造方式适配，较为灵活
        Adapter2 adapter2 = new Adapter2(new Current());
        adapter2.use18V();

    }
}
