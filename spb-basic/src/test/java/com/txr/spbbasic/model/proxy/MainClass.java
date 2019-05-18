package com.txr.spbbasic.model.proxy;

public class MainClass {

    public static void main(String[] args) {
        //1.创建被代理类的对象
        BookStore bookStore = new BookStore();

        //2.创建一个实现了InvocationHandler接口的代理对象
        BookStoreProxy bookStoreProxy = new BookStoreProxy();

        //3.调用blink方法，返回代理对象（实现了 和被代理对象相同的接口）
        Object obj = bookStoreProxy.blink(bookStore);

        BookFactory bf = (BookFactory)obj;  //此时的bf就是代理类的对象

        bf.sailBook();//转到InvocationHandler接口的实现类的invoke()方法的调用
    }
}
