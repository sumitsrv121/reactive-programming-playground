package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

    public static void main(String[] args) {
        var country = Util.faker().country();
        Flux.<String>generate(synchronousSink -> {
                    synchronousSink.next(country.name());
                })
                .handle((countryName, sink) -> {
                    sink.next(countryName);
                    if ("canada".equalsIgnoreCase(countryName)) {
                        sink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}
