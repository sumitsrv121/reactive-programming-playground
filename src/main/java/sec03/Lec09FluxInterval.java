package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .subscribe(Util.subscriber());

        Util.sleep(5);
    }
}
