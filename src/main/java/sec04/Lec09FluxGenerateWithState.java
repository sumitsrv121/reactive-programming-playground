package sec04;

import com.github.javafaker.Country;
import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec09FluxGenerateWithState {
    private static final Logger log = LoggerFactory.getLogger(Lec09FluxGenerateWithState.class);

    public static void main(String[] args) {
        Country country = Util.faker().country();
        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    String countryName = country.name();
                    sink.next(countryName);
                    counter++;
                    if (counter == 10 || countryName.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                    return counter;
                },
                (counter) -> log.info("Operation completed after iterations {}", counter)
        ).subscribe(Util.subscriber());
    }
}
