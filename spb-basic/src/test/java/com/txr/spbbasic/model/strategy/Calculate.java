package com.txr.spbbasic.model.strategy;

public class Calculate {

    private Strategy s;

    public Calculate(Strategy s){
        this.s = s;
    }

    public void doSomething(int num){
        this.s.doSomeThing(num);
    }
}
