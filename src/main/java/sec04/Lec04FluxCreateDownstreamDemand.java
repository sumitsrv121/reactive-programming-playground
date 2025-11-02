package sec04;

import common.DefaultSubscriber;
import common.Util;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        var name = Util.faker().name();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var firstName = name.firstName();
                log.info("generated first name {}", firstName);
                fluxSink.next(firstName);
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());

    }
}
