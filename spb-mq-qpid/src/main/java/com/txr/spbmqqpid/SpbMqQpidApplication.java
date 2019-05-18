package com.txr.spbmqqpid;

import com.txr.spbmqqpid.service.QpidStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpbMqQpidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbMqQpidApplication.class, args).getBean(QpidStart.class).start();
    }

}
