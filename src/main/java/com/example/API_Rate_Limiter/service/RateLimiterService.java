package com.example.API_Rate_Limiter.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.API_Rate_Limiter.config.RateLimitConfig;
import com.example.API_Rate_Limiter.model.RequestInfo;

@Service
public class RateLimiterService {

    private final Map<String, RequestInfo> requestCounts = new ConcurrentHashMap<>();

    // Configuration based on role or endpoint
    private final Map<String, RateLimitConfig> limits = Map.of(
            "ADMIN", new RateLimitConfig(5, Duration.ofMinutes(15).toMillis()),
            "USER", new RateLimitConfig(15, Duration.ofMinutes(15).toMillis()),
            "/api/heavy", new RateLimitConfig(10, Duration.ofMinutes(10).toMillis()),
            "/api/light", new RateLimitConfig(5, Duration.ofMinutes(10).toMillis())
    );

    public boolean isAllowed(String clientId, String key) {
        RateLimitConfig config = limits.getOrDefault(key, new RateLimitConfig(100, Duration.ofMinutes(15).toMillis()));
        long now = System.currentTimeMillis();
        String compoundKey = clientId + ":" + key;

        RequestInfo info = requestCounts.getOrDefault(compoundKey, new RequestInfo(0, now));

        if (now - info.getStartTime() > config.getTimeWindowMs()) {
            info = new RequestInfo(1, now);
        } else {
            if (info.getCount() >= config.getLimit()) return false;
            info.increment();
        }

        requestCounts.put(compoundKey, info);
        return true;
    }
}
