package com.example.demo.holder;

import org.springframework.stereotype.Component;

@Component
public class JwtHolder {
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
