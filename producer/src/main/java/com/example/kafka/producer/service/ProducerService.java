package com.example.kafka.producer.service;

import com.example.kafka.producer.dto.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToKafka(String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Message sent to kafka successfully : " + message);
    }

    public void sendMessagesToKafka(Response... message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Message sent to kafka successfully : " + Arrays.toString(message));
    }

    @PostConstruct
    public void sendStringMessage() {
        Response response1 = new Response("Name-1", "Description-1");
        Response response2 = new Response("Name-2", "Description-2");
        Response response3 = new Response("Name-3", "Description-3");
        Response response4 = new Response("Name-4", "Description-4");
        Response response5 = new Response("Name-5", "Description-5");

        sendMessagesToKafka(response1, response2, response3, response4, response5);
    }

}
