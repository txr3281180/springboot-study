package com.txr.spbmqqpid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xinrui.tian on 2019/5/18.
 */
@Component
public class QpidStart {

    @Autowired
    private QpidWarapper qpidWarapper;

    public void start() {
        qpidWarapper.initConnection();
        qpidWarapper.startQpid();
    }
}
