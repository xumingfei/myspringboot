package com.test.myrabbitmq.ps;

import com.rabbitmq.client.*;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Recv {
    private static final String QUEUE_NAME = "test_queue_fanout_email";
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //持久化
        boolean durable = false;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        channel.basicQos(1);
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel){
            //消息到达出发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf8");

                System.out.println("[1] Recv msg: "+msg);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    System.out.println("[1] done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };

        boolean autoAck = false; //自动应答改为false
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
