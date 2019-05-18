package com.txr.spbbasic.model.strategy;

public class StrategyB implements Strategy {

    @Override
    public void doSomeThing(int a) {
        System.out.println("相反数" + -(a));
    }
}
