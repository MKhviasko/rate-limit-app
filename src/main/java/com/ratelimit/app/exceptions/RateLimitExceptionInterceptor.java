package com.ratelimit.app.exceptions;

import com.ratelimit.app.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RateLimitExceptionInterceptor {

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ExceptionResponseDto> handleRateLimitExceededException(final RateLimitExceededException exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
            .body(new ExceptionResponseDto(HttpStatus.TOO_MANY_REQUESTS, exception.getMessage()));
    }
}
