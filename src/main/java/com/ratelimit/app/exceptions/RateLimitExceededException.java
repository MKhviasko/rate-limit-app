package com.ratelimit.app.exceptions;

public class RateLimitExceededException extends RuntimeException{

    public RateLimitExceededException(String message) {
        super(message);
    }
}
