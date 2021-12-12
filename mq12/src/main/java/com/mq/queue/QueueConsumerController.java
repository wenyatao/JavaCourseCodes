package com.mq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/*
 1. 队列queue消费者控制器
 */
@RestController
public class QueueConsumerController {

    /*
     * 消费者接收消息
     */
    @JmsListener(destination="${myqueue}")
    public void readActiveQueue(String message) {
        System.out.println("接受到：" + message);
    }
}
