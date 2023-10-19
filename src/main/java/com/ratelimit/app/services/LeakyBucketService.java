package com.ratelimit.app.services;

import com.ratelimit.app.dto.RateLimitResponseDto;

public interface LeakyBucketService {
    RateLimitResponseDto checkRateLimitAndConsume(final String routeTemplate, final boolean isFreeEdition);
}
