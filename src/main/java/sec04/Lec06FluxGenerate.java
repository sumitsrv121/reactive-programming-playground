package sec04;

import com.github.javafaker.Country;
import common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {
        assignment();
    }

    private static void generateSynchronousSink() {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                    // synchronousSink.next(2);
                    // synchronousSink.complete();
                }).take(4)
                .subscribe(Util.subscriber());
    }

    private static void assignment() {
        Country country = Util.faker().country();
        Flux.<String>generate(synchronousSink -> {
                    synchronousSink.next(country.name());
                }).takeUntil("Canada"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }
}
