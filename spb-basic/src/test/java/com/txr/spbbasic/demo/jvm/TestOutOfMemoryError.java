package com.txr.spbbasic.demo.jvm;

import java.util.Random;

public class TestOutOfMemoryError {

    /*java.lang.OutOfMemoryError: Java heap space*/
    //堆内存溢出
    public static void main(String[] args) {
        String str = "txr" ;
        while(true)
        {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }

    }
}
