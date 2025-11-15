package sec08;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02LimitRate {
    private static final Logger log = LoggerFactory.getLogger(Lec02LimitRate.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        var flux = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("Generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        flux.limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec02LimitRate::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleep(1);
        return i;
    }
}

