package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);
    public static void main(String[] args) {
        var stockFlux = stockStream().replay().autoConnect(0);

        Util.sleep(4);

        log.info("=====Sam joining====");
        stockFlux
                .subscribe(Util.subscriber("Sam"));

        Util.sleep(4);

        log.info("=====Mike joining====");

        stockFlux
                .subscribe(Util.subscriber("Mike"));

        Util.sleep(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(1, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitting price {}", price))
                .cast(Integer.class);
    }
}
