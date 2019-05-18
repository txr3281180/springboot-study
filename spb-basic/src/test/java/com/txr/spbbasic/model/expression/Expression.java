package com.txr.spbbasic.model.expression;


/*
 * 抽象解释器
 */
public abstract class Expression {
    abstract void interpret(Context context);
}
