package com.txr.spbmqqpid.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.qpid.QpidException;
import org.apache.qpid.client.AMQAnyDestination;
import org.apache.qpid.client.message.JMSMapMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import txr.common.exception.ExceptionStack;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * Created by xinrui.tian on 2019/5/18.
 */
@Slf4j
@Component
public class QpidWarapper {

    @Value("${qpid.url}")
    private String pqidUrl;

    @Value("${direct.protocol}")
    private String directProtocol;

    @Value("${fanout.protocol}")
    private String fanoutProtocol;

    @Value("${qpid.direct.queue}")
    private String directQueue;

    @Value("${qpid.fanout.queue}")
    private String fanoutQueue;

    private final String replyTo = "qpid.reply.to.msg";

    private Connection qpidConnection;
    private Session qpidSession;
    private MapMessage mapMessage;

    private Destination fanoutDestination;

    private MessageProducer messageProducer;
    private MessageConsumer fanoutConsumer;
    private MessageConsumer directConsumer;

    private ExecutorCompletionService<Boolean> executorCompletionService = new ExecutorCompletionService<Boolean>(Executors.newCachedThreadPool());

    public void initConnection() {
        while (!connectToQpid()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(ExceptionStack.getExceptionStack(e));
            }
            log.info("启动qpid连接！");
        }
    }

    public void startQpid() {
        try {
            log.info("start out put qpid");
            qpidConnection.start();
        } catch (JMSException e) {
            log.error(ExceptionStack.getExceptionStack(e));
        }
    }

    /** direct发送， 消费者收到后， fanout发送 */
    public void send(String msgId, Map<String, String> map) {
        try {
            mapMessage.setJMSCorrelationID(msgId);  //meg id
            mapMessage.setJMSReplyTo(fanoutDestination);  //转发目的地
            mapMessage.setObject("Body", map);  //内容
            messageProducer.send(mapMessage);  //发送
            mapMessage.clearBody();
            System.out.println();
            System.out.println("-----------------------------------------------");
            System.out.println("==>发送消息: " + map);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void send(MessageProducer messageProducer, Map<String, Object> megMap) {
        try {
            mapMessage.setJMSCorrelationID("89757");  //meg id

            megMap.put("广播通知", "hello world");
            mapMessage.setObject("Body", megMap);  //内容
            messageProducer.send(mapMessage);  //发送
            mapMessage.clearBody();
            System.out.println("==>转发消息: " + megMap);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private boolean connectToQpid() {
        executorCompletionService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    //connection
                    qpidConnection = new org.apache.qpid.client.AMQConnection(pqidUrl);
                    qpidConnection.setExceptionListener(e -> {
                        log.error("连接异常 : {}", ExceptionStack.getExceptionStack(e));
                        resetConnection();
                    });

                    //session
                    qpidSession = qpidConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    mapMessage = qpidSession.createMapMessage();

                    //fanout destination(目的地)
                    fanoutDestination = new AMQAnyDestination(fanoutQueue + fanoutProtocol);

                    //fanout consumer
                    fanoutConsumer = qpidSession.createConsumer(fanoutDestination);
                    //监听 fanout msg
                    fanoutConsumer.setMessageListener(new MessageListener() {
                        @Override
                        public void onMessage(Message message) {
                            JMSMapMessage mapMessage = (JMSMapMessage) message;
                            try {
                                System.out.println("接收fanout消息 : " + mapMessage.toBodyString());
                            } catch (JMSException e) {
                                log.error(ExceptionStack.getExceptionStack(e));
                            }
                        }
                    });

                    //direct consumer
                    Destination directDestination = new AMQAnyDestination(directQueue + directProtocol);
                    directConsumer = qpidSession.createConsumer(directDestination);
                    //监听 fanout msg
                    directConsumer.setMessageListener(new MessageListener() {
                        @Override
                        public void onMessage(Message message) {
                            JMSMapMessage mapMessage = (JMSMapMessage) message;
                            try {
                                String jmsCorrelationID = mapMessage.getJMSCorrelationID();
                                Map<String, Object> megMap = (Map<String, Object>) mapMessage.getObject("Body");
                                System.out.println("接收消息 [msgId:"+ jmsCorrelationID + ", body: " + megMap);

                                if ("1".equals(jmsCorrelationID)) {
                                    Destination jmsReplyTo = mapMessage.getJMSReplyTo();
                                    if (jmsReplyTo != null) {
                                        final MessageProducer messageProducer = qpidSession.createProducer(jmsReplyTo);
                                        messageProducer.setDeliveryDelay(DeliveryMode.PERSISTENT);
                                        send(messageProducer, megMap);
                                    }
                                } else {
                                    System.out.println("**************************************");
                                    System.out.println("**  ******     **   **     ******   **");
                                    System.out.println("**    **        ** **      **   **  **");
                                    System.out.println("**    **         ***       ******   **");
                                    System.out.println("**    **        ** **      ** **    **");
                                    System.out.println("**    **       **   **     **   **  **");
                                    System.out.println("**************************************");
                                }
                            } catch (JMSException e) {
                                log.error(ExceptionStack.getExceptionStack(e));
                            }
                        }
                    });

                    //producer
                    messageProducer = qpidSession.createProducer(directDestination);
                    messageProducer.setDeliveryDelay(DeliveryMode.NON_PERSISTENT);

                    return true;
                } catch (QpidException | URISyntaxException | JMSException e) {
                    log.error(ExceptionStack.getExceptionStack(e));
                    return false;
                }
            }
        });
        try {
            boolean r = executorCompletionService.take().get();
            log.info("连接qpid : {}", r);
            return r;
        } catch (InterruptedException e) {
            log.error(ExceptionStack.getExceptionStack(e));
            return false;
        } catch (ExecutionException e) {
            log.error(ExceptionStack.getExceptionStack(e));
            return false;
        }
    }

    private void resetConnection() {
        do {
            try {
                log.info("重新连接qpid");
                //关闭fanout消费者
                if (fanoutConsumer != null) {
                    fanoutConsumer.close();
                }
                fanoutConsumer = null;
                //关闭direct消费者
                if (directConsumer != null) {
                    directConsumer.close();
                }
                directConsumer = null;
                //关闭生产者
                if (messageProducer != null) {
                    messageProducer.close();
                }
                messageProducer = null;
                //关闭会话
                if (qpidSession != null) {
                    qpidSession.close();
                }
                qpidSession = null;
                //关闭连接
                if (qpidConnection != null) {
                    qpidConnection.close();
                }
                qpidConnection = null;
                Thread.sleep(3000);
            } catch (JMSException e) {
                log.error(ExceptionStack.getExceptionStack(e));
            } catch (InterruptedException e) {
                log.error(ExceptionStack.getExceptionStack(e));
            }
            log.info("启动qpid连接,成功！");
        } while (!connectToQpid());
        startQpid();
    }
}
