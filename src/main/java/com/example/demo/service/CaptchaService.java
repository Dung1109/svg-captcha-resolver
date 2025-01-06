package com.example.demo.service;

import com.example.demo.record.CaptchaResponse;
import com.example.demo.solver.CaptchaSolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CaptchaService {


    private final RestClient restClient;

    public CaptchaService(RestClient restClient) {
        this.restClient = restClient;
    }

    private CaptchaResponse fetchCaptcha() {
        ResponseEntity<CaptchaResponse> response = restClient.get()
                .uri("/captcha")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public CaptchaResponse solveCaptcha() {
        CaptchaResponse captchaResponse = fetchCaptcha();
        return new CaptchaResponse(captchaResponse.key(), CaptchaSolver.solveCaptcha(captchaResponse.content()));
    }
}
