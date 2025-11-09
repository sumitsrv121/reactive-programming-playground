package sec05;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    private static final Logger log = LoggerFactory.getLogger(Lec01Handle.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .filter(value -> value != 7)
                .handle((value, synchronousSink) -> {
                    switch (value) {
                        case 1 -> synchronousSink.next(-2);
                        case 4 -> log.info("Skipping value " + value);
                        case 7 ->
                                synchronousSink.error(new RuntimeException("Got 7 which resulted in error"));
                        default -> synchronousSink.next(value);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
