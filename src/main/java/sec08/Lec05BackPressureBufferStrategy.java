package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05BackPressureBufferStrategy {
    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureBufferStrategy.class);
    public static void main(String[] args) {
        var flux = Flux.
                create(sink -> {
                    for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                        log.info("Generating: {}", i);
                        sink.next(i);
                        Util.sleepMs(50);
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        flux
                //.onBackpressureBuffer()
                .onBackpressureError()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureBufferStrategy::timeConsumingTask)
                .subscribe();

        Util.sleep(60);
    }

    private static int timeConsumingTask(int i) {
        log.info("Received: {}", i);
        Util.sleep(1);
        return i;
    }
}
