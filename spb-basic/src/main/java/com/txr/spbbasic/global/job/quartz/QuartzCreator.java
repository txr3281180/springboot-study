package com.txr.spbbasic.global.job.quartz;


import com.txr.spbbasic.global.utils.IdUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzCreator {

    private Scheduler scheduler;

    private Scheduler getScheduler() throws SchedulerException {
        if (scheduler == null) {
            scheduler = new StdSchedulerFactory().getScheduler();
        }
        return scheduler;
    }

    /**
     * 创建定时任务
     */
    public void createTask (String id, String cronExpression, Class<? extends Job> jobClazz) throws SchedulerException {
        Scheduler scheduler = this.getScheduler();
        //防止重复id
        JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(id));
        if (jobDetail != null) {
            scheduler.deleteJob(JobKey.jobKey(id));
        }
        //触发器
        jobDetail = JobBuilder.newJob(jobClazz).withIdentity(id).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(IdUtils.UUID()).withSchedule(scheduleBuilder).build();
        //调度任务并启动
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
