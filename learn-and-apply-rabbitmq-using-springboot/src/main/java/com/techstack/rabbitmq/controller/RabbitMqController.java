package com.techstack.rabbitmq.controller;

import com.techstack.rabbitmq.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.techstack.rabbitmq.MessageConfigInfo.DIRECT_EXCHANGE_NAME;
import static com.techstack.rabbitmq.MessageConfigInfo.FANOUT_EXCHANGE_NAME;
import static com.techstack.rabbitmq.MessageConfigInfo.MOBILE_QUEUE;
import static com.techstack.rabbitmq.MessageConfigInfo.ROUTING_KEY_FOR_MOBILE;
import static com.techstack.rabbitmq.MessageConfigInfo.TOPIC_EXCHANGE_NAME;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RabbitMqController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String  processName(@PathVariable String name) {

        /**
         * Inorder to send a complex type (object) as message payload, you have to
         * implement Serializable interface
         */
        Person person = new Person(1L, name);

        /**
         * Here convertAndSend method uses {@Code SimpleMessageConverter} only supports
         * the following TYPES
         * <li>String</li>
         * <li>ByteArray</li>
         * <li>Serializable Objects</li>
         */

        /**
         * By using below method, we can directly send a message to Queue
         */
        rabbitTemplate.convertAndSend(MOBILE_QUEUE, person);

        /**
         * By using below method, we can send a message to "Direct Exchange" using "Routing Key"
         */
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, ROUTING_KEY_FOR_MOBILE, person);

        /**
         * By using below method, we can send a message to "Fanout Exchange"
         */
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", person);

        /**
         * By using below method, we can send a message to "Topic Exchange" using "Routing Key"
         */
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, "tv.mobile.ac", person);

        return "success";
    }
}
