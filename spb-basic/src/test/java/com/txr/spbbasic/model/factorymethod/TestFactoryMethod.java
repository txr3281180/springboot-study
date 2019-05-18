package com.txr.spbbasic.model.factorymethod;


public class TestFactoryMethod {

    public static void main(String[] args) {

        //苹果工厂
        FruitFactory ff = new AppleFactory();  //苹果工厂
        Fruit apple = ff.getFruit();    //生产苹果
        System.out.println(apple.get());

        //香蕉工厂
        FruitFactory ff2 = new BananaFactory();
        Fruit banana = ff2.getFruit();
        System.out.println(banana.get());

    }

}
