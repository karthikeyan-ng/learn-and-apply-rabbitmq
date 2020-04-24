package com.techstack.rabbitmq.consumer;

import com.techstack.rabbitmq.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.techstack.rabbitmq.MessageConfigInfo.MOBILE_QUEUE;

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
}
