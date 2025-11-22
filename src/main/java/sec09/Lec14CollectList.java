package sec09;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec14CollectList {
    public static void main(String[] args) {
        Mono<List<Integer>> listMono = Flux.range(1, 10)
                .concatWith(Flux.error(new RuntimeException("oops")))
                .collectList();



        listMono.subscribe(Util.subscriber());
    }
}
