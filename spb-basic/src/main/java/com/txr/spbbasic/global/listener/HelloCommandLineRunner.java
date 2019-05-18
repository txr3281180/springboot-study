package com.txr.spbbasic.global.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by xinrui.tian on 2019/4/27.
 *
 * 配置在IOC容器中
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("HelloCommandLineRunner..." + Arrays.asList(args));
    }
}
