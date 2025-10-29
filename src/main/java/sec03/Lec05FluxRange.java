package sec03;

import com.github.javafaker.Name;
import common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        Name name = Util.faker().name();
        // generate 10 random names
        Flux.range(0, 10)
                .map(data -> name.name())
                .subscribe(Util.subscriber());
    }
}
