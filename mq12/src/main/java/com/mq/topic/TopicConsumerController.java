package com.mq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/*
 1. topic消费者控制器
 */
@RestController
public class TopicConsumerController {

    /*
     * 消费者接收消息
     */
    @JmsListener(destination="${mytopic}")
    public void readActiveQueue(String message) {
        System.out.println("接受到：" + message);
    }

    @JmsListener(destination="${mytopic}")
    public void readActiveQueue1(String message) {
        System.out.println("接受到：" + message);
    }
}
