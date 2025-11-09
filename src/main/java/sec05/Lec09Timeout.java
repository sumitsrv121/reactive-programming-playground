package sec05;

import common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {
    public static void main(String[] args) {
        getProductName()
                .timeout(Duration.ofSeconds(1), getProductNameFallback())
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber());

        Util.sleep(5);
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> getProductNameFallback() {
        return Mono.fromSupplier(() -> "fallback-service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(10));
    }
}
