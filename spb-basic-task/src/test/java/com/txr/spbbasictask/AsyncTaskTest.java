package com.txr.spbbasictask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest  //指定启动类
public class AsyncTaskTest {

    @Autowired
    private AsyncTask at;

    /**
     * 测试异步
     */
    @Test
    public void testAsyncTask(){

        System.out.println("》》》》》》》》》》》》runner 1");
        at.asyncTask1();
        System.out.println("》》》》》》》》》》》》runner 2");

    }


}
