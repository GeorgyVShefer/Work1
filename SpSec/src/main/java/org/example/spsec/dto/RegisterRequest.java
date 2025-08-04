package org.example.spsec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}