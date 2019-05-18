package com.txr.spbbasic.model.decorator;

public class RunPersonDecorator extends PersonDecorator {

    public RunPersonDecorator(Person p){
        super(p);
    }

    @Override
    public void show() {
        this.getP().show();
        this.run();
    }


    public void run(){
        System.out.println("这个人在奔跑");
    }

    @Override
    public void eat() {

    }
}
