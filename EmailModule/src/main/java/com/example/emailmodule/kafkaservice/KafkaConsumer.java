package com.example.emailmodule.kafkaservice;

import com.example.emailmodule.service.EmailService;
import com.example.util.entity.UserEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;


    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consumerEvent(UserEntityDto entity){


        if (entity.getOperation().equals("Created")){

            emailService.greeting(entity);
        }
        else if(entity.getOperation().equals("Deleted")){

            emailService.deleteMessage(entity);
        }
    }
}
