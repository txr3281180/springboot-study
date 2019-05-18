package com.txr.spbdatamybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;
import java.util.TimeZone;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages={"com.txr.spbdatamybatis.repository.mapper"})
public class SpbDataMybatisApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        SpringApplication.run(SpbDataMybatisApplication.class, args);
    }

}
