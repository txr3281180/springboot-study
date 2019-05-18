package com.txr.spbbasic.model.factory;

public class TestFactory {


    public static void main(String[] args) throws Exception {
        FruitFactory fruit = new FruitFactory();

        System.out.println(fruit.getApple().get());
        System.out.println(fruit.getBanana().get());


        String apple = fruit.getFruit("apple").get();
        System.out.println(apple);


        Fruit banana = FruitFactory.getFruitForName("model.factory.Banana");
        System.out.println(banana.get());
    }
}
