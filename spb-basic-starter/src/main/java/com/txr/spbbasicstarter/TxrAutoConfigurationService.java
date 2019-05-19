package com.txr.spbbasicstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfiguration;

/**
 * Created by xinrui.tian on 2019/4/27.
 */
@Configuration
//@ConditionalOnBean(SchedulingConfiguration.class)  //如果有定时调度类则加载
@EnableConfigurationProperties(TxrAutoConfigurerProperties.class)
public class TxrAutoConfigurationService {

    @Autowired
    private TxrAutoConfigurerProperties txrAutoConfigurerProperties;

    @Bean
    public TxrAutoConfigurerProcessor txrAutoConfigurerProcessor() {
        TxrAutoConfigurerProcessor txrAutoConfigurerProcessor = new TxrAutoConfigurerProcessor();
        txrAutoConfigurerProcessor.setTxrAutoConfigurerProperties(txrAutoConfigurerProperties);
        return txrAutoConfigurerProcessor;
    }
}
