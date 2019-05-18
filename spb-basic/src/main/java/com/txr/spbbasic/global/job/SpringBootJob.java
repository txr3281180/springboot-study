package com.txr.spbbasic.global.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by xinrui.tian on 2018/11/14.
 */
@Configuration
@EnableScheduling
public class SpringBootJob {

    private int count = 0;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 每xx中第xx执行一次
     */
    @Scheduled(cron = "* 30 * * * *")
    public void timedTaskCron() {
        logger.info(" >>>>>>>>>>>>>>>>>>>> SpringBoot 定时任务调度执行 cron <<<<<<<<<<<<<<<<<<<< ");
    }

    /**
     * 每xx执行一次
     */
    @Scheduled(fixedDelay = 60000)
    public void timedTaskDelay() {
        logger.info("SpringBoot 延迟调用执行 fixedDelay -->" + count++);
    }
}
