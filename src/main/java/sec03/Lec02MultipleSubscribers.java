package sec03;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        flux
                .filter(item -> item % 2 == 0)
                .subscribe(Util.subscriber("sub3"));
        flux
                .map(item -> item * 2)
                .subscribe(Util.subscriber("sub4"));
    }
}
