package com.txr.spbbasic.model.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer {
    /*观察者*/
    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof Book){
            Book b = (Book) o;
            System.out.println(b.getName());
            System.out.println(b.getPrice());
        }

        System.out.println("对象发生改变");
    }
}
