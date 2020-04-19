package com.txr.spbmqrabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * rabbit mq 自动配置
 * 1. RabbitAutoConfiguration
 * 2. 有自动配置了连接工厂 ConnectionFactory
 * 3. RabbitProperties 封装了 RabbitMQ 的配置
 * 4. RabbitTemplate： 给RabbitMQ 发送和接受消息的
 * 5. AmqpAdmin: RabbitMQ 系统管理功能组件
 *          AmqpAdmin: 创建和删除  Queue, Exchange, Binding (示例： 单元测试)
 * 6. @EnableRabbit + @RabbitListener 监听消息队列的内容
 */
@EnableRabbit   // 开启基于注解的RabbitMQ模式
@SpringBootApplication
public class SpbMqRabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbMqRabbitApplication.class, args);
    }

}
