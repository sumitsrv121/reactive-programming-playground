package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                    // synchronousSink.next(2);
                    // synchronousSink.complete();
                }).take(4)
                .subscribe(Util.subscriber());
    }
}
