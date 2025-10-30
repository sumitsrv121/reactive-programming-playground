package sec03.assignment;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Stock implements Instrument {
    private static final int THRESHOLD_DURATION = 20 * 1000;
    @Override
    public Flux<Integer> getLatestPriceUpdate(int duration) {
        var fakerRandom = Util.faker().random();
        return Flux.interval(Duration.ofMillis(duration))
                .map(i -> fakerRandom.nextInt(80, 120))
                .take(Duration.ofMillis(THRESHOLD_DURATION));
    }
}
