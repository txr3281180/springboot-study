package com.txr.spbbasic.juc;

import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch ：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 */
public class Test5CountDownLatch {


    //计算50个线程，分别打印50000的总时间
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(50);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 50000; j++) {
                        if (j % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + j);   //打印 50000 以内的偶数
                        }
                    }
                } finally {
                    latch.countDown();
                }
            }, "t_" + i).start();
        }

        try {
            latch.await();  //计算耗费时间，需要等待子线程
        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间为：" + (end - start));
    }

}
