package com.ratelimit.app.facades.impl;

import com.ratelimit.app.dto.RateLimitResponseDto;
import com.ratelimit.app.facades.RateLimitFacade;
import com.ratelimit.app.services.TokenBucketService;
import com.ratelimit.app.utils.TokenUtils;
import com.ratelimit.app.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateLimitFacadeImpl implements RateLimitFacade {

    @Autowired
    private TokenBucketService rateLimitServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenUtils tokenUtils;

    public RateLimitResponseDto checkRateLimit(final String routeTemplate, final String userToken) {
        if (userToken.isBlank()) {
            //default case (free)
            return rateLimitServiceImpl.checkRateLimitAndConsume(routeTemplate, true);
        } else {
            var username = tokenUtils.extractUsernameFromToken(userToken);
            var user = userService.findUserByUsername(username);
            var isFree = user.isFreeEdition();
            return rateLimitServiceImpl.checkRateLimitAndConsume(routeTemplate, isFree);
        }
    }
}
