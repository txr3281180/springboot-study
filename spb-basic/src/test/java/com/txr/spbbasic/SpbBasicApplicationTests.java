package com.txr.spbbasic;

import com.txr.spbbasicstarter.TxrAutoConfigurerProcessor;
import com.txr.spbbasicstarter.condtional.OsSelectBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
