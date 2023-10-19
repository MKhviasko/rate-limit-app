package com.ratelimit.app.dto;

public class RateLimitResponseDto {
    private int remainingTokens;
    private boolean allowed;

    public RateLimitResponseDto(int remainingTokens, boolean allowed) {
        this.remainingTokens = remainingTokens;
        this.allowed = allowed;
    }

    public int getRemainingTokens() {
        return remainingTokens;
    }

    public void setRemainingTokens(int remainingTokens) {
        this.remainingTokens = remainingTokens;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
}
