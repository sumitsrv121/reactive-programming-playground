package sec04;

import com.github.javafaker.Country;
import common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {
    public static void main(String[] args) {
        Country country = Util.faker().country();

        demo2(country);
    }

    private static void demo1(Country country) {
        Flux.<String>generate(synchronousSink -> {
                    synchronousSink.next(country.name());
                }).takeUntil("canada"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }

    private static void demo2(Country country) {
        Flux.<String>generate(synchronousSink -> {
                    String countryName = country.name();
                    synchronousSink.next(countryName);
                    if (countryName.equalsIgnoreCase("Canada")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
