package com.example.booking_cinema.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerClass {

    private static final Logger logger = LoggerFactory.getLogger(ProducerClass.class);

    private static final String TOPIC = "user";

    private final KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("***************** Producer sending the message now -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
