package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
        takeUntilOperator();
    }

    private static void takeOperator() {
    /*IntStream.rangeClosed(1, 10)
            .limit(3)
            .forEach(System.out::println);*/
        // Similar is take to limit

        Flux.range(1, 10)
                .log("takeLog")
                .take(3)
                .log("subscriberLog")
                .subscribe(Util.subscriber());
        // Take we invoke cancel if range is greater than request(10 > 3).
        // Similarly, take subscriber is actual subscriber for which complete will
        // be invoked.
    }

    private static void takeWhileOperator() {
        Flux.range(1, 10)
                .log("takeWhile")
                .takeWhile(value -> value < 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntilOperator() {
        Flux.range(1, 10)
                .log("takeUntil")
                .takeUntil(value -> value > 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
