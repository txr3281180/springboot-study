package com.txr.spbbasic.model.decorator;

/**
 *  人的装饰着 (抽象类可以不实现接口方法)
 */
public abstract class PersonDecorator implements Person {

    private Person p;

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public PersonDecorator(Person p){
        this.p = p;
    }

    public abstract void show();

}
