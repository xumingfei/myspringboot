package com.test.myrabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "test_queue_fanout_email";
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
//        channel.exchangeDeclare("" , BuiltinExchangeType.TOPIC);
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);


        for (int i = 0; i < 50; i++) {
            String msg = "hello "+i;
            System.out.println("[WQ] send"+msg);
            channel.basicPublish(EXCHANGE_NAME,QUEUE_NAME,null,msg.getBytes());
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
