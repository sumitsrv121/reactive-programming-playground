package sec04;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lec03FluxSinkThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        final List<Integer> list = new ArrayList<>();

        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(r);
        }
        Util.sleep(3);
        log.info("Size of list {}", list.size());
    }

    private static void demo2() {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();

        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generateName();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(r);
        }
        Util.sleep(3);
        log.info("Size of list {}", list.size());
    }
}
