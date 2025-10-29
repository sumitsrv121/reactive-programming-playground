package sec03;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {
        var name = Util.faker().name();
        Flux.range(1, 5)
                .log("range-map")
                .map(i -> name.name())
                .subscribe(Util.subscriber());
    }
}
