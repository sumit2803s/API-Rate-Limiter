package com.example.API_Rate_Limiter.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.API_Rate_Limiter.model.RequestInfo;

@Service
public class RateLimiterService {
    private final Map<String, RequestInfo> requestCounts = new ConcurrentHashMap<>();
    private final int LIMIT = 15;
    private final long TIME_WINDOW = Duration.ofMinutes(15).toMillis();

    public synchronized boolean isAllowed(String clientId) {
        long currentTime = System.currentTimeMillis();
        RequestInfo info = requestCounts.getOrDefault(clientId, new RequestInfo(0, currentTime));

        if (currentTime - info.getStartTime() > TIME_WINDOW) {
            info = new RequestInfo(1, currentTime);
        } else {
            if (info.getCount() >= LIMIT) return false;
            info.increment();
        }

        requestCounts.put(clientId, info);
        return true;
    }
}
