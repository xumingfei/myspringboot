package com.test.myrabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BatchSend {
    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.confirmSelect();
        String msg = "send message batch";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        try {
            if(!channel.waitForConfirms()){
                System.out.println("message send failed");
            }else {
                System.out.println("message send ok");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            channel.close();
            connection.close();
        }
    }
}
