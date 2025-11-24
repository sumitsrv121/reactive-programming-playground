package sec11;

import common.Util;
import reactor.core.publisher.Mono;

public class Lec01Repeat {
    public static void main(String[] args) {
        Mono<String> countryMono = Mono
                .fromSupplier(() -> Util.faker().country().name());

        var subscriber = Util.subscriber();

        countryMono
                .repeat(3)
                .subscribe(subscriber);

    }
}
