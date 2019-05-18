package com.txr.spbbasic.model.decorator;

public class SleepPersonDecorator extends PersonDecorator{


    public SleepPersonDecorator(Person p) {
        super(p);
    }

    @Override
    public void show() {
        this.getP().show();
        this.Sleep();
    }

    public void Sleep(){
        System.out.println("睡觉的人");
    }

    @Override
    public void eat() {

    }
}
