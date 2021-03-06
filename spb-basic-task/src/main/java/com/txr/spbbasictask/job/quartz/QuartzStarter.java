package com.txr.spbbasictask.job.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import txr.common.string.IdUtils;

import javax.annotation.PostConstruct;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class QuartzStarter {

    /**
     *  启动的时候执行该方法，或者是使用ApplicationListener，在启动的时候执行该方法
     *  具体使用见：http://blog.csdn.net/liuchuanhong1/article/details/77568187
     */
    @PostConstruct
    //@Scheduled(cron="${application.quartz.job}")
    public void schedule() throws SchedulerException {
        /*可配置*/
        String cronExpression1 = "0/5 * * * * ?"; //每五秒执行一次
        String cronExpression2 = "0/10 * * * * ?"; //每十秒执行一次

        quartzCreator.createTask(IdUtils.UUID(),cronExpression1,QuartzJob1.class);
        quartzCreator.createTask(IdUtils.UUID(),cronExpression2,QuartzJob2.class);
    }

    @Autowired
    private QuartzCreator quartzCreator;
}
