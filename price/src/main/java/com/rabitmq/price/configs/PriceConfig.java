package com.rabitmq.price.configs;

import com.rabitmq.price.constants.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
public class PriceConfig {

    @Bean
    public Queue queue(){
        return QueueBuilder.durable(Constants.QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", Constants.DLQ_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", Constants.DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(Constants.DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(Constants.EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(Constants.DLQ_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(@Qualifier("exchange") DirectExchange directExchange, @Qualifier("queue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(Constants.ROUTING_KEY);
    }

    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterExchange") DirectExchange deadLetterExchange, @Qualifier("deadLetterQueue") Queue deadLetterQueue) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(Constants.DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jacksonMessageConverter(){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setIdClassMapping(Map.of(
                "com.rabitmq.price.dtos.Price", com.rabitmq.price.dtos.Price.class,
                "com.rabitmq.pcw.dtos.Quote", com.rabitmq.price.dtos.Quote.class

        ));
        typeMapper.setTrustedPackages("*");
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
