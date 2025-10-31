package sec04.helper;

import common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> sink;
    @Override
    public void accept(FluxSink<String> sink) {
        this.sink = sink;
    }

    public void generateName() {
        this.sink.next(Util.faker().name().firstName());
    }
}
