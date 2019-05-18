package com.txr.spbbasic.model.singleton;

public class Animal {
    //懒汉式
    private volatile static Animal a = null;

    private Animal(){ }

    public static Animal getInstance(){
        if(a == null){
            synchronized (Object.class){
                if(a == null){
                    a = new Animal();
                }
            }
        }
        return a;
    }

    //这种写法没有上面的写法效率高，每次对象进来会直接判断同步锁
//    public static synchronized Animal getInstance2(){
//        if(a == null){
//            a = new Animal();
//        }
//
//        return a;
//    }

}
