package com.txr.spbbasic.juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 一、i++ 的原子性问题：i++ 的操作实际上分为三个步骤“读-改-写”
 * 		  int i = 10;
 * 		  i = i++; //10
 *
 * 		  int temp = i;
 * 		  i = i + 1;
 * 		  i = temp;
 *
 * 二、原子变量：在 java.util.concurrent.atomic 包下提供了一些原子变量。
 * 		1. volatile_ 保证内存可见性
 * 		2. CAS（Compare-And-Swap） 算法保证数据变量的原子性
 * 			CAS 算法是硬件对于并发操作的支持
 * 			CAS 包含了三个操作数：
 * 			①内存值  V
 * 			②预估值  A
 * 			③更新值  B
 * 			当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
 */
public class Test2Atomic {

    //x = i++，先赋值，在++；  x = ++i 先++，在赋值

    //一个线程可以在主存中读改写，一个线程也可在主存中读改写
    //private volatile int serialNumber = 0;
    //public int getSerialNumber(){ return serialNumber++;}

    private AtomicInteger serialNumber = new AtomicInteger(0);
    public int getSerialNumber(){ return serialNumber.getAndIncrement(); }

    //必须在main方法中进行
    public static void main(String[] args) {
        Test2Atomic t = new Test2Atomic();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":" + t.getSerialNumber());
            }, "t" + i).start();
        }
    }

    // 在 main 方法中开启线程, 如果main方法结束,子线程不会结束继续运行
    // 在 @Test 方法中开启线程, 如果 @Test方法结束, 子线程也会结束
    @Test
    public void testAtomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("==========================="); //不会打印
            }, "t" + i).start();
        }
    }

}
