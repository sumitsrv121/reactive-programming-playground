package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        var cricketStream = liveStream().share();

        Util.sleep(2);
        cricketStream
                .take(4)
                .subscribe(Util.subscriber("Sam"));
        Util.sleep(3);
        cricketStream
                .take(3)
                .subscribe(Util.subscriber("Mike"));

        Util.sleep(15);
    }

    private static Flux<String> liveStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("Playing: {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                ).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
