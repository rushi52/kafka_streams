package com.example.kafkastream.service;

import com.example.kafkastream.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendMessage(String topic, Order order) {

        kafkaTemplate.send(topic, order);
    }
}
