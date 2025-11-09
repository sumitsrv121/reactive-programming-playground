package common;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .aggregate()
                .asString();
    }

    public Flux<String> getNames() {
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Mono<String> fetchProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo03/product/" + productId)
                .responseContent()
                .aggregate()
                .asString();
    }

    public Mono<String> fallbackForEmptyProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo03/empty-fallback/product/" + productId)
                .responseContent()
                .aggregate()
                .asString();
    }

    public Mono<String> fallbackForTimeoutProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo03/timeout-fallback/product/" + productId)
                .responseContent()
                .aggregate()
                .asString();
    }
}
