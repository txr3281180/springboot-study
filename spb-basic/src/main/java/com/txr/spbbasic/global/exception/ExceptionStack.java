package com.txr.spbbasic.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionStack {

    private static Logger logger = LoggerFactory.getLogger(ExceptionStack.class);

    //test
    public void logger () {
        try {
            throw new Exception("异常ing...............");
        } catch (Exception ex) {
            /*
                [ ERROR] [2019-01-10 14:03:37.111] com.txr.study.platform.BaseDemo [130] - load bond liquidity statistic data error: 异常ing...............
                Disconnected from the target VM, address: '127.0.0.1:52471', transport: 'socket'
             */

            logger.error("load bond liquidity statistic data error: {}", ex.getMessage());

            /*
                [ ERROR] [2019-01-10 14:03:53.189] com.txr.study.platform.BaseDemo [132] - load bond liquidity statistic data error1: java.lang.Exception: 异常ing...............
                    at com.txr.study.platform.BaseDemo.logger(BaseDemo.java:128)
                    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
                    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)......
            */
            logger.error("load bond liquidity statistic data error1: {}", ExceptionStack.getExceptionStack(ex));
        }
    }

    public static String getExceptionStack(Exception e) {
        String exceptionString = "";
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    exceptionString = sw.toString();
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return exceptionString;
    }
}
