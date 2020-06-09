package com.spring.redis.springredis.controller;

import com.spring.redis.springredis.service.RedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    RedisStringService redisStringService;

    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }

    @RequestMapping("set")
    public String setKey(){
        redisStringService.setString("key1","value1");
        return "success";
    }

    @RequestMapping("get")
    public String get(String key){
        return redisStringService.getString(key);
    }
}
