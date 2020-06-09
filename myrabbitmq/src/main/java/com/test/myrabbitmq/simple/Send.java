package com.test.myrabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 普通消息分发
 */
public class Send {

    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();

        //从连接中创建Channel
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "hello rabbitmq;";
        channel.basicPublish("", QUEUE_NAME,null,msg.getBytes());
        System.out.println("----send msg");
        channel.close();
        connection.close();

    }
}
