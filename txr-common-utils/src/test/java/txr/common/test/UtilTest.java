package txr.common.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import txr.common.exception.ExceptionStack;

/**
 * Created by xinrui.tian on 2019/5/18.
 */
@Slf4j
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
            log.error("load bond liquidity statistic data error: {}", ex.getMessage());

            /*
                [ ERROR] [2019-01-10 14:03:53.189] com.txr.study.platform.BaseDemo [132] - load bond liquidity statistic data error1: java.lang.Exception: 异常ing...............
                    at com.txr.study.platform.BaseDemo.logger(BaseDemo.java:128)
                    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
                    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)......
            */
            log.error("load bond liquidity statistic data error1: {}", ExceptionStack.getExceptionStack(ex));
        }
    }



}
