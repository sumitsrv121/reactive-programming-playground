package common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {
    private static final Faker faker = Faker.instance();
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static <T> Subscriber<T> subscriber(final String name) {
        return new DefaultSubscriber<>(name);
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepMs(int milliSeconds) {
        try {
            Thread.sleep(Duration.ofMillis(milliSeconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(subscription -> log.info("subscribing to {}", name))
                .doOnCancel(() -> log.info("Cancelling: {}", name))
                .doOnComplete(() -> log.info("Complete: {}", name));
    }
}
