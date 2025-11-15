package common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.time.Duration;

public class Util {
    private static final Faker faker = Faker.instance();

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
}
