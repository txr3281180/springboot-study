package com.txr.spbbasic.springboot;


import com.txr.spbbasic.global.utils.NumberTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xinrui.tian on 2018/11/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void testNumberTools () {
        String formatterStr = NumberTools.formatter("2.123", 2, "亿");
        System.out.println(formatterStr);

        System.out.println(NumberTools.precisionTwoToFour("2.1000"));
        System.out.println(NumberTools.precisionZeroToFour("2.1000"));
        System.out.println(NumberTools.precisionZeroToTwo("2.1000"));
    }
}
