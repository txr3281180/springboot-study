package com.txr.spbbasic.model.abstractfactory;

public class NorthFruitFactory implements FruitFactory{

    @Override
    public Fruit getApple() {
        return new NorthApple();
    }

    @Override
    public Fruit getBanana() {
        return new NorthBanana();
    }
}
