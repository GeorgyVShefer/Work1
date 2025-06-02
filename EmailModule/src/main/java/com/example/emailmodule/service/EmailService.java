package com.example.emailmodule.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void greeting(){

        System.out.println("Спасибо за регистрацию на нашем сервисе!");
    }

    public void deleteMessage(){

        System.out.println("Ваш аккаунт был удален!");
    }
}
