package com.txr.spbbasic.model.adapter;


public class Adapter extends Current {
    //使用继承的方式适配
    public void use18V() {
        System.out.println("使用适配器");
        this.use220V();
    }
}
