package com.rabitmq.price.constants;

public class Constants {

    public static final String QUEUE_NAME = "quote.queue";
    public static final String REPLY_QUEUE_NAME = "price.queue";
    public static final String DEAD_LETTER_QUEUE_NAME = "dlq";

    public static final String EXCHANGE_NAME = "pcw.exchange";
    public static final String DLQ_EXCHANGE_NAME = "dlq.exchange";


    public static final String ROUTING_KEY = "quote.key";
    public static final String DEAD_LETTER_ROUTING_KEY = "dlq.key";

    public static final long MESSAGE_TTL = 10000L;

}
