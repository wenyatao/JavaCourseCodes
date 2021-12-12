package com.mq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/*
 * 队列消息生产者
 */
@RestController
public class QueueProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    /*
     * 消息生产者
     */
    @RequestMapping("/sendmsg")
    public void sendmsg(String msg) {
        System.out.println("发送消息到队列：" + msg);
        // 指定消息发送的目的地及内容
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

}
