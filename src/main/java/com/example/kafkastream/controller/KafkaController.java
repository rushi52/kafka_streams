
package com.example.kafkastream.controller;
import com.example.kafkastream.model.Order;
//import com.example.kafkastream.service.HitService;
import com.example.kafkastream.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


//    @Autowired
//    HitService hitService;

//    @PostMapping("/publish")
//    public String publishMessage(@RequestBody Order order) {
//        kafkaProducerService.sendMessage("input-topic", order);
//        return "Message published: " + order;
//    }

//    @GetMapping("redis/getOrder")
//    public int getHitCount(){
//        return hitService.getHitCount();
//    }
//
//    @GetMapping("redis/incrementOrder")
//    public Long incrementOrder(){
//        return hitService.incrementHits();
//    }

    @PostMapping("/orders")
    public String createOrder(@RequestBody Order order) {
        if(redisTemplate.opsForValue().get(order.getId())!=null){
            System.out.println("Cached in redis"+order);
            return order.getId();
        }
        else {
            kafkaTemplate.send("input-orders", order.getId(), order);
            return "Order sent to Kafka!";
        }
    }
}
