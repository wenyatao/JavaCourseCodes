package com.cache.cache11.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@EnableScheduling //开启定时器功能
@Component
@RequiredArgsConstructor
public class MessageSender {

    //处理对象String换成
    private final RedisTemplate<String, String> redisTemplate;


    @Scheduled(fixedRate = 5000) //间隔5s 通过StringRedisTemplate对象向redis消息队列chat频道发布消息
    public void sendMessage(){
        redisTemplate.convertAndSend("order", "order "+ new Date());
    }
}
