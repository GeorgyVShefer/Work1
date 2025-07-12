package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.UserCredentional;
import org.example.repository.UserCredentionalRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCredentionalRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserCredentional> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserCredentional> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserCredentional registerUser(UserCredentional user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
