package sec12;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMultiCast {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        // onBackpressureBuffer has bounded queue(size 256).
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("hope you are doing well");

        Util.sleep(2);
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("new message");
    }

    private static void demo2() {
        // onBackpressureBuffer has bounded queue(size 256).
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("hope you are doing well");

        Util.sleep(2);
        flux.subscribe(Util.subscriber("jake"));
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("new message");
    }
}
