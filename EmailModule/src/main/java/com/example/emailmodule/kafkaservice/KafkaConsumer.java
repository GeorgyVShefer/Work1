package com.example.emailmodule.kafkaservice;

import com.example.usermodule.entity.UserEntity;
import com.example.emailmodule.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consumerEvent(UserEntity entity){

        if (entity.getOperation().equals("Created")){

            emailService.greeting();
        }
        else {

            emailService.deleteMessage();
        }
    }
}
