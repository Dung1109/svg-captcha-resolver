package com.example.demo.controller;


import com.example.demo.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/solve")
    public String solveCaptcha() {
        String captchaCode = captchaService.getCaptchaCode();
        String solvedCaptcha = captchaService.solveCaptcha();

        return String.format("Captcha code: %s, Solved captcha: %s",
                captchaCode, solvedCaptcha);
    }
}
