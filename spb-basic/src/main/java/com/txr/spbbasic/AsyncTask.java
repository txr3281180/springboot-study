package com.txr.spbbasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsyncTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 异步任务
     * 测试类
     * src.test.java.commonutils.util.test.AsyncTaskTest.java
     */
    @Async
    public void asyncTask1() {
        logger.info(" AsyncTask  start");
        System.out.println("》》》》》》》》》》》》runner 3");
    }
}
