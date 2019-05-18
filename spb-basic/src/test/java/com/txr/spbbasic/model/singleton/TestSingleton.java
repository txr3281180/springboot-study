package com.txr.spbbasic.model.singleton;



public class TestSingleton {

    public static void main(String[] args) {
        Person p1 = Person.getInstance();
        Person p2 = Person.getInstance();
        System.out.println(p1.equals(p2));

        Animal a1 = Animal.getInstance();
        Animal a2 = Animal.getInstance();
        System.out.println(a1.equals(a2));

        System.out.println(Number.ONE.equals(Number.ONE));
    }
}
