package com.ratelimit.app.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RateLimitConfig {

    @JsonProperty("rateLimitsPerEndpoint")
    private List<EndpointRateLimit> rateLimitsPerEndpoint;

    public List<EndpointRateLimit> getRateLimitList() {
        return rateLimitsPerEndpoint;
    }

    public void setRateLimitsPerEndpoint(List<EndpointRateLimit> rateLimitsPerEndpoint) {
        this.rateLimitsPerEndpoint = rateLimitsPerEndpoint;
    }
}
