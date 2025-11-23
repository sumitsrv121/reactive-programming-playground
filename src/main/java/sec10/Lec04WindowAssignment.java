package sec10;

import common.Util;
import reactor.core.publisher.Flux;
import sec10.assignment.FileWriterService;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {
    public static void main(String[] args) {
        var filePath = "src/main/resources/sec10/file-%d.log";
        var counter = new AtomicInteger(0);
        eventStream()
                .window(Duration.ofSeconds(10))
                .flatMap(eventData ->
                        FileWriterService.writeToFile(eventData,
                                Path.of(filePath.formatted(counter.incrementAndGet()))
                        )
                )
                .subscribe();

        Util.sleep(100);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                // .take(10)
                // .concatWith(Flux.never())
                .map(i -> "event " + i);
    }
}
