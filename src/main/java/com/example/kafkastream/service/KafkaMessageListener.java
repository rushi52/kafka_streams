package com.example.kafkastream.service;
import com.example.kafkastream.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "output-orders", groupId = "streams-group")
    public void listen(Order order) {
        System.out.println("Received message from output-topic: " + order);
    }
}
