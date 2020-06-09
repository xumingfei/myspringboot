package com.spring.redis.springredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

@Service
public class RedisStringService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getString(String key) {
        System.out.println(stringRedisTemplate.opsForValue().get(key));
        return stringRedisTemplate.opsForValue().get(key);
    }

}
