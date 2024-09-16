package com.example.kafkastream.service;
import com.example.kafkastream.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @KafkaListener(topics = "output-orders", groupId = "streams-group")
    public void listen(Order order) {
        redisTemplate.opsForValue().set(order.getId(), order);
        System.out.println("Received message from output-topic: " + order);
    }
}
