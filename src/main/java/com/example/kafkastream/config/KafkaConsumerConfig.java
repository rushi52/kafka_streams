package com.example.kafkastream.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.example.kafkastream.model.Order;

import java.util.HashMap;
import java.util.Map;

    @EnableKafka
    @Configuration
    public class KafkaConsumerConfig {


        // Consumer Configuration
        @Bean
        public ConsumerFactory<String, Order> consumerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, String.class); // Key Deserializer
            configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);  // Value Deserializer
            configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");  // Trusted packages
            configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.kafkastream.model.Order");
            configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,StringDeserializer.class);
            configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,JsonDeserializer.class);

            return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
                    new JsonDeserializer<>(Order.class));
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }
    }


