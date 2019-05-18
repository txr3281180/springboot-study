package com.txr.spbbasic.model.singleton;

public class Person {

    //饿汉式
    private static Person p = new Person();

    private Person(){}

    public static Person getInstance(){
        return p;
    }
}
