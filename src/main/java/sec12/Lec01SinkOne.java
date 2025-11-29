package sec12;

import common.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
    public static void main(String[] args) {
        demo02();

    }

    private static void demo01() {
        // create sink and emit data
        Sinks.One<Object> sink = Sinks.one();

        // use the same sink to get the data by converting it as Mono
        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber());

        // If you want to emit data
        //sink.tryEmitValue("Hi");
        // If you don't have data try emit empty
        // sink.tryEmitEmpty();
        // If you want to emit error
        sink.tryEmitError(new RuntimeException("Oops!"));

    }

    private static void demo02() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sam"));
        mono.subscribe(Util.subscriber("Mike"));
        sink.tryEmitValue("Hi");
    }
}
