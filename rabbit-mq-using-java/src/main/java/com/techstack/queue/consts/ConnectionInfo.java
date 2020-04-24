package com.techstack.queue.consts;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class ConnectionInfo {

    private static final String URI = "amqp://cidvquen:fh_90S6ppDLDTO-c1Z5NapGmk_ZFmyH3@kangaroo.rmq.cloudamqp.com/cidvquen";
    private static final String USER_NAME = "cidvquen";
    private static final String PASSWORD = "fh_90S6ppDLDTO-c1Z5NapGmk_ZFmyH3";

    public static final String QUEUE_NAME = "Queue-1";

    public static ConnectionFactory createConnectionFactory() throws NoSuchAlgorithmException, KeyManagementException,
            URISyntaxException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(URI);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASSWORD);
        return factory;
    }

}
