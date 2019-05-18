package com.txr.spbbasic.model.factorymethod;

public class AppleFactory implements FruitFactory{

    //苹果场生产苹果
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
