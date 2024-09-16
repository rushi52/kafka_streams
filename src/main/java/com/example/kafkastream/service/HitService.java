//package com.example.kafkastream.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class HitService {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    private static final String HIT_COUNT_KEY = "hitCount";
//
//
//    public Long incrementHits() {
//
//        return redisTemplate.opsForValue().increment(HIT_COUNT_KEY);
//    }
//
//
//    public int getHitCount() {
//        return (int) redisTemplate.opsForValue().get(HIT_COUNT_KEY);
//
//    }
//}
