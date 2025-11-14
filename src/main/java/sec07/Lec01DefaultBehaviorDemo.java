package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviorDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {
        var flux = Flux.create(
                sink -> {
                    for (int i = 0; i < 3; i++) {
                        log.info("Generating value : {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                }).doOnNext(v -> log.info("value: {}", v));

        Runnable r = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(r);

    }
}
