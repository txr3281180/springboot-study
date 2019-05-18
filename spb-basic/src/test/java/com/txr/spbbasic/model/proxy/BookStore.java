package com.txr.spbbasic.model.proxy;

public class BookStore implements BookFactory{

    //书店买书
    @Override
    public void sailBook() {
        System.out.println("出售 《java 编程思想》");
    }

}
