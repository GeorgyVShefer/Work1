package com.example.usermodule.controller;


import com.example.usermodule.model.UserEntity;
import com.example.usermodule.service.UserService;
import com.example.util.entity.UserEntityDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String, UserEntityDto> kafkaTemplate;
    private final UserService userService;

    @GetMapping("/{id}")
    public UserEntityDto getById(@PathVariable Integer id) {

        return null;
    }


    @PostMapping("/create")
    public String createUser(@RequestBody UserEntityDto user) {


        kafkaTemplate.send("user-events", user);
        return "Пользователь был сохранен";
    }

//    @DeleteMapping("/delete")
//    public String deleteUser(@RequestParam String email) {
//
//        Optional<UserEntity> userToDelete =
//                userEntityList.stream().filter(entity -> entity.getEmail().equals(email)).findFirst();
//
//        if (userToDelete.isPresent()){
//
//            userEntityList.remove(userToDelete.get());
//            kafkaTemplate.send("user-events", new UserEntity(email, "Deleted"));
//        }
//
//        return String.format("Пользователь %s был удален;", email);
//    }
}
