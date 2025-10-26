package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);
    public static void main(String[] args) {
        Mono.fromSupplier(() -> sum(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
                .subscribe(Util.subscriber("SumSubscriber"));
    }

    private static int sum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
