package com.txr.spbbasic.model.bridge;


public abstract class Person {

    private Address address;

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract void show();
}
