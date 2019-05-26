package com.txr.spbbasicstarter.condtional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinrui.tian on 2019/5/22
 */
public class OsSelectBean {

    private final Logger logger = LoggerFactory.getLogger(OsSelectBean.class);

    private boolean isWindows;


    public void setWindows(boolean windows) {
        isWindows = windows;
    }

    public void printlnOs() {
        if (isWindows) {
            logger.info("根据 @Conditional 装配 Windows Bean ==========================");
        } else {
            logger.info("根据 @Conditional 装配 Linux Bean ==========================");
        }
    }
}
