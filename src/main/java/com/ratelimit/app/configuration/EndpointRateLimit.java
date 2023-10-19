package com.ratelimit.app.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointRateLimit {

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("burstFree")
    private int burstFree;

    @JsonProperty("sustainedFree")
    private int sustainedFree;

    @JsonProperty("burstPaid")
    private int burstPaid;

    @JsonProperty("sustainedPaid")
    private int sustainedPaid;

    public String getEndpoint() {
        return endpoint;
    }

    public int getBurstFree() {
        return burstFree;
    }

    public int getSustainedFree() {
        return sustainedFree;
    }

    public int getBurstPaid() {
        return burstPaid;
    }

    public int getSustainedPaid() {
        return sustainedPaid;
    }
}