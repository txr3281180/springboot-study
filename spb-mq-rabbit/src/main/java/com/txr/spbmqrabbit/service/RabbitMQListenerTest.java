package com.txr.spbmqrabbit.service;

import com.txr.spbmqrabbit.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * created by xinrui.tian on 2020/4/19
 */
@Service
public class RabbitMQListenerTest {


//    /**
//     * queues  数组，可以监听多个
//     * @param book
//     */
//    @RabbitListener(queues = {"test.direct.queue"})
//    public void receive(Book book) {
//        System.out.println("收到消息"  + book);
//    }


//    @RabbitListener(queues = {"eform.table"})
//    public void receive(Message message) {
//        System.out.println("收到消息 ===========");
//        System.out.println(message.getBody());
//        System.out.println(message.getMessageProperties());
//    }



    @RabbitListener(queues = {"d111.table.change.topic"})
    public void receiveD111(Message message) throws UnsupportedEncodingException {
        System.out.println("d111收到消息 ###########");
        byte[] body = message.getBody();
        if(body != null) {
            System.out.println(message.getMessageProperties());
            System.out.println(new String(body,"UTF-8"));
        }
    }


    @RabbitListener(queues = {"d100.table.change.topic"})
    public void receiveD100(Message message) throws UnsupportedEncodingException {
        System.out.println("d100收到消息 ===========");
        byte[] body = message.getBody();
        if(body != null) {
            System.out.println(message.getMessageProperties());
            System.out.println(new String(body,"UTF-8"));
        }
    }


}
