package com.techstack.queue.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_AC;
import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_MOBILE;
import static com.techstack.queue.consts.ConnectionInfo.EXCHANGE_NAME;
import static com.techstack.queue.consts.ConnectionInfo.ROUTING_KEY_FOR_TV;

public class DirectPublisher {

    public static void main(String[] args) throws URISyntaxException, IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Mobile
        String mobileMessage = "Consumer would like to purchase an Apple iPhone11";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_FOR_MOBILE, null, mobileMessage.getBytes());

        // TV
        String tvMessage = "Consumer would like to purchase an Apple TV";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_FOR_TV, null, tvMessage.getBytes());

        // AC
        String acMessage = "Consumer would like to purchase Voltas AC";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_FOR_AC, null, acMessage.getBytes());

        channel.close();
        connection.close();
    }
}
