package com.txr.spbbasic.model.decorator;

public class MainClass {

    public static void main(String[] args) {
        Person p = new Man();
        p.show();

        System.out.println("---------------------");

        Person p2 = new RunPersonDecorator(p);
        p2.show();

        System.out.println("---------------------");

        Person p3 = new SleepPersonDecorator(p);
        p3.show();

    }
}
