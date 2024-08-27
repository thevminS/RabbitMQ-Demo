package com.rabitmq.price.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabitmq.price.constants.Constants;
import com.rabitmq.price.dtos.Quote;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeadLetterQueueListener {

//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @RabbitListener(queues = Constants.DEAD_LETTER_QUEUE_NAME)
//    public void handleDLQMessage(Message message) throws IOException {
//        Quote quote = objectMapper.readValue(message.getBody(), Quote.class);
//        // Log or process the DLQ message
//        System.out.println("Received message from DLQ: " + quote);
//    }

    /*
    * Can check using management tool (rabbitmq_shovel plugin can be used to move message around queues) to replay(move dlq messages to quote.queue).
    */
}
