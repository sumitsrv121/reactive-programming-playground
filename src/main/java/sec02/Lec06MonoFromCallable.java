package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {
    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);
    public static void main(String[] args) {
        Mono.fromCallable(() -> sum(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
                .subscribe(Util.subscriber("SumSubscriber"));
    }

    private static int sum(List<Integer> list) {
        log.info("Executing the sum of the list {}.....", list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
