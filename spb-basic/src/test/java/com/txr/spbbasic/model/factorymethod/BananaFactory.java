package com.txr.spbbasic.model.factorymethod;



public class BananaFactory implements FruitFactory {

    //香蕉场 负责生产香蕉
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
