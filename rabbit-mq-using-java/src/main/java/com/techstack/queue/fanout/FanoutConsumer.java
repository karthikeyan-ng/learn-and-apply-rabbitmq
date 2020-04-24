package com.techstack.queue.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.AC_QUEUE;
import static com.techstack.queue.consts.ConnectionInfo.MOBILE_QUEUE;
import static com.techstack.queue.consts.ConnectionInfo.TV_QUEUE;

public class FanoutConsumer {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException,
            IOException, TimeoutException {

        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received = " + message);
        };

        List<String> queues = List.of(MOBILE_QUEUE, AC_QUEUE, TV_QUEUE);
        for (String queue : queues) {
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
        }
    }
}
