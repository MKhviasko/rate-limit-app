package com.ratelimit.app.facades;

import com.ratelimit.app.dto.RateLimitResponseDto;

public interface RateLimitFacade {

    RateLimitResponseDto checkRateLimit(final String routeTemplate, final String userToken);
}
