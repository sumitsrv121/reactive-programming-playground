package sec04;

import com.github.javafaker.Country;
import common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec08StateProblem {
    public static void main(String[] args) {
        Country country = Util.faker().country();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Flux.<String>generate(synchronousSink -> {
            String countryName = country.name();
            synchronousSink.next(countryName);
            atomicInteger.incrementAndGet();
            if (atomicInteger.get() == 10 || countryName.equalsIgnoreCase("Canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
