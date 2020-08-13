package com.txr.spbbasic;


import com.txr.spbbasic.global.MonitorConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * created by xinrui.tian on 2020/8/11
 */
@Repository
@ConfigurationProperties(prefix = "monitor")
@Slf4j
public class ConfigHelper {


    Map<String, MonitorConfig> db;

    public Map<String, MonitorConfig> getDb() {
        return db;
    }

    public void setDb(Map<String, MonitorConfig> db) {
        this.db = db;
    }

    @PostConstruct
    public void init() {
        System.out.println(db);
    }

}
