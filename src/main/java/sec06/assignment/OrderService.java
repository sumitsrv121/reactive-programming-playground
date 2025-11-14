package sec06.assignment;

import common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class OrderService extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private Flux<Order> orderFlux;

    public Flux<Order> getOrderStream() {
        if (Objects.isNull(orderFlux)) {
            this.orderFlux = fetchOrderDetails();
        }
        return this.orderFlux;
    }

    private Flux<Order> fetchOrderDetails() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::mapToOrder)
                .doOnNext(order -> log.info("Order Received: {}", order))
                .share();
    }

    private Order mapToOrder(String response) {
        String[] splitResponses = response.split(":");
        return new Order(splitResponses[0], splitResponses[1],
                Integer.parseInt(splitResponses[2]),
                Integer.parseInt(splitResponses[3])
        );
    }

}
