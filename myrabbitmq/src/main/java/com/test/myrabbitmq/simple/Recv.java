package com.test.myrabbitmq.simple;

import com.rabbitmq.client.*;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("new api recv: "+msg);
            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
