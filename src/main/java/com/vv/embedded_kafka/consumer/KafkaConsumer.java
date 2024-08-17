package com.vv.embedded_kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {
    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

