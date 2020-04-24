package com.techstack.queue.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.HEADERS_EXCHANGE_NAME;

public class HeadersPublisher {

    public static void main(String[] args) throws URISyntaxException, IOException, TimeoutException,
            NoSuchAlgorithmException, KeyManagementException {

        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and TV";

        Map<String, Object> headers = new HashMap<>();
        headers.put("item1", "mobile");
        headers.put("item2", "television");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        basicProperties.builder().headers(headers).build();

        channel.basicPublish(HEADERS_EXCHANGE_NAME, "", basicProperties, message.getBytes());

        channel.close();
        connection.close();
    }
}
