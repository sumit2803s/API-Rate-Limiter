package com.example.API_Rate_Limiter.model;

public class RequestInfo {
    private int count;
    private long startTime;

    public RequestInfo(int count, long startTime) {
        this.count = count;
        this.startTime = startTime;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        this.count++;
    }

    public long getStartTime() {
        return startTime;
    }
}
