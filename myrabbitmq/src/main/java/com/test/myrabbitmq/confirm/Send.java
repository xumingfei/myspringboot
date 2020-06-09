package com.test.myrabbitmq.confirm;

import com.rabbitmq.client.*;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "test_queue_confirm1";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //开启confirm模式
        channel.confirmSelect();
        String msg = "hello,confirm message";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

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

//        channel.basicConsume(QUEUE_NAME,)
    }
}
