package com.ratelimit.app.dto;

import org.springframework.http.HttpStatus;

public class ExceptionResponseDto {
    private HttpStatus status;
    private String message;

    public ExceptionResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
