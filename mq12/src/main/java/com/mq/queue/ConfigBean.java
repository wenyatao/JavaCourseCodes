package com.mq.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 1. MQ配置类
 */
@Component
@EnableJms
public class ConfigBean {

    @Value("${myqueue}")
    private String myQueue;

    @Value("${mytopic}")
    private String topicName;

    //队列
    @Bean
    public ActiveMQQueue queue(){
        return new ActiveMQQueue(myQueue);
    }

    //topic
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }

}
