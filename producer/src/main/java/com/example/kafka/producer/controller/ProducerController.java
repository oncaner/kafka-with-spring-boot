package com.example.kafka.producer.controller;

import com.example.kafka.producer.dto.Response;
import com.example.kafka.producer.service.ProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public String sendMessageToKafka(@RequestBody String message) {
        producerService.sendMessageToKafka(message);

        return "Message sent successfully.";
    }

    @PostMapping
    public Response sendJsonObjectToKafka(@RequestBody Response response) {
        producerService.sendMessagesToKafka(response);

        return response;
    }

}
