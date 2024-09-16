//package com.example.kafkastream.process;
//
//import com.example.kafkastream.config.KafkaStreamsConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.Produced;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//import org.springframework.kafka.config.StreamsBuilderFactoryBean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//@Component
//public class StreamProcessor {
//
//    @Autowired
//    KafkaStreamsConfiguration kafkaStreamsConfiguration;
//
//    private KafkaStreams kafkaStreams;
//
//    @Autowired
//    private StreamsBuilderFactoryBean streamsBuilderFactoryBean;
//
//    @PostConstruct
//    public void startStream() throws Exception {
//
//
//
//
//
//            StreamsBuilder builder = new StreamsBuilder();
//
//            KStream<String, String> stream = builder.stream("input-topic");
//
//            stream
//                    .peek((key, value) -> System.out.println("Processing Key: " + key + ", Value: " + value))
//                    .to("output-topic", Produced.with(Serdes.String(), Serdes.String()));
//
//            kafkaStreams = new KafkaStreams(builder.build(),kafkaStreamsConfiguration.asProperties());
//            kafkaStreams.start();
//        }
//
//        @PreDestroy
//        public void stopStream() {
//            if (kafkaStreams != null) {
//                kafkaStreams.close();
//            }
//        }
//
////    @Component
////    public class StreamProcessor {
//
////        @Autowired
////        private StreamsBuilder streamsBuilder;
////
////        @PostConstruct
////        public void startStream() {
////            KStream<String, String> stream = streamsBuilder.stream("input-topic");
////            stream.foreach((key, value) -> System.out.println("Received Key: " + key + ", Value: " + value));
////
////        }
//
//
//
//
//
//
//
//
//}
