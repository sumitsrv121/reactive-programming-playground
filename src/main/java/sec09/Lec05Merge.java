package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {
    public static void main(String[] args) {
        demo2();

        Util.sleep(3);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1().mergeWith(producer2())
                .mergeWith(producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }


    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(11, 12, 13)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
}
