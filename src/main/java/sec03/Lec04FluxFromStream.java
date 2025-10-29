package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        Flux<Integer> integerFlux = Flux.fromStream(list::stream);

        integerFlux
                .subscribe(Util.subscriber());

        integerFlux
                .subscribe(Util.subscriber());


    }
}
