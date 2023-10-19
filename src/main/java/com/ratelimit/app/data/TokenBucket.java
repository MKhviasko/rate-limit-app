package com.ratelimit.app.data;

import java.time.Instant;

public class TokenBucket {

    private final int burstCapacity;
    private final int sustainedRate;
    private int tokens;
    private Instant lastRefillTime;

    public TokenBucket(int burstCapacity, int sustainedRate) {
        this.burstCapacity = burstCapacity;
        this.sustainedRate = sustainedRate;
        this.tokens = burstCapacity;
        this.lastRefillTime = Instant.now();
    }

    public synchronized boolean tryConsume() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    public synchronized int getTokens() {
        refillTokens();
        return tokens;
    }

    public synchronized void refillTokens() {
        var now = Instant.now();
        long timeElapsed = now.toEpochMilli() - lastRefillTime.toEpochMilli();
        int tokensToAdd = (int) (timeElapsed * sustainedRate / 60000);
        if (tokensToAdd > 0) {
            tokens = Math.min(tokens + tokensToAdd, burstCapacity);
            lastRefillTime = now;
        }
    }
}
