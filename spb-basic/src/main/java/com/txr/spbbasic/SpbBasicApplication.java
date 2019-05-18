package com.txr.spbbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class SpbBasicApplication {

    public static void main(String[] args) {

        //CommandLineRunner 启动时执行某些方法
        //下面该方式启动后最先执行某些方法：  getBean : 从spring容器中获取该类  .method(): 执行要调用的方法
        //SpringApplication.run(SpbBasicApplication.class, args).getBean(XXXX.class).method();

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        SpringApplication.run(SpbBasicApplication.class, args);
    }

}
