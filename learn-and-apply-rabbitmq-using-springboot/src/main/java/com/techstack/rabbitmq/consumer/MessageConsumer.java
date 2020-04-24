package com.techstack.rabbitmq.consumer;

import com.techstack.rabbitmq.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import static com.techstack.rabbitmq.MessageConfigInfo.MOBILE_QUEUE;
import static com.techstack.rabbitmq.MessageConfigInfo.TV_QUEUE;

@Slf4j
@Service
public class MessageConsumer {

    /**
     * If you are sure about the Message Type (Data), use the same
     * like "Person". Else, it would throw an Exception
     *
     * @param person
     */
    @RabbitListener(queues = MOBILE_QUEUE)
    public void getMessageFromMobileQueue(Person person) {

        log.info("Message Received : {}", person);

    }

    @RabbitListener(queues = TV_QUEUE)
    public void getMessageFromTvQueue(byte[] message) throws IOException, ClassNotFoundException {

        /**
         * Convert byte[] array message to Object type
         */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message);
        ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream);
        Person person = (Person) objectInput.readObject();
        byteArrayInputStream.close();
        objectInput.close();

        log.info("Message Received : {}", person);

    }
}
