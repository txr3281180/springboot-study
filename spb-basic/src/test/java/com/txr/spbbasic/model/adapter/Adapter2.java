package com.txr.spbbasic.model.adapter;

public class Adapter2 {
    //构造方式适配
    private Current current;

    public Adapter2(Current current) {
        this.current = current;
    }

    public void use18V() {
        System.out.println("使用适配器");
        this.current.use220V();
    }
}
