package com.techstack.queue.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.TOPIC_EXCHANGE_NAME;

public class TopicPublisher {

    public static void main(String[] args) throws URISyntaxException, IOException, TimeoutException,
            NoSuchAlgorithmException, KeyManagementException {

        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String mobileMessage = "Consumer interested on TV and Mobile topic";
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "tv.mobile.ac", null, mobileMessage.getBytes());

        channel.close();
        connection.close();
    }
}
