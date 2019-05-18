package com.txr.spbbasic.demo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xinrui.tian on 2018/9/26.
 */
public class DateTimeDemo {


    /**
     * 演示旧时间API线程安全问题
     *      可能创建成功
     *      可能抛异常 java.util.concurrent.ExecutionException: java.lang.NumberFormatException: empty String
     */
    @Test
    public void testDate() throws ExecutionException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Callable<Date> task = () -> sdf.parse("2016-10-01");  //创建线程

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();
    }

    //===========解决方式========================


    private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private final Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }


    @Test
    public void testDate1() throws ExecutionException, InterruptedException {

        Callable<Date> task = () -> convert("2016-10-01");  //创建线程

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();
    }

}
