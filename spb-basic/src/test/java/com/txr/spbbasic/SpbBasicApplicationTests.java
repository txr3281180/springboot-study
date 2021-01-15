package com.txr.spbbasic;

import com.txr.spbbasicstarter.TxrAutoConfigurerProcessor;
import com.txr.spbbasicstarter.condtional.OsSelectBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbBasicApplicationTests {

    @Autowired
    private TxrAutoConfigurerProcessor txrAutoConfigurerProcessor;

    @Test
    public void testStarter() {
        String configIpAndPort = txrAutoConfigurerProcessor.getConfigIpAndPort();
        System.out.println(configIpAndPort);
    }


    @Autowired
    private OsSelectBean windowsBean;

    @Autowired
    private OsSelectBean linuxBean;

    //vm options  -Dos.name=linux
    @Test
    public void testOsBean() {
        OsSelectBean osSelectBean = windowsBean != null ? windowsBean:linuxBean;
        osSelectBean.printlnOs();
    }


    @Autowired
    AbstractEnvironment environment;

    @Test
    public void testEv() {

        String[] activeProfiles = environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            System.out.println(activeProfile);
        }

        String[] defaultProfiles = environment.getDefaultProfiles();
        for (String defaultProfile : defaultProfiles) {
            System.out.println(defaultProfile);
        }

        System.out.println("========================");
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        for (String s : systemEnvironment.keySet()) {
            System.out.println(s + "=======>" + systemEnvironment.get(s));
        }

        System.out.println("========================");
        Map<String, Object> systemProperties = environment.getSystemProperties();
        for (String s : systemProperties.keySet()) {
            System.out.println(s + "======>" + systemProperties.get(s));
        }
    }

}
