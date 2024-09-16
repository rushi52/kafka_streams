
package com.example.kafkastream.controller;
import com.example.kafkastream.model.Order;
import com.example.kafkastream.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    private KafkaProducerService kafkaProducerService;

//    @PostMapping("/publish")
//    public String publishMessage(@RequestBody Order order) {
//        kafkaProducerService.sendMessage("input-topic", order);
//        return "Message published: " + order;
//    }

    @PostMapping("/orders")
    public String createOrder(@RequestBody Order order) {
        kafkaTemplate.send("input-orders", order.getId(), order);
        return "Order sent to Kafka!";
    }
}
