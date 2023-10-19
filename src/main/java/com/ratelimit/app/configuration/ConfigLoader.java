package com.ratelimit.app.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class ConfigLoader {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RateLimitConfig rateLimitConfig() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:config.json");
        return objectMapper.readValue(resource.getInputStream(), RateLimitConfig.class);
    }
}