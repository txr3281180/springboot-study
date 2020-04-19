package com.txr.spbmqrabbit;

import com.txr.spbmqrabbit.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpbMqRabbitApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
        // new FanoutExchange("");
        // new TopicExchange("");

        // 创建 exchange
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        // 创建队列
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        // 创建邦定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE, "amqpadmin.exchange",
                "amqp.haha", null));
        // 删除操作 ....
    }

    /* 发送 将数据以json的方式发送，需配置MessageConverter*/
    @Test
    void contextLoads() {
        // Message 需要自己构造一个；定义消息体内容和消息头
        //rabbitTemplate.send(exchange, routKey, message);

        //object默认当成消息体， 只需要传入发送的对象，自动序列化发送给rabbitmq
        //  rabbitTemplate.convertAndSend(exchange, routKey, object);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "bond");
        map.put("body", "content");
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("canal.table.notify", "eform.table", map);
    }

    @Test
    void sendObj() {
        Book book = new Book();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("test.direct", "test.direct.queue", book);
    }


    /* 接收 */
    @Test
    void receive() {
        String queueName = "eform.table";
        //String queueName = "test.direct.queue";

        Object o = rabbitTemplate.receiveAndConvert(queueName);
        System.out.println(o.getClass());
        System.out.println(o);
    }
}
