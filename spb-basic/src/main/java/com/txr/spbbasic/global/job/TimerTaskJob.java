package com.txr.spbbasic.global.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xinrui.tian on 2018/11/14.
 */
//@Component
public class TimerTaskJob extends TimerTask {

    /*
          1、普通thread实现 ：是最常见的，创建一个thread，然后让它在while循环里一直运行着，
             通过sleep方法来达到定时任务的效果。
          2、TimerTask ：启动和去取消任务时可以控制，第一次执行任务时可以指定你想要的delay时间。
          3、ScheduledExecutorService实现 ：最理想的定时任务实现方式，相比于Timer的单线程，
             它是通过线程池的方式来执行任务的，可以很灵活的去设定第一次执行任务delay时间，
             提供了良好的约定，以便设定执行的时间间隔等。
     */

    /*
        《Java并发编程实战》一书提到的用ExecutorService取代Java Timer有几个理由，我认为其中最重要的理由是：
            如果TimerTask抛出未检查的异常，Timer将会产生无法预料的行为。
            Timer线程并不捕获异常，所以 TimerTask抛出的未检查的异常会终止timer线程。
            这种情况下，Timer也不会再重新恢复线程的执行了;它错误的认为整个Timer都被取消了。
            此时，已经被安排但尚未执行的TimerTask永远不会再执行了，新的任务也不能被调度了。
    */


    private Logger logger = LoggerFactory.getLogger(getClass());

    TimerTaskJob () {
        Timer timer = new Timer();
        timer.schedule(this, 1000,1000);
    }

    /*
        public void schedule(TimerTask task,long delay)
        public void schedule(TimerTask task,long delay,long period)
        public void schedule(TimerTask task,Date time)
        public void schedule(TimerTask task,Date firstTime,long period)
     */

    @Override
    public void run() {
        logger.info(">>>>>>>>>Java  TimerTask 定时任务");
    }
}
