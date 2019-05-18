package com.txr.spbmqqpid.demo;

import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.AMQConnection;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;


/**
 * Created by xinrui.tian on 2019/3/31.
 */
public class Receive {
        public static void main(String[] args) throws Exception {
        //建立连接
        Connection connection = new AMQConnection("amqp://guest:guest@test/?failover='singlebroker'&brokerlist='tcp://localhost:5672?tcp_nodelay='true'&connecttimeout='2000''");
        connection.start();
        //创建一个会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //获取指定队列
        Destination queue = new AMQAnyDestination("qb.bondquote.req.direct;{create:always,delete:always,node:{type:queue,x-declare:{type:direct,auto-delete:True}}}");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //点对点模式，只接收一次
        //MapMessage m = (MapMessage)consumer.receive();
        //System.out.println(m);
        //监听模式，
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message);
            }
        });
        //线程阻塞
        System.in.read();
        System.out.println("关闭连接");
        connection.close();
    }
}
