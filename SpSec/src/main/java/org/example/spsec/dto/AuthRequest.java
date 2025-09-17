package org.example.spsec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}