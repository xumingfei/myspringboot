package com.spring.redis.springredis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/5 8:28
 */
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(pattern);
        System.out.println(body);
        System.out.println(topic);
    }
}
