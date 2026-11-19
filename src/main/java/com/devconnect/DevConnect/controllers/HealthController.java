package com.devconnect.DevConnect.controllers;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HealthController {
    
    // GET /health
    @GetMapping("/health")
    public String health() {
        return "DevConnect Backend is LIVE and HEALTHY";
    }

    // GET /api/v1/status
    @GetMapping("/api/v1/status") 
    public Map<String, Object> status() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "OK");
        map.put("service" ,"DevConnect");
        map.put("version", "1.0.0");
        map.put("timestamp", LocalDateTime.now().toString());

        return map;
    }

    // JACKSON - Converts JAVA Objects to HTTP requests compatible format.
}