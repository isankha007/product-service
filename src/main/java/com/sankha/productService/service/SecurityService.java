package com.sankha.productService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sankha.productService.config.AppConstants;
import com.sankha.productService.config.ApplicationConfig;
import com.sankha.productService.dto.VerificationRequest;
import com.sankha.productService.dto.VerificationResponse;
import com.sankha.productService.exceptions.PermissionDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.sankha.productService.config.AppConstants.FAILED;


@Service
@RequiredArgsConstructor
public class SecurityService {
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    private void validateToken(String requestJson) {
        HttpEntity<String> entity = ApplicationConfig.configureRequest(requestJson);
        ResponseEntity<VerificationResponse> verificationResponse = this.restTemplate.postForEntity(AppConstants.USERS_VERIFY, entity, VerificationResponse.class);
        VerificationResponse response = verificationResponse != null ? verificationResponse.getBody() : null;
        if (response == null || response.status().equals(FAILED)) {
            throw new PermissionDeniedException("Access Denied");
        }
    }

    public void validateAccess(String username, String token) {
        try {
            String requestJson = this.objectMapper.writeValueAsString(new VerificationRequest(token, username));
            validateToken(requestJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
