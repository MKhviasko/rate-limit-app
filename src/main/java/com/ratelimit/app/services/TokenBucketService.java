package com.ratelimit.app.services;

import com.ratelimit.app.dto.RateLimitResponseDto;

public interface TokenBucketService {
    RateLimitResponseDto checkRateLimitAndConsume(final String routeTemplate, boolean isFreeEdition);
}
