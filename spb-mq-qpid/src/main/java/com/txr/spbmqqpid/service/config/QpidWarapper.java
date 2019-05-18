package com.txr.spbmqqpid.service.config;

import javax.jms.Connection;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by xinrui.tian on 2019/5/18.
 */
public class QpidWarapper {

    private Connection outputConnection;
    private Session outputSession;
    private MapMessage mapMessage;
    private MessageProducer outputMessageProducer;
    private MessageConsumer outputMessageConsumer;



}
