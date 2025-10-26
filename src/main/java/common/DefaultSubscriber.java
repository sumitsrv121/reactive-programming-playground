package common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        log.info("{} Received data: {}", this.name, t);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} Received error: {}", this.name, throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        log.info("{} Completed!", this.name);
    }
}
