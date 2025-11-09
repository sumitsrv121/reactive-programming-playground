package sec05;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        onErrorContinue();
    }

    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((err, val) -> log.error("{} value which caused error", val, err))
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorResume(ArithmeticException.class, err -> fallback1())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback1() {
        return Flux.just(Util.faker().random().nextInt(10, 100));
    }
    private static Flux<Integer> fallback2() {
        return Flux.just(Util.faker().random().nextInt(100, 1000));
    }
}
