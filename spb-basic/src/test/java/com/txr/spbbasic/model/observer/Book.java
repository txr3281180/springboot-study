package com.txr.spbbasic.model.observer;


import java.util.Observable;

public class Book extends Observable {
    /*被观察者*/
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setChanged();//设置对象发生改变
        this.notifyObservers();//唤醒观察者
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.setChanged();
        this.notifyObservers();
    }

    public Book() {
    }

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
