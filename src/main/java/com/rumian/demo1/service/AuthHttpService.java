package com.rumian.demo1.service;

import org.springframework.stereotype.Service;

@Service
public class AuthHttpService extends BaseHttpService {
    private String auth = "http://localhost:8080";
    @Override
    public String getDomain() {
        return auth;
    }
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
