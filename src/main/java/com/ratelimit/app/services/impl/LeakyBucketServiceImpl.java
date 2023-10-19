package com.ratelimit.app.services.impl;

import com.ratelimit.app.configuration.EndpointRateLimit;
import com.ratelimit.app.configuration.ExceptionMessages;
import com.ratelimit.app.configuration.RateLimitConfig;
import com.ratelimit.app.data.LeakyBucket;
import com.ratelimit.app.dto.RateLimitResponseDto;
import com.ratelimit.app.exceptions.RateLimitExceededException;
import com.ratelimit.app.services.LeakyBucketService;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeakyBucketServiceImpl implements LeakyBucketService {

    private final Map<String, Map<String, LeakyBucket>> rateLimitMap = new ConcurrentHashMap<>();

    @Autowired
    private RateLimitConfig rateLimitConfig;

    @PostConstruct
    void init() {
        for (EndpointRateLimit rateLimit : rateLimitConfig.getRateLimitList()) {
            rateLimitMap.computeIfAbsent("free", k -> new ConcurrentHashMap<>())
                .put(rateLimit.getEndpoint(), new LeakyBucket(rateLimit.getBurstFree(), rateLimit.getSustainedFree()));
            rateLimitMap.computeIfAbsent("paid", k -> new ConcurrentHashMap<>())
                .put(rateLimit.getEndpoint(), new LeakyBucket(rateLimit.getBurstPaid(), rateLimit.getSustainedPaid()));
        }
        //        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //        rateLimitMap.values()
        //            .forEach(leakyBucket -> scheduler
        //                .scheduleAtFixedRate(leakyBucket::refillBucket, 0, 1000 / leakyBucket.getSustainedRate(), TimeUnit.MILLISECONDS));
    }

    @Override
    public RateLimitResponseDto checkRateLimitAndConsume(final String routeTemplate, final boolean isFreeEdition) {
        var editionType = isFreeEdition ? "free" : "paid";
        var map = rateLimitMap.get(editionType);
        var tokenBucket = map.get(routeTemplate);
        if (Objects.isNull(tokenBucket)) {
            new RateLimitResponseDto(0, false);
        }
        var isAllowed = tokenBucket.allowRequest();
        if (!isAllowed) {
            throw new RateLimitExceededException(
                String.format(ExceptionMessages.RATE_LIMIT_EXCEEDED_EXCEPTION_MESSAGE, routeTemplate));
        }
        return new RateLimitResponseDto(tokenBucket.getCapacity(), isAllowed);
    }
}
