package com.techstack.queue.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.DIRECT_EXCHANGE_NAME;
import static com.techstack.queue.consts.ConnectionInfo.FANOUT_EXCHANGE_NAME;
import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_AC;
import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_MOBILE;
import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_TV;

public class FanoutPublisher {

    public static void main(String[] args) throws URISyntaxException, IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Mobile
        String mobileMessage = "Consumer would like to purchase an Apple iPhone11 and Voltas AC";
        channel.basicPublish(FANOUT_EXCHANGE_NAME, "", null, mobileMessage.getBytes());

        channel.close();
        connection.close();
    }
}
