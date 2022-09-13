package com.example.booking_cinema.controller;


import com.example.booking_cinema.service.ProducerClass;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final ProducerClass producer;

    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return "Published successfully";
    }

    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic("user", 3, (short)1);
    }
}
