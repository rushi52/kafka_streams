package com.example.kafkastream.config;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerde implements Serde<JsonNode> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Serializer<JsonNode> serializer() {
        return (topic, data) -> {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public Deserializer<JsonNode> deserializer() {
        return (topic, data) -> {
            try {
                return objectMapper.readTree(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

