package com.txr.spbbasicstarter;

import com.txr.spbbasicstarter.condtional.LinuxConditional;
import com.txr.spbbasicstarter.condtional.OsSelectBean;
import com.txr.spbbasicstarter.condtional.WindowsConditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
    //@Lazy
    //@Scope  // singleton  | prototype | request | session
    @Bean("windowsBean")
    @Scope("prototype")
    @Conditional({WindowsConditional.class})
    public OsSelectBean windowsBean() {
        OsSelectBean osSelectBean = new OsSelectBean();
        osSelectBean.setWindows(true);
        return osSelectBean;
    }


    //-Dos.name=linux

    @Bean("linuxBean")
    @Scope("prototype")
    @Conditional({LinuxConditional.class})
    public OsSelectBean linuxBean() {
        OsSelectBean osSelectBean = new OsSelectBean();
        osSelectBean.setWindows(false);
        return osSelectBean;
    }


}
