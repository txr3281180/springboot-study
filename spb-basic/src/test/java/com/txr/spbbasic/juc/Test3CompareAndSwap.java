package com.txr.spbbasic.juc;

/*
*   模拟CAS算法
*/
public class Test3CompareAndSwap {

    public static void main(String[] args) {
        final Test3CompareAndSwap cas = new Test3CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();  //获取内存值
                    //System.out.println(expectedValue);
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
                    System.out.println(b);
                }
            }).start();
        }

    }

    private int value; //内存值

    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较或设置
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;

        if(oldValue == expectedValue){  //如果预期值与旧值一样， 设置新值
            this.value = newValue;
        }

        return oldValue;  //返回旧值
    }

    //是否成功
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }

}
