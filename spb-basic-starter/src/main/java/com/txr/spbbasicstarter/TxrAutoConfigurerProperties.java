package com.txr.spbbasicstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by xinrui.tian on 2019/4/27.
 */

@Component
@ConfigurationProperties(prefix = "txr.auto.config")
public class TxrAutoConfigurerProperties {

    private String ip = "localhost";
    private String port = "8080";

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
