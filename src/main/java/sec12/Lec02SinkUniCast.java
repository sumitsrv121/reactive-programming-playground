package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec02SinkUniCast {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        // onBackpressureBuffer has unbounded queue.
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("hope you are doing well");

        flux.subscribe(Util.subscriber("sam"));
    }

    private static void demo2() {
        // onBackpressureBuffer has unbounded queue.
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("hope you are doing well");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
    }
}
