package com.example.kafkastream.process;


import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaStreamsRunner {

    @Autowired
    private Properties kafkaProperties;

    @Autowired
    private Topology topology;

    @Bean
    public KafkaStreams kafkaStreams() {
        KafkaStreams streams = new KafkaStreams(topology, kafkaProperties);
        streams.start();

        // Add shutdown hook to close the streams gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        return streams;
    }
}

