package com.txr.spbbasic.model.observer;

public class MainClass {

    public static void main(String[] args) {
        Book b = new Book();  //被观察者
        MyObserver m1 = new MyObserver();

        //注册观察者
        b.addObserver(m1);

        System.out.println(b.countObservers());  //记录存在几个观察者
        b.setName("java编程思想");
        b.setPrice(78.99);

        b.deleteObserver(m1);   //移除观察者
        b.setName("Oracle");    //发生改变则不会被观察

    }
}
