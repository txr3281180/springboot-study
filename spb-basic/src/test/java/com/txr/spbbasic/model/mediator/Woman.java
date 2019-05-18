package com.txr.spbbasic.model.mediator;

public class Woman extends Person {

    public Woman(String name, int condition, Mediator mediator) {
        super(name, condition, mediator);
    }

    public void getPartner(Person person) {
        this.getMediator().setWoman(this);
        this.getMediator().getPartner(person);
    }

}
