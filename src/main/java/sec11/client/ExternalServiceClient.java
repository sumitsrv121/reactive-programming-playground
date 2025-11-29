package sec11.client;

import common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServiceClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private static final String PRODUCT_SERVICE = "product";


    public Mono<String> getProductName(int id) {
        return getDetails("/demo06/" + ExternalServiceClient.PRODUCT_SERVICE + "/" + id);
    }

    public Mono<String> getCountry() {
        return getDetails("/demo06/country");
    }

    private Mono<String> getDetails(String uri) {
        return this.httpClient.get()
                .uri(uri)
                .response(this::toResponse)
                .next()
                .doOnNext(data -> log.info("data fetched: {}", data));
    }

    private Flux<String> toResponse(HttpClientResponse clientResponse, ByteBufFlux byteBufFlux) {
        return switch (clientResponse.status().code()) {
            case 200 -> byteBufFlux.asString();
            case 400 -> Flux.error(new ClientError());
            default -> Flux.error(new ServerError());
        };
    }
}
