package com.example.usermodule.controller;

import com.example.usermodule.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private List<UserEntity> userEntityList = new ArrayList<>();
    private final KafkaTemplate<String, UserEntity> kafkaTemplate;

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Integer id) {

        return userEntityList.get(id);
    }


    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {

        userEntityList.add(user);

        kafkaTemplate.send("user-events", new UserEntity(user.getEmail(), "Created"));

        return user;
    }
}
