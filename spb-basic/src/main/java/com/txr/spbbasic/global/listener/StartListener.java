package com.txr.spbbasic.global.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * spring boot 启动时的生命周期
 * 需要在 配置文件中添加 context.listener.classes
 * <p>
 * (演示： 配置文件何时映射， Environment 何时映射)
 *
 */
//@Component
public class StartListener implements ApplicationListener {

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 在这里可以监听到Spring Boot的生命周期
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("=====================初始化环境变量===profiles:" + profiles + "===env:" + env + "=====================");

            ApplicationEnvironmentPreparedEvent appEvent = (ApplicationEnvironmentPreparedEvent) event;
            ConfigurableEnvironment environment = appEvent.getEnvironment();
            String[] profiles = environment.getActiveProfiles();
            for (String profile : profiles) {
                System.out.println("获取环境变量 ==>" + profile);  //获取环境变量
            }

        } else if (event instanceof ApplicationPreparedEvent) {

            System.out.println("=====================初始化完成===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println("ApplicationPreparedEvent getApplicationContext:" + ((ApplicationPreparedEvent) event).getApplicationContext());

        } else if (event instanceof ContextRefreshedEvent) { // 应用刷新 }

            System.out.println("=====================应用刷新===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ContextRefreshedEvent getApplicationContext:" + ((ContextRefreshedEvent) event).getApplicationContext());

        } else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成 }

            System.out.println("=====================应用已启动完成===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ApplicationReadyEvent getApplicationContext:" + ((ApplicationReadyEvent) event).getApplicationContext());

        } else if (event instanceof ContextStartedEvent) {

            System.out.println("=====================应用启动,需要在代码动态添加监听器才可捕获===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ContextStartedEvent getApplicationContext:" + ((ContextStartedEvent) event).getApplicationContext());

        } else if (event instanceof ContextStoppedEvent) {

            System.out.println("=====================应用停止===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ContextStoppedEvent getApplicationContext:" + ((ContextStoppedEvent) event).getApplicationContext());

        } else if (event instanceof ContextClosedEvent) {

            System.out.println("=====================应用关闭===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ContextClosedEvent  getApplicationContext :" + ((ContextClosedEvent) event).getApplicationContext());

        } else {

            System.out.println("=====================ELSE===profiles:" + profiles + "===env:" + env + "=====================");
            System.out.println(">>ApplicationEvent getTimestamp :" + event.getTimestamp());
        }
    }
}
