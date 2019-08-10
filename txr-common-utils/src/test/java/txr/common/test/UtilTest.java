package txr.common.test;


import org.junit.jupiter.api.Test;
import txr.common.exception.ExceptionStack;
import txr.common.string.NumberTools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xinrui.tian on 2019/5/18.
 */
public class UtilTest {

    @Test
    public void testExceptionStack() {
        try {
            throw new Exception("异常ing...............");
        } catch (Exception ex) {
            /*
                [ ERROR] [2019-01-10 14:03:37.111] com.txr.study.platform.BaseDemo [130] - load bond liquidity statistic data error: 异常ing...............
                Disconnected from the target VM, address: '127.0.0.1:52471', transport: 'socket'
             */
            System.out.println("load bond liquidity statistic data error:" + ex.getMessage());

            /*
                [ ERROR] [2019-01-10 14:03:53.189] com.txr.study.platform.BaseDemo [132] - load bond liquidity statistic data error1: java.lang.Exception: 异常ing...............
                    at com.txr.study.platform.BaseDemo.logger(BaseDemo.java:128)
                    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
                    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)......
            */
            System.out.println("load bond liquidity statistic data error1:" + ExceptionStack.getExceptionStack(ex));
        }
    }

    @Test
    public void testNumberTools () {
        String formatterStr = NumberTools.formatter("2.123", 2, "亿");
        System.out.println(formatterStr);

        System.out.println(NumberTools.precisionTwoToFour("2.1000"));
        System.out.println(NumberTools.precisionZeroToFour("2.1000"));
        System.out.println(NumberTools.precisionZeroToTwo("2.1000"));
    }

    @Test
    public void testDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(localDateTime);
        System.out.println(format);
    }

}
