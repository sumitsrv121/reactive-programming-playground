package sec10;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {
    public static void main(String[] args) {
        demo4();

        Util.sleep(60);
    }

    private static void demo1() {
        // this buffer will hold Integer.MAX_VALUE items
        // and will wait till accumulating those many items
        // or source has to complete
        // before sending it to subscriber.
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        // buffer will store 3 items or less (in case of complete signal from producer)
        // and hand it over to subscriber
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }


    private static void demo4() {
        // buffer will wait to get 3 items fill in if there is no complete signal
        // which is sort of bad. So instead of that use bufferTimeout
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                // .take(10)
                // .concatWith(Flux.never())
                .map(i -> "event " + i);
    }
}
