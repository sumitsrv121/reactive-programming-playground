package common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

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
}
