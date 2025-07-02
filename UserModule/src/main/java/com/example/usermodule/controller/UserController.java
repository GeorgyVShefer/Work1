package com.example.usermodule.controller;


import com.example.usermodule.service.UserService;
import com.example.util.entity.UserEntityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Пример API", description = "Пример контроллера с OPEN API")
public class UserController {

    private final KafkaTemplate<String, UserEntityDto> kafkaTemplate;
    private final UserService userService;

    @GetMapping("/{id}")
    public UserEntityDto getById(@PathVariable Long id) {

        return userService.getById(id);
    }

    @Operation(summary = "Приветствую", description = "Пример получение пользотвателяф по идентификатору")
    @PostMapping("/create")
    public String createUser(@RequestBody UserEntityDto user) {

        userService.save(user);
        kafkaTemplate.send("user-events", user);
        return "Пользователь был сохранен";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody UserEntityDto dto, @RequestParam Long id) {

        userService.update(dto, id);
        return "Пользователь был изменен!";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        userService.deleteById(id);
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
