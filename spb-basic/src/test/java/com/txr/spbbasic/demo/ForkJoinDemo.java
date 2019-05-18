package com.txr.spbbasic.demo;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by xinrui.tian on 2018/9/27.
 */
public class ForkJoinDemo{

    /**
     * 普通计算求和
     */
    @Test
    public void test(){
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i <= 2000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("普通计算耗费时间：" + (end - start) + "毫秒");
    }

    /**
     * java8 并行流求和
     */
    @Test
    public void testJava8 (){
        long start = System.currentTimeMillis();
        //rangeClosed ：返回一个有序 LongStream从 startInclusive（含）至 endInclusive（含）的 1增量步。
        long sum = LongStream.rangeClosed(0, 2000000000L)
                .parallel()  //并行流
                .reduce(0, Long::sum);
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("并行流耗费时间：" + (end - start) + "毫秒");
    }

    /**
     * fork join 计算求和
     */
    @Test
    public void testForkJoin (){
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 2000000000L);
        Long sum = forkJoinPool.invoke(task);
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("ForkJoin耗费时间：" + (end - start) + "毫秒");

    }
}

class ForkJoinCalculate extends RecursiveTask<Long> {

    private long startIndex;  //起始值
    private long endIndex;  //结束值
    public static final long THRESHOLD = 10000L;  //临界值

    //构造器
    public ForkJoinCalculate() {}
    public ForkJoinCalculate(long startIndex, long endIndex) { this.startIndex = startIndex;this.endIndex = endIndex; }

    @Override
    protected Long compute() {
        long length = endIndex - startIndex;

        if (length <= THRESHOLD) {   //如果到达临界值，开始求和
            long sum = 0;
            for (long i = startIndex; i <= endIndex; i++) {
                sum += i;
            }
            return sum;
        } else {
            //没有达到临界值继续拆分
            long middle = (startIndex + endIndex) / 2;  //中间值
            //递归调用
            ForkJoinCalculate left = new ForkJoinCalculate(startIndex, middle);
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, endIndex);
            right.fork();

            return left.join() + right.join();
        }
    }
}