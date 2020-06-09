package com.test.myrabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.test.myrabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class Send2 {

    private static final String QUEUE_NAME = "test_queue_confirm3";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //开启confirm模式
        channel.confirmSelect();

        //未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());

        //通道添加监听
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---handleAck---multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    System.out.println("---handleAck----multiple--false");
                    confirmSet.remove(deliveryTag);
                }
            }
            //handleNack
            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                if (multiple) {
                    System.out.println("---handleAck---multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else {
                    System.out.println("---handleAck----multiple--false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });


        String msgStr = "中国";
        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,msgStr.getBytes());
        }

        /*try {
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
        }*/

//        channel.basicConsume(QUEUE_NAME,)
    }
}
