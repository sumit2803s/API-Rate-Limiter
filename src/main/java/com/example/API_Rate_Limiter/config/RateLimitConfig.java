package com.example.API_Rate_Limiter.config;

public class RateLimitConfig {
    private int limit;
    private long timeWindowMs;

    public RateLimitConfig(int limit, long timeWindowMs) {
        this.limit = limit;
        this.timeWindowMs = timeWindowMs;
    }

    public int getLimit() {
        return limit;
    }

    public long getTimeWindowMs() {
        return timeWindowMs;
    }
}
