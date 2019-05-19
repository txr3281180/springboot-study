package com.txr.spbbasicstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinrui.tian on 2019/4/27.
 */
public class TxrAutoConfigurerProcessor {
    private final Logger logger = LoggerFactory.getLogger(TxrAutoConfigurerProcessor.class);

    private String ip;
    private int port;

    public void setTxrAutoConfigurerProperties(TxrAutoConfigurerProperties txrAutoConfigurerProperties) {
        this.ip = txrAutoConfigurerProperties.getIp();
        this.port = Integer.valueOf(txrAutoConfigurerProperties.getPort());
        logger.info("txr auto config {}:{}", ip, port);
    }

    public String getConfigIpAndPort() {
        return ip + ":" + port;
    }
}
