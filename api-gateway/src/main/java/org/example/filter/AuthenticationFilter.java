package org.example.filter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${security.service.url:http://localhost:9191}")
    private String securityServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/auth")) {

            filterChain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/api/users")) {

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Missing authorization header!");
                return;
            }

            String token = authHeader.substring(7);

            if (!validateTokenWithSecurity(token)){

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("invalidate jwt token");
                return;
            }

        }

        filterChain.doFilter(request,response);
    }
    @CircuitBreaker(name = "securityServiceBreaker", fallbackMethod = "securityFallback")
    private boolean validateTokenWithSecurity(String token) {

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("token", token);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    securityServiceUrl + "/auth/validate",
                    request,
                    Map.class
            );
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){

                Boolean isValid = (Boolean) response.getBody().get("valid");
                return isValid != null && isValid;
            }
        }catch (Exception e){

            e.getCause();
        }

        return false;
    }

    private boolean securityFallback(String token, Exception ex){

        return false;
    }
}
