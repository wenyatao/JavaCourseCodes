package com.cache.cache11.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    private  Redisson redisson;

    @Resource
    private RedisTemplate <String, Integer> redisTemplate;


    /**
     * redisson模拟减库存
     * @return
     */
    @GetMapping("deductStock")
    public String deductStock(){
        String key = "deductStock";
        RLock redisId=redisson.getLock(key);//拿到锁对象
        try{
            redisId.lock(5, TimeUnit.SECONDS);
            int stock = redisTemplate.opsForValue().get("stock");
            boolean b = stock > 0;
            if(b){
                int realStock=stock-1;
                redisTemplate.opsForValue().set("stock",realStock);
            }else{
                System.out.println("扣减失败，库存不足");
            }

        }finally {
            redisId.unlock();
        }
        return "end";
    }

    @PostConstruct
    public void setStock(){
        redisTemplate.opsForValue().set("stock",100);
    }






}
