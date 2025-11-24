package sec11;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {
    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo4();

        Util.sleep(10);
    }

    private static void demo1() {
        getCountryName()
                .retry(3)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(Retry.max(2))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        getCountryName()
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(1))
                                .doBeforeRetry(signal -> log.info("Retrying {}", signal.totalRetries()))
                ).subscribe(Util.subscriber());
    }

    private static void demo4() {
        getCountryName()
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(1))
                                .filter(ex -> ex instanceof RuntimeException)
                                .onRetryExhaustedThrow((retrySpec, signal) -> signal.failure())
                ).subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 5) {
                        throw new RuntimeException("OOPS!!!");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> log.error("Error: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("Subscribing"));
    }
}
