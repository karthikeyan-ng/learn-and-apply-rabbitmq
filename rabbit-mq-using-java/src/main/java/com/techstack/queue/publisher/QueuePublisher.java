package com.techstack.queue.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.techstack.queue.consts.ConnectionInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.techstack.queue.consts.ConnectionInfo.QUEUE_NAME;

/**
 *  A Message Producer Using Queue,
 */
public class QueuePublisher {

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException,
            KeyManagementException, URISyntaxException {

        ConnectionFactory factory = ConnectionInfo.createConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Exercise Step - 1
//        sendSimpleSingleMessage(channel);

        // Exercise Step - 2
        sendSeriesOfMessage(channel);


        channel.close();
        connection.close();
    }

    /**
     * Simple Message to a configured Queue Name
     *
     * @param channel
     * @throws IOException
     */
    private static void sendSimpleSingleMessage(Channel channel) throws IOException {
        String message = "Third message from RabbitMQ";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    }

    public static void sendSeriesOfMessage(Channel channel) throws IOException {
        List<String> messages = List.of("First", "Second", "Third", "Fourth");

        for(String message : messages) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
    }
}
