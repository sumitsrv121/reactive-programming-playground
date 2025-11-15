package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {
    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);
    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(Lec08Parallel::process)
                .sequential()
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(1);
        return i * 2;
    }
}
