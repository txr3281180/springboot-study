package com.txr.spbbasic.demo.jvm;

public class TestStackOverflowError {

    /*java.lang.StackOverflowError*/
    //占内存溢出
    public static void systemOut(){
        systemOut();  //递归调用
    }

    public static void main(String[] args) {
        systemOut();
    }
}
