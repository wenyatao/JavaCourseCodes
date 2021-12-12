package com.mq.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;

/*
 * topic消息生产者
 */
@RestController
public class TopicProducerController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    /*
     * 消息生产者
     */
    @RequestMapping("/topicSendMsg")
    public void sendmsg(String msg) {
        System.out.println("发送消息到MQ：" + msg);
        // 指定消息发送的目的地及内容
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }
}
