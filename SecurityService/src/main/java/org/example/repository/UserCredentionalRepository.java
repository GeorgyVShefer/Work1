package org.example.repository;

import org.example.entity.UserCredentional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentionalRepository extends JpaRepository<UserCredentional, Long> {

    Optional<UserCredentional> findByEmail(String email);
    Optional<UserCredentional> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
