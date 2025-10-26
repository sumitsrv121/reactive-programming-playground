package sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        Mono<Integer> monoJust = Mono.just(1);

        monoJust.subscribe(
                data -> log.info("Received value: {}", data),
                error -> log.error("Received error: {}", error.getMessage()),
                () -> log.info("OnComplete is called"),
                subscription -> subscription.request(1)
        );
    }
}
