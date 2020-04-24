package com.techstack.queue.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.QUEUE_NAME;

public class RealTimePublisherExample {

    public static void main(String[] args) throws URISyntaxException, IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject json = new JSONObject();
        json.put("fromDate", "01-Feb-2020");
        json.put("toDate", "28-Feb-2020");
        json.put("email", "test@gmail.com");
        json.put("query", "select * from data");

        channel.basicPublish("", QUEUE_NAME, null, json.toString().getBytes());

        channel.close();
        connection.close();

    }
}
