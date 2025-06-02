package com.example.emailmodule.configuration;

import com.example.usermodule.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEntity>
    concurrentKafkaListenerContainerFactory(ConsumerFactory<String, UserEntity> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, UserEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
