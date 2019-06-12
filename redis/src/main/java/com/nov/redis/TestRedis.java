package com.nov.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestRedis {

    @Autowired
    private static RedisTemplate<String, String> redisTemplate; // inject the template as ListOperations


    public static void main(String[] args) {

        redisTemplate.opsForValue().set("key", "value");


    }

}
