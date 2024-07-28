package com.jbdl.user_service.config;

import com.jbdl.common_service.Commons;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Commons.KAFCA_BOOTSTRAP_SERVER_IP);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(map);
    }

    @Bean
    public KafkaTemplate <String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
