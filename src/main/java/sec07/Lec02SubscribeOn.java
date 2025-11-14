package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(
                        sink -> {
                            for (int i = 0; i < 3; i++) {
                                log.info("Generating value : {}", i);
                                sink.next(i);
                            }
                            sink.complete();
                        }).doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        flux.subscribe(Util.subscriber());

        Util.sleep(2);
    }
}
