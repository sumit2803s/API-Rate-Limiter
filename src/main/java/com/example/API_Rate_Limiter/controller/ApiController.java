package com.example.API_Rate_Limiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/heavy")
    public String heavyEndpoint() {
        return " Heavy endpoint accessed";
    }

    @GetMapping("/light")
    public String lightEndpoint() {
        return " Light endpoint accessed";
    }

    @GetMapping("/admin/data")
    public String adminEndpoint() {
        return "Admin data accessed";
    }

    @GetMapping("/user/profile")
    public String userEndpoint() {
        return " User profile accessed";
    }
}
