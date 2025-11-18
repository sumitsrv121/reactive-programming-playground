package sec09.assignment;

import common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private static final String PRICE_SERVICE = "price";
    private static final String PRODUCT_SERVICE = "product";
    private static final String REVIEW_SERVICE = "review";


    public Mono<Product> getProductDetails(int id) {
        return Mono.zip(getDetails(PRODUCT_SERVICE, id), getDetails(PRICE_SERVICE, id),
                        getDetails(REVIEW_SERVICE, id))
                .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    private Mono<String> getDetails(String service, int id) {
        return this.httpClient.get()
                .uri("/demo05/" + service + "/" + id)
                .responseContent()
                .aggregate()
                .asString()
                .doOnNext(data -> log.info("data fetched: {}", data));
    }
}
