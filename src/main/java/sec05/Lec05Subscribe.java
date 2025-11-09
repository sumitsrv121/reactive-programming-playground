package sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(val -> log.info("receive: {}", val))
                .doOnComplete(() -> log.info("Complete"))
                .doOnError(err -> log.error("error", err))
                .subscribe();
    }
}
