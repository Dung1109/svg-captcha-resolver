package com.example.demo;

import com.example.demo.record.CaptchaResponse;
import com.example.demo.solver.CaptchaSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;


@SpringBootApplication
public class Demo1Application implements CommandLineRunner {

    @Autowired
    RestClient restClient;

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ResponseEntity<CaptchaResponse> response = restClient.get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CaptchaResponse>() {});

//        CaptchaResponse captchaResponse = response.getBody();
//        String captchaCode = captchaResponse.key();
//        String captchaSvg = captchaResponse.content();
//
////        System.out.println("Response: " + captchaResponse.content());
//
//        String result = CaptchaSolver.solveCaptcha(captchaSvg);
//        System.out.println("Result: " + result);
//        System.out.println("Captcha code: " + captchaCode);
    }
}

