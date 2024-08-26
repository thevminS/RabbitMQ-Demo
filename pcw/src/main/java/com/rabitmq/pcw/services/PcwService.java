package com.rabitmq.pcw.services;

import com.rabitmq.pcw.constants.Constants;
import com.rabitmq.pcw.dtos.Price;
import com.rabitmq.pcw.dtos.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PcwService {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    public Price sentToPrice(Quote quote) {
        log.info("Quote : {}", quote);
        Price response = (Price) rabbitTemplate.convertSendAndReceive(exchange.getName(), Constants.ROUTING_KEY, quote);
        log.info("Prices : {}", response);
        return response;
    }
}
