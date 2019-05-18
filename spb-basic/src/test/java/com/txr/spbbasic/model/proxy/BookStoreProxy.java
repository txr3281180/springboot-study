package com.txr.spbbasic.model.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookStoreProxy implements InvocationHandler{

    Object obj;//可以看成是实现了接口的被代理类的对象的声明

    //此方法的作用：
    public Object blink(Object obj){
        //①给被代理类实例化
        this.obj = obj;
        //②返回一个代理类的对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), this);
    }

    //此方法的作用：当通过代理类的对象发起对重写方法的调用时，都会转换为对如下invoke方法的调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        methodBefore();

        Object returnVal = method.invoke(obj, args);

        methodAfter();

        return returnVal;
    }


    /* 切面方法 */
    private void methodBefore(){
        System.out.println("买书活动---------->打折");
    }

    private void methodAfter(){
        System.out.println("售后服务---------->送代金券");
    }
}
