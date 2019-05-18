package com.txr.spbbasic.model.abstractfactory;

public class TestFruitFactory {

    public static void main(String[] args) {

        FruitFactory ff = new NorthFruitFactory();  //创建北方工厂
        ff.getApple().get();    //生产北方苹果
        ff.getBanana().get();   //生产北方香蕉

        FruitFactory ff2 = new SouthFruitFactory();  //创建南方工厂
        ff2.getApple().get();   //生产南方苹果
        ff2.getBanana().get();   //生产南方香蕉
    }
}
