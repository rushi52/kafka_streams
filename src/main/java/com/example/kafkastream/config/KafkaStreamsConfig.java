package com.example.kafkastream.config;



//package com.example.kafkastream.config;
//
//import org.apache.kafka.streams.StreamsConfig;
////import org.apache.kafka.streams.kstream.KStreamBuilder;
//import org.apache.kafka.streams.kstream.Produced;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.common.serialization.Serdes;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaStreamsConfig {
//
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    public KafkaStreamsConfiguration kafkaStreamsConfig() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        return new KafkaStreamsConfiguration(props);
//    }
//}




import com.example.kafkastream.model.Order;
import com.example.kafkastream.process.OrderCalculator;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

@Configuration
public class KafkaStreamsConfig {

//    public class JsonSerde<T> extends Serdes.WrapperSerde<T> {
//        public JsonSerde() {
//            super(new JsonSerializer<>(), new JsonDeserializer<>());
//        }
//    }

    @Bean
    public Properties kafkaProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-processing-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");


        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);

        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, orderSerde.getClass().getName());
        return props;
    }

    @Bean
    public Topology createTopology() {
        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Order> stream = builder.stream("input-orders", Consumed.with(Serdes.String(),orderSerde));

        stream.foreach((key, order) -> {
            double totalAmount = OrderCalculator.calculateTotalAmount(order);
            order.setTotalAmount(totalAmount);
        });

        stream.to("output-orders", Produced.with(Serdes.String(),orderSerde));

        return builder.build();
    }
}

