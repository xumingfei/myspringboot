package com.spring.redis.springredis;

import com.spring.redis.springredis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/4 10:22
 */
public class RedisTestMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
        redisTemplate.opsForValue().set("name", "张三");
        redisTemplate.opsForHash().put("hash2","field2","hvalue2");
    }
}
