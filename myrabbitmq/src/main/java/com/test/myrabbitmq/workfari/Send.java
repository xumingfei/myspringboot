package com.test.myrabbitmq.workfari;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 公平分发
 */
public class Send {
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /**
         * 每个消费者 发送确认消息之前,消息队列不发送下一个消息,一次只处理一个消息
         * 限制同一个消费者不得超过一条消息
         */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);


        for (int i = 0; i < 50; i++) {
            String msg = "hello "+i;
            System.out.println("[WQ] send"+msg);
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            try {
                TimeUnit.MILLISECONDS.sleep(i*20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        channel.close();
        connection.close();
    }
}
