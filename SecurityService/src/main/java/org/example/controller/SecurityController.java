package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dto.AuthResponse;
import org.example.dto.RegisterRequest;
import org.example.entity.UserCredentional;
import org.example.service.JwtUtil;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SecurityController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody RegisterRequest registerRequest){

        if(userService.existsByEmail(registerRequest.getEmail()) || userService.existsByUsername(registerRequest.getUsername())){

            return ResponseEntity.badRequest().body("user already exist");
        }
        UserCredentional user = UserCredentional.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getPassword())
                .password(registerRequest.getPassword())
                .roles(Collections.singleton("user"))
                .build();

        userService.registerUser(user);

        String token = jwtUtil.createToken(user.getRoles(), user.getUsername());

        return ResponseEntity.ok(new AuthResponse(token, "Bearer", 3600));
    }
}
