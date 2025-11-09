package sec05.assignment;

import common.ExternalServiceClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductServiceImpl implements ProductService {
    private final ExternalServiceClient client;
    private final FallbackService fallbackService;

    public ProductServiceImpl(ExternalServiceClient client) {
        this.client = client;
        fallbackService = new FallbackService(client);
    }

    @Override
    public Mono<String> getProductName(int productId) {
        return this.client.getProductName(productId)
                .timeout(Duration.ofSeconds(2), fallbackService.fallbackForTimeout(productId))
                .switchIfEmpty(fallbackService.fallbackIfEmpty(productId));
    }

}
