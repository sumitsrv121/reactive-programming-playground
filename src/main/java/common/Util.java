package common;

import org.reactivestreams.Subscriber;

public class Util {
    public static <T> Subscriber<T> subscriber(final String name) {
        return new DefaultSubscriber<>(name);
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }
}
