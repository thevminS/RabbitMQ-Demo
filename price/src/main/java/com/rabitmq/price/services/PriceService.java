package com.rabitmq.price.services;

import com.rabitmq.price.constants.Constants;
import com.rabitmq.price.dtos.Price;
import com.rabitmq.price.dtos.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class PriceService {

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public Price handleQuote(Quote quote) throws InterruptedException {
        log.info("Quote : {}", quote);
        // Process the Quote and create a Price
        Price price = new Price();
        price.setId(quote.getId());
        price.setPriceList(generateRandomIntegers());

        log.info("Price : {}", price);
        return price;
    }



    public List<Integer> generateRandomIntegers() throws InterruptedException {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            int randomNumber = random.nextInt(2000 - 1001) + 1000;
            randomNumbers.add(randomNumber);
        }
        Thread.sleep(5000);
        return randomNumbers;
    }
}
