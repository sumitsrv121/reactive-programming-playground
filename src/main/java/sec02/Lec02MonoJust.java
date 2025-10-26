package sec02;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;


public class Lec02MonoJust {
    private static final Logger log = LoggerFactory.getLogger(Lec02MonoJust.class);

    public static void main(String[] args) {
        Mono<String> monoString = Mono.just("sumit");
        log.info("String Mono: {}", monoString); // String Mono: MonoJust

        monoString.subscribe(data -> log.info("Received: {}", data)); // Received: sumit
    }
}
