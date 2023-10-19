# Rate Limit App

This standalone service is designed to protect APIs and ensure a controlled flow of incoming requests. It utilizes the Token Bucket algorithm to manage rate limits for different routes.

### Rate Limiting Mechanism

The service enforces rate limits, which are determined by two key parameters: Burst (maximum burst requests) and Sustained (requests per minute). This mechanism enables requests to accumulate "credit" or tokens, capped at the maximum burst limit. Tokens refresh according to the rate specified by the sustained limit. When a request is validated, it consumes one token. If tokens are exhausted, the request is denied until they replenish at the defined sustained rate.

### Configuration

The service loads its configuration from config.json:

- endpoint: The route template to limit.
- burst: The maximum burst requests allowed.
- sustained: The number of sustained requests per minute.

### Implementation and Usage

The service provides a single endpoint located at /take/. This endpoint is accessible to client-facing services and is used to determine whether a request should be accepted or declined based on the defined rate limit.

Here's how it works:

If the rate limit is not surpassed, the endpoint deducts a token and confirms the request, providing the count of remaining tokens.

In cases where the rate limit has already been reached, the endpoint's response indicates 0 remaining tokens, signaling that the request is rejected.

### Token Bucket Algorithm

https://en.wikipedia.org/wiki/Token_bucket