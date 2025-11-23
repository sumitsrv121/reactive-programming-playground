package sec10;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {
    public static void main(String[] args) {
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvents)
                .subscribe();

        Util.sleep(60);
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event " + i);
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux.doOnNext(val -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
