package sec05.assignment;

import common.ExternalServiceClient;
import reactor.core.publisher.Mono;

public class FallbackService {
    private final ExternalServiceClient httpClient;

    public FallbackService(ExternalServiceClient httpClient) {
        this.httpClient = httpClient;
    }

    public Mono<String> fallbackForTimeout(int productId) {
        return httpClient.fallbackForTimeoutProductName(productId);
    }

    public Mono<String> fallbackIfEmpty(int productId) {
        return httpClient.fallbackForEmptyProductName(productId);
    }
}
