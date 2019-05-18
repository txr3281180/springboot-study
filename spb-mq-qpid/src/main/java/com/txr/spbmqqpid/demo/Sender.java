package com.txr.spbmqqpid.demo;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;


public class Sender {

    public static void main(String[] args) throws Exception
    {
        //建立amqp连接
        Connection connection =
                new AMQConnection("amqp://guest:guest@test/?failover='singlebroker'&brokerlist='tcp://localhost:5672?tcp_nodelay='true'&connecttimeout='2000''");
        //创建一个回话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //create: always 表示如果队列不存在 则创建
        Destination queue = new AMQAnyDestination("qb.bondquote.req.direct;{create:always,delete:always,node:{type:queue,x-declare:{type:direct,auto-delete:True}}}");
        //创建指定队列一个生产者
        MessageProducer producer = session.createProducer(queue);
        //创建消息
        MapMessage m = session.createMapMessage();
        m.setJMSCorrelationID("55617");
        m.setObject("Diko","QPID，消息发送！");
        //发送消息
        producer.send(m);
        //断开连接
        connection.close();
    }

}