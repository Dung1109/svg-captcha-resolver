package com.example.demo.controller;


import com.example.demo.record.LoginResponse;
import com.example.demo.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;
    private final RestClient restClient;

    public CaptchaController(CaptchaService captchaService, RestClient restClient) {
        this.captchaService = captchaService;
        this.restClient = restClient;
    }

    @GetMapping("/solve")
    public ResponseEntity<LoginResponse> solveCaptcha() {
        var solvedCaptcha = captchaService.solveCaptcha();
        LoginRequest requestBody = new LoginRequest(solvedCaptcha.key(), solvedCaptcha.content(), "Tayduong01@", "0108352261");

        log.info("Request body: {}", requestBody);
        ResponseEntity<LoginResponse> response = restClient.post()
                .uri("/security-taxpayer/authenticate")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });

        log.info("Response: {}", response.getBody());
        var data = String.format("Captcha code: %s, Solved captcha: %s, Token: %s",
                solvedCaptcha.key(), solvedCaptcha.content(), Objects.requireNonNull(response.getBody()).token());

        return response;
    }
}



record LoginRequest(String ckey, String cvalue, String password, String username) {
}

//record Captcha(String key, String content) {
//}