package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec09MonoDefer {
    private static final Logger log = LoggerFactory.getLogger(Lec09MonoDefer.class);

    public static void main(String[] args) {
        Mono.defer(() -> createPublisher())
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher() {
        log.info("Creating publisher.....");
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        log.info("Find the sum of the list {}", list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
