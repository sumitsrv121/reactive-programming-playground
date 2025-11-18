package sec09;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    record Car(String body, String engine, String tires) {
    }

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .subscribe(Util.subscriber());

        Util.sleep(5);
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }


    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "tires-" + i)
                .delayElements(Duration.ofMillis(75));
    }
}
