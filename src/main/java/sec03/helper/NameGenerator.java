package sec03.helper;

import com.github.javafaker.Name;
import common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {
    private static final Name name = Util.faker().name();

    public static List<String> getNamesList(int count) {
        return IntStream.rangeClosed(1, count).mapToObj(i -> generateName()).toList();
    }

    private static String generateName() {
        Util.sleep(1);
        return name.name();
    }

    public static Flux<String> getNameFlux(int count) {
        return Flux.range(1, count).map(i -> generateName());
    }
}
