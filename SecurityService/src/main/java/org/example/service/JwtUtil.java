package org.example.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;


@Component
public class JwtUtil {

    public static final String SECRET = "3c1182b6b964bd34aaa3087a639dd571acbf5c1da0cd4cef23472345dd848c5c";

    public String createToken(Set<String> roles, String username){

        return Jwts.builder()
                .claim("roles", roles)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSecretKey(){

        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public boolean validateToken(String token){

        try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException ex){

            return false;
        }
    }

    public String getUsernameFromToken(String token){

        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
}
