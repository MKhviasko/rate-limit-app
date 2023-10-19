package com.ratelimit.app.controller;

import com.ratelimit.app.dto.RateLimitResponseDto;
import com.ratelimit.app.dto.RouteTemplateDto;
import com.ratelimit.app.facades.RateLimitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/take")
public class RateLimitController {

    public static final String RATE_LIMIT_REMAINING_HEADER = "x-ratelimit-remaining";

    @Autowired
    private RateLimitFacade rateLimitFacadeImpl;

    @GetMapping(value = "/{routeTemplate}", produces = "application/json")
    public ResponseEntity<RateLimitResponseDto> checkRateLimitByRouteParam(@PathVariable String routeTemplate,
        @RequestParam("Authorization") String userToken) {
        var response = rateLimitFacadeImpl.checkRateLimit(routeTemplate, userToken);
        return ResponseEntity.ok()
            .headers(fillResponseHeaders(response))
            .body(response);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<RateLimitResponseDto> checkRateLimitByQueryParam(@RequestParam("routeTemplate") String routeTemplate,
        @RequestParam("Authorization") String userToken) {
        var response = rateLimitFacadeImpl.checkRateLimit(routeTemplate, userToken);
        return ResponseEntity.ok()
            .headers(fillResponseHeaders(response))
            .body(response);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<RateLimitResponseDto> checkRateLimitByRequestBody(@RequestBody RouteTemplateDto routeTemplate,
        @RequestParam("Authorization") String userToken) {
        var response = rateLimitFacadeImpl.checkRateLimit(routeTemplate.getRouteTemplate(), userToken);
        return ResponseEntity.ok()
            .headers(fillResponseHeaders(response))
            .body(response);
    }

    private HttpHeaders fillResponseHeaders(final RateLimitResponseDto response) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(RATE_LIMIT_REMAINING_HEADER, String.valueOf(response.getRemainingTokens()));
        return headers;
    }
}
