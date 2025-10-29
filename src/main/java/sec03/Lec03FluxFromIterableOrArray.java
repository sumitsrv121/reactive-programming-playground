package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber("ListSubscriber"));

        Integer[] arr = {1, 2, 3, 4};

        Flux.fromArray(arr)
                .subscribe(Util.subscriber("ArraySubscriber"));
    }
}
