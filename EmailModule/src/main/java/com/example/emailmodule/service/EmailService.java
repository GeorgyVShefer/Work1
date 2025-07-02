package com.example.emailmodule.service;

import com.example.util.entity.UserEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public void greeting(UserEntityDto dto){

        sendEmail(dto.getEmail(), "Hello", "Hello, nice meet you!)");
    }

    public void deleteMessage(UserEntityDto dto){

        sendEmail(dto.getEmail(), "Hello", "Your account is deleted!(");
    }
}
