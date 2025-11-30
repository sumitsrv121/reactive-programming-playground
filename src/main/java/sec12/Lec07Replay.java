package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec07Replay {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        // handle all messages in unbounded queue for subscriber which starts late
        Sinks.Many<Object> sink = Sinks.many().replay().all();

        emitMessage(sink);
    }

    private static void demo2() {
        // handle last 1 message using limit for subscriber which starts late
        Sinks.Many<Object> sink = Sinks.many().replay().limit(1);

        emitMessage(sink);
    }

    private static void demo3() {
        // handle all messages from last 200 millisecond for subscriber which starts late
        Sinks.Many<Object> sink = Sinks.many().replay().limit(Duration.ofMillis(200));

        emitMessage(sink);
    }

    private static void emitMessage(Sinks.Many<Object> sink) {
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("How are you");
        sink.tryEmitNext("?");

        Util.sleep(2);
        flux.subscribe(Util.subscriber("jake"));
    }
}
