package com.txr.spbbasic.model.strategy;

public class MainClass {

    public static void main(String[] args) {
        int num = 3;

        Strategy s1 = new StrategyA();  //策略A
        Strategy s2 = new StrategyB();  //策略B

        Calculate c1 = new Calculate(s1);
        c1.doSomething(num);

        Calculate c2 = new Calculate(s2);
        c2.doSomething(num);
    }
}
