package com.txr.spbbasic.global.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by xinrui.tian on 2019/4/27.
 *
 *  配置在META-INF/spring.factories
 *
 */
/*带泛型 ApplicationContextInitializer<C extends ConfigurableApplicationContext>*/
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer...initialize" + applicationContext);
    }
}
