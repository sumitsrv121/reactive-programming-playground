package sec03.assignment;

import reactor.core.publisher.Flux;

public interface Instrument {
    Flux<Integer> getLatestPriceUpdate(int duration);
}
