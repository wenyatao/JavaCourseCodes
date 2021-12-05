package com.cache.cache11.sub;

import org.springframework.stereotype.Service;



@Service
public class RedisReceiver {

    /**
     *  订单换成对象即可
     */
    public void receiveMessage(String message) {

        //此处可处理订单
        System.out.println("接收消息：" + message);
    }
}
