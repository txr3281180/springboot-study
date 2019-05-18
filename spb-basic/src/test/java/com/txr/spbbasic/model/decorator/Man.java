package com.txr.spbbasic.model.decorator;

public class Man implements Person {

    @Override
    public void eat() {
        System.out.println("男人在吃饭");
    }

    @Override
    public void show() {
        this.eat();
    }
}
