package sec11;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {
    public static void main(String[] args) {
        demo5();

        Util.sleep(5);
    }

    private static void demo1() {
        getCountryName()
                .repeat(3)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .repeat()
                .takeUntil("Canada"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(() -> atomicInteger.incrementAndGet() < 3)
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofMillis(500)).take(2))
                .subscribe(Util.subscriber());
    }

    private static void demo5() {
        Flux.just(1, 2, 3)
                .repeat(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono
                .fromSupplier(() -> Util.faker().country().name());
    }
}
