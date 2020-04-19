package com.txr.spbmqrabbit.service;

import com.txr.spbmqrabbit.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * created by xinrui.tian on 2020/4/19
 */
@Service
public class RabbitMQListenerTest {


    /**
     * queues  数组，可以监听多个
     * @param book
     */
    @RabbitListener(queues = {"test.direct.queue"})
    public void receive(Book book) {
        System.out.println("收到消息"  + book);
    }


    @RabbitListener(queues = {"eform.table"})
    public void receive(Message message) {
        System.out.println("收到消息 ===========");
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }


}
