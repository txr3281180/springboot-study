package com.txr.spbbasic.model.strategy;

public class StrategyA implements Strategy {

    @Override
    public void doSomeThing(int a) {
        System.out.println("平方" + (a*a));
    }
}
