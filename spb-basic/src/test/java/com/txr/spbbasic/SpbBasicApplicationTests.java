package com.txr.spbbasic;

import com.txr.spbbasicstarter.TxrAutoConfigurerProcessor;
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

}
