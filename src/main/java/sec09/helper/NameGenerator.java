package sec09.helper;

import com.github.javafaker.Name;
import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private static final Name name = Util.faker().name();
    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    String firstName = name.firstName();
                    log.info("Generating Name: {}", firstName);
                    Util.sleep(1);
                    redis.add(firstName);
                    sink.next(firstName);
                }).cast(String.class)
                .startWith(redis);
    }
}
