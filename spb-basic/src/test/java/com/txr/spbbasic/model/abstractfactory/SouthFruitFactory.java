package com.txr.spbbasic.model.abstractfactory;

public class SouthFruitFactory implements FruitFactory{
    @Override
    public Fruit getApple() {
        return new SouthApple();  //生产南方苹果
    }

    @Override
    public Fruit getBanana() {
        return new SouthBanana();   //生产南方香蕉
    }
}
